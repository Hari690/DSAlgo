package problems;

import java.util.ArrayList;
import java.util.List;

public class PermutSequence {

    public static void main(String[] args) {
        new PermutSequence().getPermutation(3,3);
    }

    public String getPermutation(int n, int k) {

        List<Integer> nums = new ArrayList();
        for(int i=1;i<=n;i++) {
            nums.add(i);
        }
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> output = new ArrayList<>();
        helperFunction(output, temp, nums.stream().mapToInt(i->i).toArray(), k);
        temp = output.get(output.size()-1);
        StringBuffer sb = new StringBuffer();
        temp.stream().forEach(s->sb.append(s));
        return sb.toString();
    }

    public static void helperFunction(List<List<Integer>> result, List<Integer> current, int[] nums, int k) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length && result.size() < k; i++) {
            if (current.contains(nums[i])) {
                continue;
            }
            current.add(nums[i]);
            helperFunction(result, current, nums, k);
            current.remove(current.size() - 1);
        }
    }
}
