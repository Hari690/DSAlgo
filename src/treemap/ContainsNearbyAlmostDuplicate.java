package treemap;

import java.util.TreeSet;

/**
 * Given an integer array nums and two integers k and t,
 * return true if there are two distinct indices i and j in the array such that
 * abs(nums[i] - nums[j]) <= t and abs(i - j) <= k.
 */
public class ContainsNearbyAlmostDuplicate {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        // Maintain a window using TreeSet and index where we can search based on value
        // of element in the array.
        // Since we move in forward direction we can always search backwards using ceiling and
        // nums[i]-k.
        // This is an nlogt solution.
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) return false;
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long min = set.ceiling((long)nums[i] - t);    // minimum candicate in the target range [nums[i] - t, nums[i] + t]
            if (min != null && min <= (long)nums[i] + t) return true;    // found duplicates
            set.add((long)nums[i]);
            if (i >= k) set.remove((long)nums[i - k]);
        }
        return false;
    }
}
