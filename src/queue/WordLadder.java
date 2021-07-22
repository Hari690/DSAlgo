package queue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2
 * -> ... -> sk such that:
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence
 * from beginWord to endWord, or 0 if no such sequence exists.
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        HashSet<String> wordSet = new HashSet<>(wordList);
        wordSet.remove(beginWord); // the same as marking visited
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            step++;
            // since we are calculating next element for all elements in queue
            // we will end up with the shortest path between source and destination
            while (size-- > 0) {
                String str = q.poll();
                if (str.equals(endWord)) {
                    return step; // found shortest transformation path
                }
                for (int i = 0; i < str.length(); i++) {
                    char[] chars = str.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) { // try to change 1 character of `str`
                        chars[i] = c;
                        String newStr = new String(chars);
                        if (wordSet.contains(newStr)) {
                            q.offer(newStr);
                            wordSet.remove(newStr); // the same as marking visited
                        }
                    }
                }
            }
        }
        return 0; // no such transformation sequence.
    }
}
