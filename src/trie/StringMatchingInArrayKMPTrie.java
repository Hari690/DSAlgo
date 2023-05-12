package trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of string words. Return all strings in words which is substring of another word in any order.
 * String words[i] is substring of words[j], if can be obtained removing some characters to left and/or right side of words[j].
 *
 *
 * Example 1:
 * Input: words = ["mass","as","hero","superhero"]
 * Output: ["as","hero"]
 * Explanation: "as" is substring of "mass" and "hero" is substring of "superhero".
 * ["hero","as"] is also a valid answer.
 * Example 2:
 * Input: words = ["leetcode","et","code"]
 * Output: ["et","code"]
 * Explanation: "et", "code" are substring of "leetcode".
 * Example 3:
 *
 * Input: words = ["blue","green","bu"]
 * Output: []
 *
 * O(nlogn + n*w^2) S.C - O(n*w^2)
 */
public class StringMatchingInArrayKMPTrie {
    class Trie{
        class TrieNode{

            char data;
            TrieNode[] children;
            boolean isTerminal;

            public TrieNode(char data, boolean isTerminal){
                this.data = data;
                this.children = new TrieNode[26];
                this.isTerminal = isTerminal;
            }
        }

        public TrieNode root;

        public Trie(){
            root = new TrieNode('\0', false);
        }

        public void addWord(String word){

            int len = word.length();
            LinkedList<Character> list = new LinkedList<>();
            for(int i = len - 1; i >= 0; i--){
                list.addFirst(word.charAt(i));
                addWordHelper(list.toString());
            }
        }

        public void addWordHelper(String word){

            TrieNode curr = root;
            for(char ch : word.toCharArray()){
                if(ch - 'a' < 0)
                    continue;
                TrieNode next = curr.children[ch - 'a'];
                if(next == null){
                    next = new TrieNode(ch, false);
                    curr.children[ch - 'a'] = next;
                }
                curr = next;
            }

            curr.isTerminal = true;
        }

        public boolean searchPrefix(String prefix){

            TrieNode curr = root;
            for(char ch : prefix.toCharArray()){
                TrieNode next = curr.children[ch - 'a'];
                if(next == null)
                    return false;
                curr = next;
            }

            return true;
        }
    }

    public List<String> stringMatchingTrie(String[] words) {
        int len = words.length;
        List<String> ans = new ArrayList<>();

        Arrays.sort(words, new Comparator<String>(){
            public int compare(String o1, String o2){
                return o1.length() - o2.length();
            }
        });

        Trie trie = new Trie();

        for(int i = len - 1; i >= 0; i--){
            if(trie.searchPrefix(words[i]))
                ans.add(words[i]);
            trie.addWord(words[i]);
        }

        return ans;
    }


    public List<String> stringMatchingKMP(String[] words) {
        List<String> list = new ArrayList<>();

        for(int i=0; i<words.length; i++) {
            for(int j=0; j<words.length; j++) {
                if(i != j  && words[i].length() >= words[j].length()
                        && !list.contains(words[j])){
                    if(kmpMatch(words[i], words[j])) {
                        list.add(words[j]);
                    }
                }
            }
        }
        return list;
    }

    // longest prefix(beginning part) which is same as a suffix
    // Examples of lps[] construction:
    //For the pattern “AAAA”,
    //lps[] is [0, 1, 2, 3]
    //
    //For the pattern “ABCDE”,
    //lps[] is [0, 0, 0, 0, 0]
    //
    //For the pattern “AABAACAABAA”,
    //lps[] is [0, 1, 0, 1, 2, 0, 1, 2, 3, 4, 5]
    //
    //For the pattern “AAACAAAAAC”,
    //lps[] is [0, 1, 2, 0, 1, 2, 3, 3, 3, 4]
    //
    //For the pattern “AAABAAA”,
    //lps[] is [0, 1, 2, 0, 1, 2, 3]
    //
    // we already know some of the characters in the text of the next window.
    // We take advantage of this information to avoid matching the characters that we know will anyway match.
    // txt = "AAAAABAAABA"
    // pat = "AAAA"
    // for i = 4 we reset j = 3 so we don't have to check first 3 chars again.
    public int[] computeLps(String p) {
        int[] lps = new int[p.length()];
        // length of the previous longest prefix suffix
        int len=0;
        for(int i=1; i<p.length();) {
            if(p.charAt(len) == p.charAt(i)) {
                lps[i] = len+1;
                i++; len++;
            } else {
                if(len != 0) {
                    len = lps[len-1];
                } else {
                    i++;
                }
            }
        }
        return lps;
    }

    public boolean kmpMatch(String s, String p) {
        int[] lps = computeLps(p);
        int i=0, j=0;
        while(i<s.length() && j<p.length()) {
            if(s.charAt(i) == p.charAt(j)) {
                i++; j++;
            } else {
                if(j != 0) {
                    j = lps[j-1];
                } else {
                    i++;
                }
            }
//            normal kmp to keep searching
//            if (j == p.length()) {
//                j = lps[j-1];
//            }
        }
        return j == p.length();
    }


    public List<String> stringMatching(String[] words) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < words.length - 1; i++) {
            String currWord = words[i];
            for (int j = i + 1; j < words.length; j++) {
                String nextWord = words[j];
                if (currWord.contains(nextWord)) {
                    set.add(nextWord);
                }

                if (nextWord.contains(currWord)) {
                    set.add(currWord);
                }
            }
        }

        List<String> res = new ArrayList<>(set); // convert hashset to list
        return res;
    }

    /*
    Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
    Example 1:

    Input: haystack = "sadbutsad", needle = "sad"
    Output: 0
    Explanation: "sad" occurs at index 0 and 6.
    The first occurrence is at index 0, so we return 0.
    Example 2:

    Input: haystack = "leetcode", needle = "leeto"
    Output: -1
    Explanation: "leeto" did not occur in "leetcode", so we return -1.
     */
    public int firstOccurenceOfPInS(String s, String p) {

        int[] lps = computeLps(p);
        int i=0, j=0;
        while(i<s.length() && j<p.length()) {
            if(s.charAt(i) == p.charAt(j)) {
                i++; j++;
            } else {
                if(j != 0) {
                    j = lps[j-1];
                } else {
                    i++;
                }
            }
        }

        if( j == p.length()) {
            return i-p.length();
        } else {
            return -1;
        }

    }


    public static void main(String[] args) {
        StringMatchingInArrayKMPTrie stringMatchingInArrayKMPTrie = new StringMatchingInArrayKMPTrie();
        String[] words = {"AAAA","AAAAABAAABA"};
        stringMatchingInArrayKMPTrie.stringMatchingKMP(words);
    }
}