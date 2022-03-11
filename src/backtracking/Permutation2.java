package backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a collection of numbers, nums, that might contain duplicates,
 * return all possible unique permutations in any order.
 */
public class Permutation2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> result = new ArrayList<>();
        List<List<Integer>> output = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();

        for(int num : nums)
            map.put(num, map.getOrDefault(num,0)+1);

        permute(nums, result, output, map);

        return output;

    }

    private void permute(int[] nums, List<Integer> result, List<List<Integer>> output, Map<Integer,Integer> map) {
        if(result.size()==nums.length)
            output.add(new ArrayList(result));

        for(Map.Entry<Integer,Integer> entry: map.entrySet()) {
            if(map.get(entry.getKey())>0) {
                map.put(entry.getKey(),entry.getValue()-1);
                result.add(entry.getKey());
                permute(nums, result, output, map);
                result.remove(result.size()-1);
                map.put(entry.getKey(),entry.getValue()+1);
            }
        }
    }

    public static void main(String[] args) {
        Permutation2 solution = new Permutation2();
        int[] nums = {1,1,2};
        solution.permuteUnique(nums);
    }
}
