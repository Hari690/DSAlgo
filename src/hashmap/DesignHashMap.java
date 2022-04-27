package hashmap;

import java.util.LinkedList;
import java.util.List;

/**
 * Design a HashMap without using any built-in hash table libraries.
 * Implement the MyHashMap class:
 * MyHashMap() initializes the object with an empty map.
 * void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
 * int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 * void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
 *
 *
 * Example 1:
 * Input
 * ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 * [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 * Output
 * [null, null, null, 1, -1, null, 1, null, -1]
 *
 * Explanation
 * MyHashMap myHashMap = new MyHashMap();
 * myHashMap.put(1, 1); // The map is now [[1,1]]
 * myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
 * myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
 * myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
 * myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
 * myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
 * myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
 * myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
 *
 */
public class DesignHashMap {
    class Entry {
        public int key;
        public int value;

        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry hashEntry = (Entry) o;
            return key == hashEntry.key;
        }

    }

    private int numBuckets = 10000;
    List<Entry>[] buckets;

    private int hash_function(int key) {
        return key % numBuckets;
    }

    public DesignHashMap() {
        buckets = new LinkedList[numBuckets];
    }

    public void put(int key, int value) {
        int i = hash_function(key);
        if (buckets[i] == null) buckets[i] = new LinkedList<>();
        int check = buckets[i].indexOf(new Entry(key, -1));
        if (check == -1)
            buckets[i].add(new Entry(key, value));
        else {
            Entry entry = buckets[i].get(check);
            entry.key = key;
            entry.value = value;
        }
    }

    public int get(int key) {
        int i = hash_function(key);
        if (buckets[i] == null)
            return -1;
        int check = buckets[i].indexOf(new Entry(key, -1));
        if (check == -1)
            return -1;
        return buckets[i].get(check).value;
    }

    public void remove(int key) {
        int i = hash_function(key);
        if (buckets[i] == null)
            return;
        buckets[i].remove(new Entry(key, -1));
    }
}