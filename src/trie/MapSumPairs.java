package trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a map that allows you to do the following:
 * Maps a string key to a given value.
 * Returns the sum of the values that have a key with a prefix equal to a given string.
 * Implement the MapSum class:
 *
 * MapSum() Initializes the MapSum object.
 * void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
 * int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
 *
 *
 * Example 1:
 * Input
 * ["MapSum", "insert", "sum", "insert", "sum"]
 * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * Output
 * [null, null, 3, null, 5]
 *
 * Explanation
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);
 * mapSum.sum("ap");           // return 3 (apple = 3)
 * mapSum.insert("app", 2);
 * mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 */
public class MapSumPairs {
    Trie root = null;
    Map<String,Integer> map = null;
    public MapSumPairs() {
        root = new Trie();
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        int no = 0;
        if(map.containsKey(key)) {
            no = val - map.get(key);
        } else {
            no = val;
        }
        Trie node = root;
        for (char c : key.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new Trie();
            }
            node = node.children[c - 'a'];
            node.count+=no;
        }
        map.put(key, val);
    }

    public int sum(String prefix) {
        Trie node = root;
        for (char c : prefix.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                System.out.println("hi");
                return 0;
            }
            node = node.children[c - 'a'];
        }
        return node.count;
    }

    class Trie {
        Trie[] children = new Trie[26];
        int count = 0;
    }
}
