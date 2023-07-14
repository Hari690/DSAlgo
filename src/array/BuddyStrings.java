package array;

/**
 * Given two strings s and goal, return true if you can swap two letters in s so the result is equal to goal, otherwise, return false.
 * Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at s[i] and s[j].
 * For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
 *
 * Example 1:
 * Input: s = "ab", goal = "ba"
 * Output: true
 * Explanation: You can swap s[0] = 'a' and s[1] = 'b' to get "ba", which is equal to goal.
 *
 * Example 2:
 * Input: s = "ab", goal = "ab"
 * Output: false
 * Explanation: The only letters you can swap are s[0] = 'a' and s[1] = 'b', which results in "ba" != goal.
 *
 * Example 3:
 * Input: s = "aa", goal = "aa"
 * Output: true
 * Explanation: You can swap s[0] = 'a' and s[1] = 'a' to get "aa", which is equal to goal.
 *
 *
 * Constraints:
 * 1 <= s.length, goal.length <= 2 * 104
 * s and goal consist of lowercase letters.
 */
public class BuddyStrings {
    public boolean buddyStrings(String s, String g) {
        if(s.length()!=g.length()) {
            return false;
        }

        int diff = 0;
        Integer first = null, second = null;
        int[] map = new int[26];
        for(int i=0;i<s.length();i++) {
            if (s.charAt(i)!=g.charAt(i)) {
                diff++;
                if (diff>2)
                    return false;
                if(diff==1) {
                    first = i;
                }
                if(diff==2) {
                    second = i;
                }
            }
            map[s.charAt(i)-'a']++;
        }

        if(first!=null && second!=null) {
            char[] arr = s.toCharArray();
            char temp = arr[first];
            arr[first] = arr[second];
            arr[second] = temp;
            return new String(arr).equals(g);
        } else if (first==null && second==null) {
            // if they have same characters as goal swap is possible only if we have at-least 2 characters which are the same.
            // else order will change so return false.
            for(int v : map) {
                if(v>1)
                    return true;
            }
            return false;
        }

        return false;
    }
}
