package hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement a SnapshotArray that supports the following interface:
 *
 * SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 */
public class SnapshotArray {
    private int snapId = 0;
    private Map<Integer,Map<Integer,Integer>> map;
    public SnapshotArray(int length) {
        map = new HashMap<>();
        Map<Integer,Integer> currentMap = new HashMap<>();
        for(int i=0;i<length;i++)
            currentMap.put(i, 0);
        map.put(snapId,currentMap);
    }

    public void set(int index, int val) {
        Map<Integer,Integer> currentMap = map.get(snapId);
        currentMap.put(index,val);
    }

    public int snap() {
        snapId++;
        map.put(snapId, new HashMap<>());
        return snapId-1;
    }

    public int get(int index, int snap_id) {
        int currSnapId = snap_id;
        Map<Integer,Integer> currentMap = map.get(currSnapId);
        while(!currentMap.containsKey(index)) {
            currSnapId--;
            currentMap = map.get(currSnapId);
        }
        return currentMap.get(index);
    }
}
