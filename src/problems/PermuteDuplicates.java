package problems;

import java.util.ArrayList;
import java.util.List;

public class PermuteDuplicates {

    public static void main(String[] args) {
        int[] nums = {1,1,2};
        System.out.println(new PermuteDuplicates().permuteUnique(nums));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {

        List<Integer> result = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        List<List<Integer>> output = new ArrayList<>();

        permute(nums, result, output, indexes);

        return output;

    }

    private void permute(int[] nums, List<Integer> result, List<List<Integer>> output, List<Integer> indexes) {
        if(result.size()==nums.length) {
            boolean contains = false;
            for(List<Integer> list :output) {
                int i=0;
                while((i<nums.length) && result.get(i)==list.get(i)) {
                    i++;
                    if(i==nums.length) {
                        contains = true;
                    }
                }
            }

            if(!contains) {
                output.add(new ArrayList<>(result));
            }
        }
        for(int i=0; i<nums.length; i++) {
            if(indexes.contains(i)) {
                continue;
            }
            result.add(nums[i]);
            indexes.add(i);
            permute(nums, result, output, indexes);
            result.remove(result.size()-1);
            indexes.remove(indexes.size()-1);
        }
    }
}
