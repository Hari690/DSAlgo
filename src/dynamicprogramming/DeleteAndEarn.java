package dynamicprogramming;

/**
 * You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any
 * number of times:
 *
 * Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every
 * element equal to nums[i] + 1.
 * Return the maximum number of points you can earn by applying the above operation some number of times.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DeleteAndEarn {
    /*
        Intution is sorting and checking num+1 in one direction will suffice.
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
        DeleteAndEarn deleteAndEarn = new DeleteAndEarn();
        deleteAndEarn.deleteAndEarn(nums);
    }
}
