package problems;

import java.util.HashMap;
import java.util.Map;

/**
 * In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.*
 *
 * Example 1:
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 *
 * Example 2:
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 *
 * Example 3:
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 */
public class VerifyAlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {

        Map<Character,Integer> orderMap = new HashMap<>();

        int index = 0;
        for ( Character c : order.toCharArray()) {
            orderMap.put(c, index++);
        }

        int i = 0;
        while ((i+1) < words.length) {
            int j=0;
            while(j<words[i].length() && j<words[i+1].length()) {
                if ( orderMap.get(words[i].charAt(j)) < orderMap.get(words[i+1].charAt(j))) {
                    break;
                } else if ( orderMap.get(words[i].charAt(j)) == orderMap.get(words[i+1].charAt(j))) {
                    j++;
                } else {
                    return false;
                }
            }
            if( j < words[i].length() && j==words[i+1].length()) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static void main(String[] args) {

        String words[] = {"fxasxpc","dfbdrifhp","nwzgs","cmwqriv","ebulyfyve","miracx","sxckdwzv","dtijzluhts","wwbmnge","qmjwymmyox"};
        String order = "zkgwaverfimqxbnctdplsjyohu";
        System.out.println(new VerifyAlienDictionary().isAlienSorted(words, order));
    }
}
