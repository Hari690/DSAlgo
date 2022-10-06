package queue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
 *
 * Example 1:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * Explanation: There are 2 shortest transformation sequences:
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 * Example 2:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: []
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 */
public class WordLadder2 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Queue<List<String>> q = new LinkedList<>();
        List<String> firstList = new ArrayList();
        firstList.add(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        q.offer(firstList);
        HashSet<String> wordSet = new HashSet<>(wordList);
        wordSet.remove(beginWord); // the same as marking visited
        boolean isFound = false;
        while (!q.isEmpty()) {
            int size = q.size();
            // since we are calculating next element for all elements in queue
            // we will end up with the shortest path between source and destination
            List<String> reached = new ArrayList<>();
            while (size-- > 0) {
                List<String> item = q.poll();
                String s =  item.get(item.size()-1);
                for (int i = 0; i < s.length(); i++) {
                    char[] chars = s.toCharArray();
                    // Max word length is 5 so complexity is O(26*5)
                    for (char c = 'a'; c <= 'z'; c++) { // try to change 1 character of `str`
                        chars[i] = c;
                        String newStr = new String(chars);
                        if (!visited.contains(newStr) && wordSet.contains(newStr)) {
                            if ((newStr).equals(endWord)) {
                                List<String> newList = new LinkedList<>(item);
                                newList.add(newStr);
                                result.add(newList);
                                isFound = true;
                            } else {
                                List<String> newList = new LinkedList<>(item);
                                newList.add(newStr);
                                q.offer(newList);
                                reached.add(newStr);
                            }
                        }
                    }
                }
            }
            // add it only after level completion so we explore all paths in the level.
            visited.addAll(reached);
            if (isFound)
                return result;
        }
        return result;
    }

    public static void main(String[] args) {
        WordLadder2 wordLadder2 = new WordLadder2();
        List<List<String>> result = wordLadder2.findLadders("red", "tax", List.of("ted","tex","red","tax","tad","den","rex","pee"));
        result.stream().forEach(System.out::println);
    }
}
