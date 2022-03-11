package binarysearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design a data structure to find the frequency of a given value in a given subarray.
 *
 * The frequency of a value in a subarray is the number of occurrences of that value in the subarray.
 *
 * Implement the RangeFreqQuery class:
 *
 * RangeFreqQuery(int[] arr) Constructs an instance of the class with the given 0-indexed integer array arr.
 * int query(int left, int right, int value) Returns the frequency of value in the subarray arr[left...right].
 * A subarray is a contiguous sequence of elements within an array. arr[left...right] denotes the subarray that contains the elements of
 * nums between indices left and right (inclusive).
 *
 * ["RangeFreqQuery", "query", "query"]
 * [[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
 * Output
 * [null, 1, 2]
 */
public class RangeFreqQuery {

    /*
        Idea is to store indexes for any element in an array and then do binary search on it.
        Since built in binary search returns insertion point for an element,
        we can find the inclusive and exclusive index by doing (-1)*(s+1) and (-1)(e+2)
     */
    Map<Integer, List<Integer>> map;
    public RangeFreqQuery(int[] arr) {
        map = new HashMap<>();
        for(int i=0;i<arr.length;i++) {
            List<Integer> list = map.get(arr[i]);
            if(list==null) {
                list = new ArrayList<>();
                map.put(arr[i], list);
            }
            list.add(i);
            map.put(arr[i], list);
        }
    }

    public int query(int left, int right, int value) {
        if(!map.containsKey(value)) return 0;
        List<Integer> list = map.get(value);
        int start = Collections.binarySearch(list, left);
        int end = Collections.binarySearch(list, right);
        if(start<0)
            start = (-1)*(start+1);
        if(end<0)
            end = (-1)*(end+2);
        return end-start+1;
    }
}
