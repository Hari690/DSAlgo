package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A sequence of numbers is called an arithmetic progression if the difference between any two consecutive elements is the same.
 * Given an array of numbers arr, return true if the array can be rearranged to form an arithmetic progression. Otherwise, return false.
 *
 * Example 1:
 * Input: arr = [3,5,1]
 * Output: true
 * Explanation: We can reorder the elements as [1,3,5] or [5,3,1] with differences 2 and -2 respectively, between each consecutive elements.
 *
 * Example 2:
 * Input: arr = [1,2,4]
 * Output: false
 * Explanation: There is no way to reorder the elements to obtain an arithmetic progression.
 */
public class CanMakeAPFromSequence {
    public class _1502_Can_Make_Arithmetic_Progression_From_Sequence {
        class Solution {
            public boolean canMakeArithmeticProgression(int[] arr) {
                Arrays.sort(arr);
                int d = arr[1] - arr[0];
                for (int i = 2; i < arr.length; i++)
                    if (arr[i] - arr[i - 1] != d)
                        return false;
                return true;
            }
        }

        class Solution2 {
            public boolean canMakeArithmeticProgression(int[] arr) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                Set<Integer> set = new HashSet();

                for (int num : arr) {
                    min = Math.min(min, num);
                    max = Math.max(max, num);
                    set.add(num);
                }
                int n = arr.length;
                int d = max - min;
                if (d % (n - 1) != 0)
                    return false;
                d /= (n - 1);
                int i = 0;
                while (i < n) {
                    if (!set.contains(min))
                        return false;
                    min += d;
                    i++;
                }

                return true;
            }
        }
    }
}
