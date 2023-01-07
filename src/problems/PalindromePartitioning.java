package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * You are given a 0-indexed array of unique strings words.
 * A palindrome pair is a pair of integers (i, j) such that:
 *
 * 0 <= i, j < word.length,
 * i != j, and
 * words[i] + words[j] (the concatenation of the two strings) is a
 * palindrome
 * .
 * Return an array of all the palindrome pairs of words.
 */
public class PalindromePartitioning {

    /*
        There are several cases to be considered that isPalindrome(s1 + s2):
        Case1: If s1 is a blank string, then for any string that is palindrome s2, s1+s2 and s2+s1 are palindrome.
        Case 2: If s2 is the reversing string of s1, then s1+s2 and s2+s1 are palindrome.
        Case 3: If s1[0:cut] is palindrome and there exists s2 is the reversing string of s1[cut+1:] , then s2+s1 is palindrome.
        Case 4: Similar to case3. If s1[cut+1: ] is palindrome and there exists s2 is the reversing string of s1[0:cut] ,
        then s1+s2 is palindrome.
        To make the search faster, build a HashMap to store the String-idx pairs.
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if(words == null || words.length == 0){
            return res;
        }
        //build the map save the key-val pairs: String - idx
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            map.put(words[i], i);
        }

        //special cases: "" can be combine with any palindrome string
        if(map.containsKey("")){
            int blankIdx = map.get("");
            for(int i = 0; i < words.length; i++){
                if(isPalindrome(words[i])){
                    if(i == blankIdx) continue;
                    res.add(Arrays.asList(blankIdx, i));
                    res.add(Arrays.asList(i, blankIdx));
                }
            }
        }

        //find all string and reverse string pairs
        for(int i = 0; i < words.length; i++){
            String cur_r = reverseStr(words[i]);
            if(map.containsKey(cur_r) && map.get(cur_r)!=i){
                res.add(Arrays.asList(i, map.get(cur_r)));
            }
        }

        //find the pair s1, s2 that
        //case1 : s1[0:cut] is palindrome and s1[cut+1:] = reverse(s2) => (s2, s1)
        //case2 : s1[cut+1:] is palindrome and s1[0:cut] = reverse(s2) => (s1, s2)
        for(int i = 0; i < words.length; i++){
            String cur = words[i];
            for(int cut = 1; cut < cur.length(); cut++){
                if(isPalindrome(cur.substring(0, cut))){
                    String cut_r = reverseStr(cur.substring(cut));
                    if(map.containsKey(cut_r) && map.get(cut_r)!=i){
                        res.add(Arrays.asList(map.get(cut_r), i));
                    }
                }
                if(isPalindrome(cur.substring(cut))){
                    String cut_r = reverseStr(cur.substring(0, cut));
                    if(map.containsKey(cut_r) && map.get(cut_r)!=i){
                        res.add(Arrays.asList(i, map.get(cut_r)));
                    }
                }
            }
        }

        return res;
    }

    public String reverseStr(String str){
        StringBuilder sb= new StringBuilder(str);
        return sb.reverse().toString();
    }

    public boolean isPalindrome(String s){
        int i = 0;
        int j = s.length() - 1;
        while(i <= j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
