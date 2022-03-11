package backtracking;

import java.util.ArrayList;

/**
 * You are given a string s that consists of only digits.
 *
 * Check if we can split s into two or more non-empty substrings such that the numerical values of the substrings are in descending order and the difference between numerical values of every two adjacent substrings is equal to 1.
 *
 * For example, the string s = "0090089" can be split into ["0090", "089"] with numerical values [90,89]. The values are in descending order and adjacent values differ by 1, so this way is valid.
 * Another example, the string s = "001" can be split into ["0", "01"], ["00", "1"], or ["0", "0", "1"]. However all the ways are invalid because they have numerical values [0,1], [0,1], and [0,0,1] respectively, all of which are not in descending order.
 * Return true if it is possible to split s​​​​​​ as described above, or false otherwise.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 * Example 1:
 *
 * Input: s = "1234"
 * Output: false
 * Explanation: There is no valid way to split s.
 * Example 2:
 *
 * Input: s = "050043"
 * Output: true
 * Explanation: s can be split into ["05", "004", "3"] with numerical values [5,4,3].
 * The values are in descending order with adjacent values differing by 1.
 * Example 3:
 *
 * Input: s = "9080701"
 * Output: false
 * Explanation: There is no valid way to split s.
 */
public class SplitStringDescendingConsecutiveStrings {
    public boolean splitString(String s) {
        if (s == null || s.length()<=1) return false;
        return backtrack(0, s, new ArrayList<>());
    }

    /*
        Add one element to list then keep checking if remaining part
        of string can for a number 1+ greater than string until we reach
        end of list.
     */
    public boolean backtrack(int pos, String s, ArrayList<Long> list) {
        // Base case where we reach till end of string and we have atleast 2 parts
        if (pos >= s.length()) return list.size() >= 2;

        long num = 0;
        for (int i=pos; i<s.length(); i++) {
            num = num*10 + (s.charAt(i)-'0'); // "070" i = 1 -> 0.. i = 2 -> 7.. i =3 -> 70
            if (list.size()==0 || list.get(list.size()-1) - num == 1) { // if it is first digit or difference is +1 valid

                list.add(num);  // add the number and continue to next index
                if (backtrack(i+1, s, list)) return true;
                list.remove(list.size()-1); // backtrack, done with that itteration coun't find it

            }
        }
        return false;
    }
}
