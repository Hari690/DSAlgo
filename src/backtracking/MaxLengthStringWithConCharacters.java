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
        recurse( arr,0, word);
        return maxSize;
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
        list.add("un");
        list.add("iq");
        list.add("ue");

        MaxLengthStringWithConCharacters main = new MaxLengthStringWithConCharacters();
        System.out.println(main.maxLength(list));
    }

}
