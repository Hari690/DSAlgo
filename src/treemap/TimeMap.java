package treemap;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the
 * key's value at a certain timestamp.
 *
 * Implement the TimeMap class:
 *
 * TimeMap() Initializes the object of the data structure.
 * void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
 * String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp. If there
 * are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".
 */
class TimeMap {

    /** Initialize your data structure here. */
    private Map<String, TreeMap<Integer,String>> map = null;
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        TreeMap<Integer,String> innerMap = map.get(key);
        if(innerMap==null)
            innerMap = new TreeMap<Integer,String>();
        innerMap.put(timestamp, value);
        map.put(key, innerMap);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer,String> innerMap = map.get(key);
        if(innerMap==null)
            return "";
        Map.Entry<Integer,String> entry = innerMap.floorEntry(timestamp);
        if(entry==null)
            return "";
        return entry.getValue();
    }
}
