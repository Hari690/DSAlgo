package graphs;

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically
 * neighboring. The same letter cell may not be used more than once.
 */
// For multiple words better use trie or hashmap so we need to traverse matrix only once.
public class WordSearch {

    public static void main(String[] args) {
//        char[][] board = {{'A','B','C','E'},
//                          {'S','F','C','S'},
//                          {'A','D','E','E'}};
//
//        boolean found = new problems.WordSearch().exist(board,"ABCCED");

        char[][] board = {{'A','A'}};

        boolean found = new WordSearch().exist(board,"AAA");

        System.out.println(found);

    }

    public boolean exist(char[][] board, String word) {

        boolean found = false;
        for(int i=0;i<board.length;i++) {
            for (int j = 0; j < board[i].length; j++) {

                if(board[i][j]==word.charAt(0) && !found) {
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    found = exist(board,word, i, j, visited, 0);
                }
            }
        }

        return found;

    }

    private boolean exist(char[][] board, String word, int row, int col, boolean[][] visited, int index) {

        if(index==word.length())
            return true;

        if(row>=board.length || row<0)
            return false;

        if(col>=board[row].length || col <0)
            return false;

        if(visited[row][col]) {
            return false;
        }

        if(word.charAt(index)!=board[row][col]) {
            return false;
        }


        visited[row][col] = true;
        boolean exists1 = exist(board,word,row,col+1, visited, index+1);
        boolean exists2 = exist(board,word,row,col-1,visited, index+1);
        boolean exists3 = exist(board,word,row+1,col,visited, index+1);
        boolean exists4 = exist(board,word,row-1,col,visited, index+1);
        if(exists1 || exists2 || exists3 || exists4) {
            return true;
        }
        visited[row][col] = false;
        return false;
    }

}
