package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * There are N courses, labelled from 1 to N.
 *
 * We are given relations[i] = [X, Y], representing a prerequisite relationship between course X and course Y: course X has to be studied before course Y.
 *
 * In one semester you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.
 *
 * Return the minimum number of semesters needed to study all courses.  If there is no way to study all the courses, return -1.
 *
 * Example 1:
 * Input: N = 3, relations = [[1,3],[2,3]]
 * Output: 2
 * Explanation:
 * In the first semester, courses 1 and 2 are studied. In the second semester, course 3 is studied.
 *
 * Example 2:
 * Input: N = 3, relations = [[1,2],[2,3],[3,1]]
 * Output: -1
 * Explanation:
 * No course can be studied because they depend on each other.
 *
 * Note:
 * 1 <= N <= 5000
 * 1 <= relations.length <= 5000
 * relations[i][0] != relations[i][1]
 * There are no repeated relations in the input.
 */
public class ParallelCoursesTopologicalSort {
    public int minimumSemesters(int N, int[][] relations) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        int[] inDegree = new int[N+1];
        for (int[] r: relations) {
            g.computeIfAbsent(r[0], l -> new ArrayList<>()).add(r[1]);
            ++inDegree[r[1]];
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        int semester = 0;
        while (!q.isEmpty()) {
            for (int sz = q.size(); sz > 0; sz--) {
                int c = q.poll();
                --N;
                if (!g.containsKey(c)) {
                    continue;
                }
                for (int course: g.remove(c)) {        // c's in-degree is currently 0, but it has no prerequsite
                    if (--inDegree[course] == 0) {        // decrease the in-degree of course's neighbors
                        q.offer(course);        // add current 0 in-degree vertex into Queue
                    }
                }
            }
            ++semester;        // need one more semester
        }
        return N == 0 ? semester : -1;
    }
}
