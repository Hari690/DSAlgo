package heaps;

import java.util.PriorityQueue;

/**
 * Problem: Given an input string, return an output string such that all the lower case characters should be sorted. Indices of non-lower case characters should remain the same as in the original input string.
 * Eg. Input -> 'Test@123 Google'
 * Output -> 'Teeg@123 Gloost'
 */
public class SortLowercaseLetters {
    public String lowerCaseSort (String str){
        if (str.length() == 0) return "";

        PriorityQueue<Character> pq = new PriorityQueue<>();

        for (int i = 0; i < str.length(); i++) {
            if (Character.isLowerCase(str.charAt(i))) {
                pq.add(str.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLowerCase(str.charAt(i))) {
                sb.append(pq.poll());
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SortLowercaseLetters sortLowercaseLetters = new SortLowercaseLetters();
        System.out.println(sortLowercaseLetters.lowerCaseSort("Test@123 Google"));
    }
}
