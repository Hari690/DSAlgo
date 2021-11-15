import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class AlertProcessor implements LogProcessor {

    static final int ALERT_THRESHOLD = 10;
    static final int ALERT_DURATION_SECS = 120;
    public static final int SESSION_SIZE = 1;

    boolean inAlert = false;

    // Storing data for ALERT_DURATION_SECS based on dateTime in epoch as key.
    private TreeMap<Long, List<LogLine>> alertStats = new TreeMap<>();

    @Override
    public void process(LogLine logLine) {
        addToAlertWindow(logLine);
        checkAlertStatus();
    }

    private void addToAlertWindow(LogLine logLine) {
        alertStats.computeIfAbsent(logLine.getDate(), key -> new ArrayList<>()).add(logLine);
    }

    private void checkAlertStatus() {
        // Average value will be average over 2mins aggregated over 10secs session duration values.
        // i.e total hits counted per 10secs over 2mins/no of session intervals = 12
        float average = getAverageValue();
        if(average >= ALERT_THRESHOLD) {
            if( !inAlert) {
                System.out.println("High Traffic generated an alert - average hits = "+average+", triggered at "+epochToLocalDateTime(alertStats.lastEntry().getKey()));
                inAlert = true;
            }
        } else {
            if(inAlert) {
                System.out.println("Alarm recovered - average hits = "+average+", triggered at "+epochToLocalDateTime(alertStats.lastEntry().getKey()));
                inAlert = false;
            }
        }
        pruneAlertWindow();
    }

    private void pruneAlertWindow() {
        // since items can come in late we will keep the window with extra data for LAG_DURATIONS_SECS and prune
        // older items that fall outside this window.
        while(alertStats.lastKey() - alertStats.firstKey() - 1 >= ALERT_DURATION_SECS + LogProcessor.LAG_DURATIONS_SECS) {
            alertStats.pollFirstEntry();
        }
    }
    
    private float getAverageValue() {
        long oldestTime = alertStats.firstEntry().getKey();
        long latestTime = alertStats.lastEntry().getKey();
        // we wait until the session lag to ensure all traffic for the duration has reached.
        if((latestTime-oldestTime)>=ALERT_DURATION_SECS + LogProcessor.LAG_DURATIONS_SECS) {
            long count = 0;
            // get sum of traffic across all sessions and divide by no of sessions to get average.
            for (long time = oldestTime; time < oldestTime + ALERT_DURATION_SECS; time += SESSION_SIZE) {
                SortedMap<Long, List<LogLine>> subMap = alertStats.subMap(time, time + SESSION_SIZE);
                count += subMap.entrySet().stream().map(entry -> entry.getValue().size()).reduce(0, (a, b) -> a + b);
            }
            return (float) count / (ALERT_DURATION_SECS/SESSION_SIZE);
        }
        return 0;
    }

    public boolean isInAlert() {
        return inAlert;
    }

    public TreeMap<Long, List<LogLine>> getAlertStats() {
        return alertStats;
    }
}
