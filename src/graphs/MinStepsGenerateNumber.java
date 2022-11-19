package graphs;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * Given an int n. You can use only 2 operations:
 *
 * multiply by 2
 * integer division by 3 (e.g. 10 / 3 = 3)
 * Find the minimum number of steps required to generate n from 1.
 *
 * Example 1:
 * Input: 10
 * Output: 6
 * Explanation: 1 * 2 * 2 * 2 * 2 / 3 * 2
 * 6 steps required, as we have used 5 multiplications by 2, and one division by 3.
 *
 * Example 2:
 * Input: 3
 * Output: 7
 * Explanation: 1 * 2 * 2 * 2 * 2 * 2 / 3 / 3
 * 7 steps required, as we have used 5 multiplications by 2 and 2 divisions by 3.
 */
public class MinStepsGenerateNumber {
    public int minSteps(int n) {
        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        int level = 0;

        q.add(1);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                if (curr == n)
                    return level;
                if (visited.contains(curr))
                    continue;
                visited.add(curr);
                q.add(curr * 2);
                q.add(curr / 3);
            }
            level++;
        }

        return -1;
    }
}
