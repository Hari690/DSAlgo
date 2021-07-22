package trie;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Implement the StreamChecker class as follows:
 *
 * StreamChecker(words): Constructor, init the data structure with the given words.
 * query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest, including
 * this letter just queried) spell one of the words in the given list.
 */
public class StreamChecker {

    public Trie t = new Trie();

    // since we want to push in the beginning
    public Deque<Character> stream = new LinkedList();

    public StreamChecker(String[] words) {
        for (String w : words) {
            String w_rev = new StringBuilder(w).reverse().toString();
            t.insert(w_rev);
        }
    }

    public boolean query(char letter) {
        stream.addFirst(letter);
        return t.search(stream);
    }

    class Trie {
        public Trie[] children;
        public boolean endOfWord;

        public Trie() {
            endOfWord = false;
            children = new Trie[26];
        }

        public void insert(String s) {
            Trie t = this;
            for (char c : s.toCharArray()) {
                if (t.children[c - 'a'] == null) {
                    t.children[c - 'a'] = new Trie();
                }
                t = t.children[c - 'a'];
            }
            t.endOfWord = true;
        }

        public boolean search(Deque<Character> s) {
            Trie t = this;
            for (char c : s) {
                if (t.children[c - 'a'] == null) {
                    return false;
                }
                t = t.children[c - 'a'];
                if (t.endOfWord) {
                    return true;
                }
            }
            return false;
        }
    }
}
