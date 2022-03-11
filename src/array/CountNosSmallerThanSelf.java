package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is
 * the number of smaller elements to the right of nums[i].
 */
public class CountNosSmallerThanSelf {

    public List<Integer> countSmaller(int[] nums) {
        Integer[] counts = new Integer[nums.length];

        if(nums == null) {
            return Arrays.asList(counts);
        }

        if(nums.length == 1) {
            counts[0] = 0;
            return Arrays.asList(counts);
        }
        NavigableMap<Integer,Integer> m= new TreeMap();
        for(int i = nums.length-1; i >= 0 ; i--) {
            if(!m.containsKey(nums[i])) {
                m.put(nums[i], 1);
            } else {
                m.put(nums[i], m.get(nums[i]) + 1);
            }

            Map<Integer, Integer> headMap = m.headMap(nums[i]);
            int value = 0;
            for(Map.Entry<Integer, Integer> e : headMap.entrySet()) {
                value += e.getValue();

            }
            counts[i] = value;
        }
        return Arrays.asList(counts);
    }
}
