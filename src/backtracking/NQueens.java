package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.'
 * both indicate a queen and an empty space, respectively.
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 *
 * Input: n = 1
 * Output: [["Q"]]
 *
 * // Parallelisation
 * /*
 *   while not done:
 *   1) pop a state S from the queue (use locks), if queue is empty,
 *      wait on a semaphore until there is an S
 *   2) expand state S
 *   2a) if S has feasible children then put them into the queue
 *       except for one state SS, call it S and goto 2
 *      (also signal the semaphore)
 *   2b) if S has no feasible children goto 1
 * end while
 *  */
public class NQueens {
    // we should keep only one queen in one column.
    private Set<Integer> col = new HashSet<>();

    // all queens along a single diagonal have row - col as constant so we should not keep 2 in the same.
    private Set<Integer> diag1 = new HashSet<>();

    // similarly other diagonal has row + col as a constant.
    private Set<Integer> diag2 = new HashSet<>();

    // complexity is n!*n

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        dfs(res,new ArrayList<>(), 0, n);
        return res;
    }
    private void dfs(List<List<String>> res, List<String> list, int row, int n){
        if (row == n){
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < n; i++){
            if (col.contains(i) || diag1.contains(row + i) || diag2.contains(row - i)) continue;

            char[] charArray = new char[n];
            Arrays.fill(charArray, '.');
            charArray[i] = 'Q';
            String rowString = new String(charArray);

            list.add(rowString);
            col.add(i);
            diag1.add(row + i);
            diag2.add(row - i);

            dfs(res, list, row + 1, n);

            list.remove(list.size() - 1);
            col.remove(i);
            diag1.remove(row + i);
            diag2.remove(row - i);
        }
    }
}
