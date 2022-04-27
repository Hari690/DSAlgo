package problems;

import java.util.LinkedList;
import java.util.List;

/**
 * Design a HashSet without using any built-in hash table libraries.
 *
 * Implement MyHashSet class:
 * void add(key) Inserts the value key into the HashSet.
 * bool contains(key) Returns whether the value key exists in the HashSet or not.
 * void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
 */
class DesignHashSet {

    /*
        Use an array of LinkedList.
     */
    private int numBuckets = 15000;
    List<Integer>[] buckets;

    private int hash_function(int key){
        return key % numBuckets;
    }

    /** Initialize your data structure here. */
    public DesignHashSet() {
        buckets = new LinkedList[numBuckets];
    }

    public void add(int key) {
        int i = hash_function(key);
        if(buckets[i] == null) buckets[i] = new LinkedList<>();
        if(buckets[i].indexOf(key) == -1)
            buckets[i].add(key);
    }

    public void remove(int key) {
        int i = hash_function(key);
        if(buckets[i] == null) return;
        if(buckets[i].indexOf(key) != -1) buckets[i].remove(buckets[i].indexOf(key));
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int i = hash_function(key);
        if(buckets[i] == null || buckets[i].indexOf(key) == -1) return false;
        return true;
    }
}