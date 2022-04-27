package trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically
 * neighboring. The same letter cell may not be used more than once in a word.
 */
public class WordSearch2 {
    private Trie trie;
    public List<String> findWords(char[][] board, String[] words) {
        List<String> output = new ArrayList<>();
        createTrie(words);
        // dfs on the trie
        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[0].length;j++) {
                // traversing grid only once instead of once for each word.
                findWord(board, i, j, trie, output);
            }
        }
        return output;
    }

    private void createTrie(String[] words) {
        Trie trie = new Trie();
        this.trie = trie;

        for(String word : words ) {
            trie = this.trie;
            for(int i=0;i<word.length();i++) {
                if(trie.children[word.charAt(i)-'a']==null) {
                    trie.children[word.charAt(i)-'a'] = new Trie();
                }
                trie = trie.children[word.charAt(i)-'a'];
            }
            trie.word = word;
        }
    }

    private void findWord(char[][] board, int i, int j, Trie trie, List<String> output) {
        if(i<0 || j<0 || i>=board.length || j>=board[0].length || board[i][j]=='#') {
            return;
        }
        char c = board[i][j];
        if( trie.children[c-'a'] == null)  {
            return;
        }
        trie = trie.children[c-'a'];
        if(trie.word!=null) {
            output.add(trie.word);
            trie.word = null;
        }
        char temp = board[i][j];
        board[i][j] = '#';
        findWord(board, i, j+1, trie, output);
        findWord(board, i+1, j, trie, output);
        findWord(board, i, j-1, trie, output);
        findWord(board, i-1, j, trie, output);
        board[i][j] = temp;
    }

    class Trie {
        private Trie[] children;
        private String word;
        Trie() {
            children = new Trie[26];
            word = null;
        }
    }

    public static void main(String[] args) {
        WordSearch2 search = new WordSearch2();
        char[][] c = {{'a','b','c'},{'a','e','d'},{'a','f','g'}};
        String[] words = {"abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade"};
        List<String> output = search.findWords(c, words);
        output.stream().forEach(System.out::println);
    }
}
