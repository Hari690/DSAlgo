package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
 * Example:
 * Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
 * Output: ["2", "4->49", "51->74", "76->99"]
 * The high level idea:
 * Check each num in nums[]. Initialize another variable next = lower.
 * If nums[i]< next: we jump to next num to check if it’s in range.
 * If nums[i]== next: it means we find the first num in range. We increment the next target by one.
 * If nums[i] > next: Add the missing range [next, nums[i] — 1]. Update the next value to nums[i] + 1.
 * After we finished the above loop, we need to double check the final next value. If next ≤ upper, we still have a missing range [next, upper] to add.
 * We create a separate function getRange(int unm1, int num2) to deal with two cases: num1 == num2 or num1 < num2
 */
public class FindMissingRanges {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<String>();
        int next = lower;
        for (int i = 0; i < nums.length; i++) {
            // 1. We don't need to add [Integer.MAX_VALUE, ...] to result
            if(lower == Integer.MAX_VALUE) return res;
            if (nums[i] < next) {
                continue;
            }
            if (nums[i] == next) {
                next++;
                continue;
            }
            res.add(getRange(next, nums[i] - 1));
            // 2. We don't need to proceed after we have process Integer.MAX_VALUE in array
            if(nums[i] == Integer.MAX_VALUE) return res;
            next = nums[i] + 1;
        }

        if (next <= upper) {
            res.add(getRange(next, upper));
        }
        return res;
    }

    public String getRange(int n1, int n2) {
        return n1 == n2 ? String.valueOf(n1) : String.format("%d->%d" , n1, n2);
    }
}
