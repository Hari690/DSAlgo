package treemap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * You are given several logs that each log contains a unique id and timestamp. Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.
 * Design a log storage system to implement the following functions:
 * void Put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.
 * int[] Retrieve(String start, String end, String granularity): Return the id of logs whose timestamps are within the range from start to end. Start and end all have the same format as timestamp. However, granularity means the time level for consideration. For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.
 * Example 1:
 * put(1, "2017:01:01:23:59:59");
 * put(2, "2017:01:01:22:59:59");
 * put(3, "2016:01:01:00:00:00");
 * retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
 * retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
 * Note:
 * There will be at most 300 operations of Put or Retrieve.
 * Year ranges from [2000,2017]. Hour ranges from [00,23].
 * Output for Retrieve has no order required.
 */
public class DesignLogStorageSystem {
    TreeMap<String, List<Integer>> timeToIdMap;
    String minTimeStamp;
    String maxTimeStamp;
    Map<String, Integer> graMap;

    public DesignLogStorageSystem() {
        timeToIdMap = new TreeMap<>();
        minTimeStamp = "2000:01:01:00:00:00";
        maxTimeStamp = "2017:12:31:23:59:59";
        graMap = new HashMap<>();
        graMap.put("Year", 4);
        graMap.put("Month", 7);
        graMap.put("Day", 10);
        graMap.put("Hour", 13);
        graMap.put("Minute", 16);
        graMap.put("Second", 19);
    }

    public void put(int id, String timestamp) {
        List<Integer> idList = timeToIdMap.getOrDefault(timestamp, new ArrayList<>());
        idList.add(id);
        timeToIdMap.put(timestamp, idList);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        List<Integer> ans = new ArrayList<>();
        int graIdx = graMap.get(gra);
        String startTime = s.substring(0, graIdx) + minTimeStamp.substring(graIdx);
        String endTime = e.substring(0, graIdx) + maxTimeStamp.substring(graIdx);

        NavigableMap<String, List<Integer>> subMap = timeToIdMap.subMap(
                startTime, true, endTime, true);

        for (String timeStamp : subMap.keySet()) {
            ans.addAll(subMap.get(timeStamp));
        }

        return ans;
    }
}
