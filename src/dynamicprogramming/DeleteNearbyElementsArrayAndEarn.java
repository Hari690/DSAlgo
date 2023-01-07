package dynamicprogramming;

/**
 * You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any
 * number of times:
 *
 * Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every
 * element equal to nums[i] + 1.
 * Return the maximum number of points you can earn by applying the above operation some number of times.
 *
 * Example 1:
 * Input: nums = [3,4,2]
 * Output: 6
 * Explanation: You can perform the following operations:
 * - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
 * - Delete 2 to earn 2 points. nums = [].
 * You earn a total of 6 points.
 * Example 2:
 *
 * Input: nums = [2,2,3,3,3,4]
 * Output: 9
 * Explanation: You can perform the following operations:
 * - Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
 * - Delete a 3 again to earn 3 points. nums = [3].
 * - Delete a 3 once more to earn 3 points. nums = [].
 * You earn a total of 9 points.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DeleteNearbyElementsArrayAndEarn {
    /*
        Intution is sorting and checking num+1 in one direction will suffice similar to house robber.
        We need to check 2 cases i.e including current no minus no after it and checking by moving to the next no.
     */
    public int topDown(int[] a, int i, int[] dp){
        if(i==a.length)
            return 0;
        if(dp[i]!=0)
            return dp[i];
        int elem=a[i];
        int sum=0;
        int nextIndex=i;
        //summing the duplicates of the element
        while(nextIndex<a.length &&  a[nextIndex]==elem){
            nextIndex++;
            sum+=elem;
        }
        //skipping the elem+1 elements
        while(nextIndex<a.length && a[nextIndex]==(elem+1)){
            nextIndex++;
        }
        // either you pick or you skip ( don't delete this no ) to reach the nos which you skipped.
        int ans= Math.max(sum+topDown(a, nextIndex, dp), topDown(a, i+1, dp));
        dp[i]=ans;
        return ans;
    }

    public int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        return topDown(nums, 0, dp);
    }


    public int deleteAndEarnBottomUp(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();

        List<Integer> list = new ArrayList<>();
        for(int num : nums) {
            if(!map.containsKey(num))
                list.add(num);
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        Collections.sort(list);

        int check1=0;
        int check2=0;

        for(int i=0;i<list.size();i++) {
            // this case is when consecutive items are one after the other so we cannot take previous element as part of this move.
            if(i!=0 && list.get(i)==list.get(i-1)+1) {
                int temp = check2;
                check2 = Math.max(check2,check1+map.get(list.get(i))*list.get(i));
                check1 = temp;
            } else {
                int temp = check2;
                check2 = check2+map.get(list.get(i))*list.get(i);
                check1 = temp;
            }
        }
        return check2;
    }

    public static void main(String[] args) {
        int[] nums = {3,4,2};
        DeleteNearbyElementsArrayAndEarn deleteAndEarn = new DeleteNearbyElementsArrayAndEarn();
        deleteAndEarn.deleteAndEarn(nums);
    }
}
