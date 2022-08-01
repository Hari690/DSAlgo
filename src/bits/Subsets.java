package bits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> subsets = new Subsets().subsets(nums);
        System.out.println(subsets);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        for(int i=1;i<=Math.pow(2,nums.length);i++) { // 8
            List<Integer> result = new ArrayList<>();
            for(int k=0;k<nums.length;k++) { //3
                if((i&(1<<k))>0) { // check if bit in k'th position is set
                    result.add(nums[k]);
                }
            }
            output.add(result);
        }
        return output;
    }

    public List<List<Integer>> subsetsBacktracking(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
