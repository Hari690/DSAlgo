package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] +
 * nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 */
/*
    Sorting and 2 pointers to search the array to yield the nos we want.
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nos = {1,3,4,5,6};
        new ThreeSum().threeSum(nos);
    }

    /* Sorting array and traversing in a loop and walking in both direction in reminder of array
        and check if sum an be obtained. O(n^2) solution.
     */
    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i-1])) {
                int lo = i+1, hi = num.length-1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        // duplicates
                        while (lo < hi && num[lo] == num[lo+1]) lo++;
                        while (lo < hi && num[hi] == num[hi-1]) hi--;
                        lo++; hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
                }
            }
        }

        Object[] objects = new String[3];
        return res;
    }

    public List<List<Integer>> threeSumMap2(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        Map<Integer, Integer> lookup = new HashMap<>(nums.length);

        // another option is to store no of occurences and check
        // the outer loop values are different
        for (int i = 0; i < nums.length; i++) {
            lookup.put(-nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int key = nums[i] + nums[j];
                if (lookup.containsKey(key)) {
                    int k = lookup.get(key);
                    if (k != i && k != j) {
                        List<Integer> tuple = Arrays.asList(nums[i], nums[j], nums[k]);
                        Collections.sort(tuple);
                        result.add(tuple);
                    }
                }
            }
        }
        return new ArrayList(result);
    }

    /*
        Not very good since we are sorting the array in the beginning.
     */
    public List<List<Integer>> threeSumMap(int[] nums) {
        List<List<Integer>> list = new LinkedList<>();
        Arrays.sort(nums);
        for(int i = 0;i< nums.length-2;i++){
            if(i > 0 && nums[i] == nums[i-1]) continue;                     //corner case {0,0,0,0}
            twoSum(list, nums, i+1,nums.length-1,-nums[i]);
        }
        return list;
    }
    private void twoSum(List<List<Integer>> list, int[] nums,int low,int high,int target){
        if(low > high) return;
        HashSet<Integer> set = new HashSet<>();
        for(int i = low;i<= high;i++){
            if(set.contains(target - nums[i])) {
                list.add(Arrays.asList(-target,nums[i],target-nums[i]));
                while(i+1 <= high && nums[i] == nums[i+1]) i++;             //corner case {0,0,0,0}
            }else{
                set.add(nums[i]);
            }
        }
    }
}
