package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
 *
 * Return the maximum possible length of s.
 *
 * Example 1:
 *
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
 * Maximum length is 4.
 */
public class MaxLengthStringWithConCharacters {

    int maxSize = 0;

    public int maxLength(List<String> arr) {
        String word = "";
        return backtrack(arr, 0, word);
//        recurse( arr,0, word);
//        return maxSize;
    }

    private void recurse(List<String> input, int index, String temp) {
        if(temp.length() > maxSize) {
            maxSize = temp.length();
        }
        for ( int i=index; i < input.size(); i++) {
            if( noDuplicates(temp+input.get(i))) {
                recurse(input, i+1, temp+input.get(i));
            }
        }
    }

    /*
        Using include and exclude technique to explore all subsets.
     */
    private int backtrack(List<String> input, int index, String temp) {
        if(temp.length() > maxSize) {
            maxSize = temp.length();
        }
        if(index==input.size())
            return temp.length();
        int maxLength = 0;
        if(noDuplicates(temp+input.get(index))) {
            maxLength = Math.max(maxLength, backtrack(input, index+1, temp+input.get(index)));
        }
        return Math.max(maxLength, backtrack(input, index+1, temp));
    }

    private boolean noDuplicates(String s) {
        Set<Character> set = new HashSet<>();
        for ( char c : s.toCharArray()) {
            if( set.contains(c)) {
                return false;
            }
            set.add(c);
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("cha");
        list.add("r");
        list.add("act");
        list.add("ers");

        MaxLengthStringWithConCharacters main = new MaxLengthStringWithConCharacters();
        System.out.println(main.maxLength(list));
    }

}
