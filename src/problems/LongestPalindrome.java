package problems;

import java.util.HashSet;

/*
Given a string s, return the longest palindromic substring in s.
 */
class LongestPalindrome {
    private int lo, maxLen;

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;

        // pass through all letters and check if its can be extended.
        for (int i = 0; i < len-maxLen/2; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i+1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }

    public int longestPalindromeLength(String s) {
        if(s==null || s.length()==0) return 0;
        // adding and removing from HashSet and check if there's anything left in the HashSet
        HashSet<Character> hs = new HashSet<>();
        int count = 0;
        for(int i=0; i<s.length(); i++){
            if(hs.contains(s.charAt(i))){
                hs.remove(s.charAt(i));
                count++;
            }else{
                hs.add(s.charAt(i));
            }
        }
        if(!hs.isEmpty()) return count*2+1;
        return count*2;
    }

    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();

        longestPalindrome.longestPalindrome("babad");
    }

}
