package linkedlist;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 *
 * Implement the LFUCache class:
 *
 * LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 * int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 * void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
 * To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.
 * The functions get and put must each run in O(1) average time complexity.
 *
 * Example 1:
 * Input
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 *
 * Explanation
 * // cnt(x) = the use counter for key x
 * // cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
 * LFUCache lfu = new LFUCache(2);
 * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lfu.get(1);      // return 1
 *                  // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
 *                  // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lfu.get(2);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
 *                  // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lfu.get(1);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lfu.get(4);      // return 4
 *                  // cache=[4,3], cnt(4)=2, cnt(3)=3
 *
 */
class LFUCache {

    /*
        LRUCache inside map with frequency as key
     */
    private int minFrequency = Integer.MAX_VALUE;
    private int capacity = 0;
    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    private Map<Integer, Node> cache = new HashMap<>();
    // Use LinkedHashMap as value here to track the insertion (least-recently-used) order.
    private Map<Integer, LinkedHashMap<Integer, Node>> frequencyMap = new HashMap<>();

    public int get(int key) {
        Node node = cache.get(key);
        if(node == null) return -1;

        updateNode(node);
        return node.val;
    }

    public void put(int key, int value) {
        if(capacity <= 0) return;
        Node node = cache.get(key);
        if(node == null){
            node = new Node(key, value, 0);
        }
        node.val = value;
        updateNode(node);
    }
    private void updateNode(Node node){
        int freq = node.frequency;
        node.frequency++;
        // It's a new node.
        if(freq == 0) {
            if(cache.size() == capacity){
                Map<Integer, Node> nodes = frequencyMap.get(minFrequency);
                Iterator<Integer> it = nodes.keySet().iterator();
                int k = it.next();
                // Remove the least frequently and recently used key-value.
                it.remove();
                if(nodes.isEmpty()) frequencyMap.remove(minFrequency);
                cache.remove(k);
            }
            minFrequency = 1;
            cache.put(node.key, node);
        } else {
            Map<Integer, Node> nodes = frequencyMap.get(freq);
            nodes.remove(node.key);
            if(nodes.isEmpty()){
                frequencyMap.remove(freq);
                if(freq == minFrequency){
                    minFrequency++;
                }
            }
        }
        frequencyMap.compute(node.frequency, (k, v) ->{
            if(v == null){
                v = new LinkedHashMap<>();
            }
            // Re-insert the node into the LinkedHashMap to track the least-recently-used order.
            v.remove(node.key);
            v.put(node.key, node);
            return v;
        });
    }
    static class Node{
        private int key;
        private int val;
        private int frequency;
        Node(int key, int val, int frequency){
            this.key = key;
            this.val = val;
            this.frequency = frequency;
        }
    }
}