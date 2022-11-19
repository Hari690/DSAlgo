package hashmap;

/**
 * Problem: Given an input string, return an output string such that all the lower case characters should be sorted.
 * Indices of non-lower case characters should remain the same as in the original input string.
 * Eg. Input -> 'Test@123 Google'
 * Output -> 'Teeg@123 Gloost'
 */
public class SortLowerCaseCharactersStringCountingSort {
    static String sortLowerCase(String input) {
        int count[] = new int[26];
        StringBuilder string = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (Character.isLowerCase(ch))
                count[ch - 'a']++;
        }

        for (int i = 0; i < string.length(); i++) {
            char ch = input.charAt(i);
            if (Character.isLowerCase(ch)) {
                string.setCharAt(i, findNext(ch, count));
            }
        }
        return string.toString();
    }

    static char findNext(char current, int[] count) {
        while (current < 26 && count[current] == 0) {
            current++;
        }
        count[current]--;
        return (char) ('a' + current);
    }
}
