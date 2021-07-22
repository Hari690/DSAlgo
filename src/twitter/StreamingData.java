package twitter;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class StreamingData {
    Map<String,TreeMap<Long,Long>> aggregateMinMap = new HashMap<>();
    Map<String,TreeMap<Long,Long>> aggregateHoursMap = new HashMap<>();
    Map<String,TreeMap<Long,Long>> aggregateDaysMap = new HashMap<>();

    public void insertEventData(String eventName, long epochTime) {
        Long mins = (epochTime/1000)/60;
        Long hours = mins/60;
        Long days = hours/24;

        TreeMap<Long,Long> minsMap = aggregateMinMap.get(eventName);
        if( minsMap==null) {
            minsMap = new TreeMap<>();
            minsMap.put(mins, 0l);
        }
        minsMap.put(mins, minsMap.getOrDefault(mins,0l)+1);
        aggregateMinMap.put(eventName, minsMap);

        TreeMap<Long,Long> hoursMap = aggregateHoursMap.get(eventName);
        if( hoursMap==null) {
            hoursMap = new TreeMap<>();
            hoursMap.put(hours, 1l);
        }
        hoursMap.put(hours, hoursMap.getOrDefault(hours, 0l)+1);
        aggregateHoursMap.put(eventName, hoursMap);

        TreeMap<Long,Long> daysMap = aggregateDaysMap.get(eventName);
        if( daysMap==null) {
            daysMap = new TreeMap<>();
            daysMap.put(days, 1l);
        }
        daysMap.put(days, daysMap.getOrDefault(days,0l)+1);
        aggregateDaysMap.put(eventName, daysMap);
    }

    public Long getEventData(String eventName, Long startTimeEpoch, Long endTimeEpoch, String queryType) {
        if ( queryType.equals("mins")) {
            TreeMap<Long,Long> minsMap = aggregateMinMap.get(eventName);
            if( minsMap == null) {
                return 0l;
            }
            long count =0;
            Map<Long,Long> durationMap = minsMap.subMap((startTimeEpoch/1000)/60,true, (endTimeEpoch/1000)/60, true);
            if( durationMap == null) {
                return 0l;
            }
            for(Map.Entry<Long,Long> entry : durationMap.entrySet()) {
                count+=entry.getValue();
            }
            return count;
        }
        if ( queryType.equals("hours")) {
            TreeMap<Long,Long> innerMap = aggregateHoursMap.get(eventName);
            if( innerMap == null) {
                return 0l;
            }
            long count =0;
            Map<Long,Long> durationMap = innerMap.subMap((startTimeEpoch/1000)/60/60, (endTimeEpoch/1000)/60/60);
            if( durationMap == null) {
                return 0l;
            }
            for(Map.Entry<Long,Long> entry : durationMap.entrySet()) {
                count+=entry.getValue();
            }
            return count;
        }
        if ( queryType.equals("days")) {
            TreeMap<Long,Long> innerMap = aggregateDaysMap.get(eventName);
            if( innerMap == null) {
                return 0l;
            }
            long count =0;
            Map<Long,Long> durationMap = innerMap.subMap((startTimeEpoch/1000)/60/60/24, (endTimeEpoch/1000)/60/60/24);
            if( durationMap == null) {
                return 0l;
            }
            for(Map.Entry<Long,Long> entry : durationMap.entrySet()) {
                count+=entry.getValue();
            }
            return count;
        }
        return 0l;
    }

    public void testMinQuery() {
        insertEventData("A",123456789l);
        Long count = getEventData("A",123456788l,123456790l,"mins");
        System.out.println((count==1l)?"Passed":"Failed");
        insertEventData("A",143456787l);
        count = getEventData("A",123456788l,123456790l,"mins");
        System.out.println((count==1l)?"Passed":"Failed");
    }

    public static void main(String[] args) {
        new StreamingData().testMinQuery();
    }
}

