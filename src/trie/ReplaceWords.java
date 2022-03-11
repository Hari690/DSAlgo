package trie;

import java.util.List;

/**
 * In English, we have a concept called root, which can be followed by some other word to form another longer word - let's call this word successor. For example, when the root "an" is followed by the successor word "other", we can form a new word "another".
 * Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces, replace all the successors in the sentence with the root forming it. If a successor can be replaced by more than one root, replace it with the root that has the shortest length.
 * Return the sentence after the replacement.
 *
 * Example 1:
 * Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * Output: "the cat was rat by the bat"
 *
 * Example 2:
 * Input: dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
 * Output: "a a b c"
 *
 */
public class ReplaceWords {
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie root = createTrie(dictionary);
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        for(int i=0;i<words.length;i++) {
            result.append(searchTrie(words[i], root));
            if(i!=words.length-1)
                result.append(" ");
        }
        return result.toString();
    }

    private String searchTrie(String word, Trie root) {
        StringBuilder result = new StringBuilder();
        for(char c : word.toCharArray()) {
            if(root.children[c-'a']==null) {
                break;
            }
            result.append(c);
            root = root.children[c-'a'];
            if(root.endOfWord==true)
                return result.toString();
        }
        return word;
    }

    private Trie createTrie(List<String> dictionary) {
        Trie root = new Trie();
        for(String word : dictionary) {
            Trie node = root;
            for(char c : word.toCharArray()) {
                if(node.children[c-'a']==null) {
                    node.children[c-'a'] = new Trie();
                }
                node = node.children[c-'a'];
            }
            node.endOfWord = true;
        }
        return root;
    }

    class Trie {
        Trie[] children = new Trie[26];
        boolean endOfWord = false;
    }

    public static void main(String[] args) {
        ReplaceWords replaceWords = new ReplaceWords();
        List<String> list = List.of("cat","bat","rat");
        String sentence = "the cattle was rattled by the battery";
        replaceWords.replaceWords(list, sentence);
    }
}
