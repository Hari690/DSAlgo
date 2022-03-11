package twopointer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, reverse only all the vowels in the string and return it.
 *
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both cases.
 */
public class VowelsInString {
    public String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
        int left=0;
        int right=s.length()-1;
        char[] chars = s.toCharArray();
        while(left<right) {
            if(!vowels.contains(chars[left])) {
                left++;
            }
            else if(!vowels.contains(chars[right])) {
                right--;
            } else {
                swap(chars, left, right);
                left++;
                right--;
            }
        }
        return new String(chars);
    }

    private void swap(char[] chars, int left, int right) {
        char temp = chars[left];
        chars[left] = chars[right];
        chars[right] = temp;
    }
}
