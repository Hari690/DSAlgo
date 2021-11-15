package array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a 0-indexed integer array nums, return the number of distinct quadruplets (a, b, c, d) such that:
 *
 * nums[a] + nums[b] + nums[c] == nums[d], and
 * a < b < c < d
 */
public class CountQuarduplets {
    public int countQuadruplets(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();

        int n = nums.length;
        int total = 0;
        for(int i=n-1;i>=0;i--) {
            map.put(nums[i], map.getOrDefault(nums[i],0)+1);
            for(int j=i-1;j>=0;j--) {
                for(int k=j-1;k>=0;k--) {
                    if(map.containsKey(nums[i]+nums[j]+nums[k]))
                        total+=map.get(nums[i]+nums[j]+nums[k]);
                }
            }
        }
        return total;
    }
}
