package trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

    //Total : N*K + Q(K+N)
//Space : N*K

/**
 * Design a special dictionary with some words that searchs the words in it by a prefix and a suffix.
 *
 * Implement the WordFilter class:
 *
 * WordFilter(string[] words) Initializes the object with the words in the dictionary.
 * f(string prefix, string suffix) Returns the index of the word in the dictionary, which has the prefix prefix and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.
 * Example 1:
 *
 * Input
 * ["WordFilter", "f"]
 * [[["apple"]], ["a", "e"]]
 * Output
 * [null, 0]
 *
 * Explanation
 * WordFilter wordFilter = new WordFilter(["apple"]);
 * wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".
 */
class PrefixAndSuffixSearch {

    Trie prefix = null;
    Trie suffix = null;

    //Time : N*K
    /*
        Implement 2 tries one with the prefixes and one with reverse of words( suffixes ).
        Now store indexes in each node and populate the tries.
        Find the indexes which satisfy the prefix and suffix.
        Walk down the indexes until finding the larger index which is common in both lists and return.
     */
    public PrefixAndSuffixSearch(String[] words) {
        prefix = new Trie();
        suffix = new Trie();

        for(int i = 0; i < words.length; i++) {
            prefix.insert(words[i], i);
            suffix.insert(new StringBuilder(words[i]).reverse().toString(), i);
        }

    }

    //Time : K + N
    public int f(String pre, String suff) {
        List<Integer> pList = prefix.startsWith(pre);
        List<Integer> sList = suffix.startsWith(new StringBuilder(suff).reverse().toString());

        int i = pList.size()-1, j = sList.size()-1;
        while(i >= 0 && j >= 0) {
            if(Objects.equals(pList.get(i), sList.get(j))) return pList.get(i);
            else if(pList.get(i) > sList.get(j)) i--;
            else j--;
        }

        return -1;
    }
}

class Trie {

    public Trie[] t;
    List<Integer> index;

    Trie() {
        t = new Trie[26];
        index = new ArrayList<>();
    }

    //insert
    public void insert(String word, int i) {
        Trie root = this;
        for(char c: word.toCharArray()) {
            if(root.t[c-'a'] == null) {
                root.t[c-'a'] = new Trie();
            }
            root = root.t[c-'a'];
            root.index.add(i);
        }
    }

    //startswith
    public List<Integer> startsWith(String word) {
        Trie root = this;
        for(char c : word.toCharArray()) {
            if(root.t[c-'a'] == null) {
                return new ArrayList<>();
            }
            root = root.t[c-'a'];
        }
        return root.index;
    }
}
