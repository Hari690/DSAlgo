package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * Note:
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 */
public class ValidSudoku {

    //O(9^2) - constant space
    public boolean isValidSudoku(char[][] board) {
        Set seen = new HashSet();
        for (int i=0; i<9; ++i) {
            for (int j=0; j<9; ++j) {
                char number = board[i][j];
                if (number != '.')
                    if (!seen.add(number + " in row " + i) ||
                            !seen.add(number + " in column " + j) ||
                            !seen.add(number + " in block " + i/3 + "-" + j/3))
                        return false;
            }
        }
        return true;
    }

    public boolean isValidSudokuMultiSets(char[][] board) {
        Set<String> rowSet = new HashSet();
        Set<String> columnsSet = new HashSet();
        Set<String> gridSet = new HashSet();
        for (int i=0; i<9; ++i) {
            for (int j=0; j<9; ++j) {
                char number = board[i][j];
                if (number != '.') {
                    String rowKey = getKey(number, i);
                    String colKey = getKey(number, j);
                    String innerGridKey = getKey(number, i/3, j/3);
                    if (rowSet.contains(rowKey) || columnsSet.contains(colKey) ||gridSet.contains(innerGridKey))
                        return false;
                    rowSet.add(rowKey);
                    columnsSet.add(colKey);
                    gridSet.add(innerGridKey);
                }
            }
        }
        return true;
    }

    private <T> String getKey(T... values) {
        return Arrays.stream(values).map(String::valueOf).collect(Collectors.joining("#"));
    }

    public static void main(String[] args) {
        char[][] b =  {{'6','4','7','1','9','5','8','3','2'},
        {'2','9','8','5','3','7','1','6','4'},
        {'8','1','9','7','6','2','4','5','3'},
        {'4','7','2','8','5','3','6','9','1'},
        {'7','5','4','9','2','8','3','1','6'},
        {'9','6','5','3','4','1','2','8','7'},
        {'3','8','6','4','1','9','7','2','5'},
        {'1','2','3','6','8','4','5','7','9'}};
        ValidSudoku validSudoku = new ValidSudoku();
        System.out.println(validSudoku.isValidSudokuMultiSets(b));
    }
}
