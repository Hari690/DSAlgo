package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> output = new HashSet<>();
        Arrays.sort(nums);
        int n = nums.length;
        for(int i=0;i<n-3;i++) {
            for(int j=i+1;j<n-2;j++) {
                int left = j+1, right=n-1;
                while(left<right) {
                    int sum = nums[i]+nums[j]+nums[left]+nums[right];
                    if(sum==target) {
                        output.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        left++;
                        right--;
                    }
                    if(sum<target) {
                        left++;
                    }
                    if(sum>target) {
                        right--;
                    }
                }
            }
        }
        List<List<Integer>> finalOutput = new ArrayList<>();
        finalOutput.addAll(output);
        return finalOutput;
    }

    public static void main(String[] args) {
        int[] nums = {1,0,-1,0,-2,2};
        int target = 0;
        FourSum fourSum = new FourSum();
        fourSum.fourSum(nums, target);
    }

}
