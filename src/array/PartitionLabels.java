package array;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part.
 * Return a list of integers representing the size of these parts.
 * Input: s = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 */
public class PartitionLabels {

    public List<Integer> partitionLabels(String S) {
        int[] end_idx = new int[26];
        for (int i = 0; i < S.length(); ++i) {
            end_idx[S.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList();
        int start = 0, end = 0;
        for (int i = 0; i < S.length(); ++i) {
            end = Math.max(end, end_idx[S.charAt(i) - 'a']);
            if (i == end) { // all the characters of current partition included
                result.add(i - start + 1);
                start = i + 1;
            }
        }
        return result;
    }
}
