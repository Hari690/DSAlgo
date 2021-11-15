import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StatsProcessor implements LogProcessor {

    private static final int SESSION_DURATION_SECS = 10;

    // Storing previous 10secs based on dateTime as key.
    private TreeMap<Long, List<LogLine>> sessionData = new TreeMap<>();

    private Long nextDateTime = null;

    @Override
    public void process(LogLine logLine) {
        addToSessionWindow(logLine);
        checkSessionStats(logLine.getDate());
    }

    private void addToSessionWindow(LogLine logLine) {
        sessionData.computeIfAbsent(logLine.getDate(), key -> new ArrayList<>()).add(logLine);
        if(nextDateTime == null) {
            nextDateTime = logLine.getDate();
        }
    }

    private String getSection(LogLine logLine) {
        return "/" + logLine.getRequestType().split(" ")[1].split("/")[1];
    }

    private void checkSessionStats(long currentLogDateTime) {
        printSessionStats();
        pruneSessionWindow(currentLogDateTime);
    }

    private void printSessionStats() {
        if(sessionData.firstKey() >= nextDateTime) {
            // Get data for the SESSION DURATION from beginning of the window.
            // Window will have more data than a session to allow for processing late data.
            // so the stats are displayed LAG_DURATIONS_SECS after every session.
            // Current approach is to aggregate the data on the fly when we have to display
            // session details. Alternatively we can maintain the data in another dictionary
            // and adjust that according to the `sessionData`
            Map<Long, List<LogLine>> sessionMap = sessionData.subMap(sessionData.firstKey(),
                sessionData.firstKey()+ SESSION_DURATION_SECS);
            Map<String,Long> sessionStats = new HashMap<>();
            for(Map.Entry<Long, List<LogLine>> entry : sessionMap.entrySet()) {
                for(LogLine logLine : entry.getValue()) {
                    String section = getSection(logLine);
                    sessionStats.put(section, sessionStats.getOrDefault(section, 0l) + 1);
                }
            }
            long windowEnd = sessionData.firstKey()+SESSION_DURATION_SECS;
            System.out.println("Stats "+sessionStats+" at "+epochToLocalDateTime(windowEnd));
            nextDateTime = windowEnd;
        }
    }

    private void pruneSessionWindow(long currentLogDateTime) {
        // Get rid of older data once we have accumlated more than LAG_DURATIONS_SECS data in the window
        // to allow for late data to get processed.
        if(currentLogDateTime > sessionData.firstKey()+SESSION_DURATION_SECS+LAG_DURATIONS_SECS) {
            sessionData.pollFirstEntry();
        }
    }
}
