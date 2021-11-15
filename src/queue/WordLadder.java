package queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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


    // my solution
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        int min = Integer.MAX_VALUE;
        Map<String,List<String>> map = new HashMap<>();
        wordList.add(beginWord);


        for(int i=0;i<wordList.size();i++) {
            for(int j=0;j<wordList.size();j++) {
                if(diff1(wordList.get(i),wordList.get(j)) && !wordList.get(i).equals(wordList.get(j))){
                    List<String> neighbours = map.get(wordList.get(i));
                    if(neighbours == null)
                        neighbours = new ArrayList<>();
                    neighbours.add(wordList.get(j));
                    map.put(wordList.get(i), neighbours);
                }
            }
        }

        //map.entrySet().stream().forEach(System.out::println);
        Set<String> set = new HashSet<>();

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int level=1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                String word = queue.remove();
                //System.out.println("level="+level+" word="+word);
                if(word.equals(endWord))
                    return level;
                set.add(word);
                List<String> neighbours = map.get(word);
                if(neighbours!=null) {
                    for(String neighbour : neighbours) {
                        if(!set.contains(neighbour))
                            queue.add(neighbour);
                    }
                }
            }
            level++;
        }

        return 0;

    }

    private boolean diff1(String word1, String word2) {
        int diff=0;
        for(int i=0;i<word1.length();i++) {
            if(word1.charAt(i)!=word2.charAt(i) )
                diff++;
            if(diff>1)
                break;
        }
        return (diff==1);
    }
}
