package backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of strings nums containing n unique binary strings each of length n,
 * return a binary string of length n that does not appear in nums. If there are multiple answers, you may return any of them.
 *
 * Example 1:
 *
 * Input: nums = ["01","10"]
 * Output: "11"
 * Explanation: "11" does not appear in nums. "00" would also be correct.
 * Example 2:
 *
 * Input: nums = ["00","01"]
 * Output: "11"
 * Explanation: "11" does not appear in nums. "10" would also be correct.
 * Example 3:
 *
 * Input: nums = ["111","011","001"]
 * Output: "101"
 * Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.
 */
public class FindUniqueBinaryString {
    public String findDifferentBinaryString(String[] nums) {
        Set<String> set = new HashSet<>();

        for(String num : nums) {
            set.add(num);
        }

        int length = nums[0].length();
        return generate(set, "", length);
    }

    private String generate(Set<String> set, String word, int length) {
        if(word.length()==length) {
            if(!set.contains(word))
                return word;
            return null;
        }
        String word1 = generate(set, word+"0", length);
        if(word1!=null)
            return word1;
        String word2 = generate(set, word+"1", length);
        return (word2==null)?null:word2;
    }
}
