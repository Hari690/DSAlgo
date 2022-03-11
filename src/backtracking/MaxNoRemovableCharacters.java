package backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given two strings s and p where p is a subsequence of s. You are also given a distinct 0-indexed integer array removable
 * containing a subset of indices of s (s is also 0-indexed).
 *
 * You want to choose an integer k (0 <= k <= removable.length) such that, after removing k characters from s using the first k indices
 * in removable, p is still a subsequence of s. More formally, you will mark the character at s[removable[i]] for each 0 <= i < k, then
 * remove all marked characters and check if p is still a subsequence.
 *
 * Return the maximum k you can choose such that p is still a subsequence of s after the removals.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without
 * changing the relative order of the remaining characters.
 *
 * Input: s = "abcacb", p = "ab", removable = [3,1,0]
 * Output: 2
 * Explanation: After removing the characters at indices 3 and 1, "abcacb" becomes "accb".
 * "ab" is a subsequence of "accb".
 * If we remove the characters at indices 3, 1, and 0, "abcacb" becomes "ccb", and "ab" is no longer a subsequence.
 * Hence, the maximum k is 2.
 */
public class MaxNoRemovableCharacters {

    public int maximumRemovals(String s, String p, int[] removable) {
        return check(s,p,removable);
    }

    private int check(String s, String p,int[] removable) {
        int max = 0;
        int left=0;
        int right=removable.length-1;

        while(left<=right) {
            int mid = (right+left)/2;
            if(checkSubseq(s,p,mid, removable)) {
                max = Math.max(max, mid+1);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return max;
    }

    private boolean checkSubseq(String s, String p, int index, int[] removable) {
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<=index;i++) {
            set.add(removable[i]);
        }
        int i=0;
        int j=0;
        while(i<s.length() && j<p.length()) {
            if(set.contains(i))
                i++;
            else if(s.charAt(i)!=p.charAt(j))
                i++;
            else {
                i++;
                j++;
            }
        }
        return j==p.length();
    }

    public static void main(String[] args) {
        MaxNoRemovableCharacters solution = new MaxNoRemovableCharacters();

        int[] removals = {0,1,2,3,4};
        solution.maximumRemovals("abcab","abc",removals);
    }
}
