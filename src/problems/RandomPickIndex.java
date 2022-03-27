package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Given an integer array nums with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
 * Implement the Solution class:
 * Solution(int[] nums) Initializes the object with the array nums.
 * int pick(int target) Picks a random index i from nums where nums[i] == target. If there are multiple valid i's, then each index should have an equal probability of returning.
 *
 * Example 1:
 * Input
 * ["Solution", "pick", "pick", "pick"]
 * [[[1, 2, 3, 3, 3]], [3], [1], [3]]
 * Output
 * [null, 4, 0, 2]
 *
 * Explanation
 * Solution solution = new Solution([1, 2, 3, 3, 3]);
 * solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 * solution.pick(1); // It should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 */
public class RandomPickIndex {
    Map<Integer, List<Integer>> map = null;
    Random random = null;
    public RandomPickIndex(int[] nums) {
        map = new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            int num = nums[i];
            List<Integer> list = map.get(num);
            if(list==null) {
                list = new ArrayList<>();
                map.put(num, list);
            }
            list.add(i);
        }
        random = new Random();
    }

    public int pick(int target) {
        List<Integer> list = map.get(target);
        return list.get(random.nextInt(list.size()));
    }

    int[] nums;
    Random rnd;

//    public Solution(int[] nums) {
//        this.nums = nums;
//        this.rnd = new Random();
//    }

    /*
        O(n) pick
     */
    public int pickReservoirSampling(int target) {
        int result = -1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target)
                continue;
            // this would guarantee result as at-least first one. But we keep trying for all.
            if (rnd.nextInt(++count) == 0)
                result = i;
        }

        return result;
    }
}
