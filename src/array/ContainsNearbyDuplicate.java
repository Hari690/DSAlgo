package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] ==
 * nums[j] and abs(i - j) <= k.
 */
public class ContainsNearbyDuplicate {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            List<Integer> list = map.get(nums[i]);
            if(list==null) {
                list = new ArrayList<>();
                map.put(nums[i], list);
            }
            else {
                if(Math.abs(i-list.get(list.size()-1))<=k) {
                    return true;
                }
            }
            list.add(i);
        }
        return false;
    }
}
