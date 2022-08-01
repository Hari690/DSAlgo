package hashmap;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given two string arrays words1 and words2.
 * A string b is a subset of string a if every letter in b occurs in a including multiplicity.
 *
 * For example, "wrr" is a subset of "warrior" but is not a subset of "world".
 * A string a from words1 is universal if for every string b in words2, b is a subset of a.
 * Return an array of all the universal strings in words1. You may return the answer in any order.
 *
 * Example 1:
 * Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
 * Output: ["facebook","google","leetcode"]
 * Example 2:
 * Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["l","e"]
 * Output: ["apple","google","leetcode"]
 */
public class CheckWordsSubsetOf {
    public List<String> wordSubsets(String[] A, String[] B) {
        List<String> result=new ArrayList<>();
        int[] target=new int[26];

        for(String word:B){
            int[] temp=new int[26];
            for(char ch:word.toCharArray()){
                temp[ch-'a']++;
                target[ch-'a']=Math.max(target[ch-'a'],temp[ch-'a']);
            }
        }

        for(String word:A){
            int[] source=new int[26];
            for(char ch:word.toCharArray()){
                source[ch-'a']++;
            }
            if(subset(source,target)){
                result.add(word);
            }
        }

        return result;
    }

    private boolean subset(int[] parent,int[] child){
        for(int i=0;i<26;i++){
            if(parent[i]<child[i]) return false;
        }
        return true;
    }
}
