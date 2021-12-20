package array;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, return the number of unique palindromes of length three that are a subsequence of s.
 *
 * Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.
 *
 * A palindrome is a string that reads the same forwards and backwards.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without
 * changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 */
public class ThreeLengthPalindrome {
    public int countPalindromicSubsequence(String s) {
        int[] lmap = new int[26];
        int[] rmap = new int[26];
        Set<String> set = new HashSet<>();

        for(int i=1;i<s.length();i++) {
            rmap[s.charAt(i)-'a']+=1;
        }

        int count=0;
        lmap[s.charAt(0)-'a']=1;
        for(int i=1;i<s.length()-1;i++) {
            rmap[s.charAt(i)-'a']-=1;
            for(int no=0;no<26;no++) {
                char c = (char)(no+'a');
                if(rmap[no] > 0 && lmap[no] > 0) {
                    set.add(c +String.valueOf(s.charAt(i))+ c);
                }
            }
            lmap[s.charAt(i)-'a']+=1;
        }
        return set.size();
    }
}
