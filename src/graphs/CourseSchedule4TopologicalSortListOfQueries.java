package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course ai first if you want to take course bi.
 * For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
 * Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c.
 * You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether course uj is a prerequisite of course vj or not.
 * Return a boolean array answer, where answer[j] is the answer to the jth query.
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
 * Output: [false,true]
 * Explanation: The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
 * Course 0 is not a prerequisite of course 1, but the opposite is true.
 *
 * Example 2:
 * Input: numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
 * Output: [false,false]
 * Explanation: There are no prerequisites, and each course is independent.
 */
public class CourseSchedule4TopologicalSortListOfQueries {
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        Map<Integer, Set<Integer>> graph = createGraph(prerequisites);
        List<Boolean> result = new ArrayList<>();

        Map<Integer, Set<Integer>> relations = new HashMap<>();

        // there can be circular dependency, so this array to keep track if we have visited the node before
        for(int i=0; i<queries.length; i++){
            // DFS search
            dfs(graph, relations, queries[i][0]);
            if(relations.containsKey(queries[i][0]) && relations.get(queries[i][0]).contains(queries[i][1]))
                result.add(true);
            else
                result.add(false);
        }

        return result;
    }

    private Set<Integer> dfs(Map<Integer, Set<Integer>> graph, Map<Integer, Set<Integer>> relations, int node){
        if(relations.containsKey(node))
            return relations.get(node);

        Set<Integer> dependencies = graph.getOrDefault(node, new HashSet<>());
        Set<Integer> related = new HashSet<>();
        for(Integer dependency : dependencies){
            related.addAll(dfs(graph, relations, dependency));
            related.add(dependency);
            relations.computeIfAbsent(node, k -> new HashSet<>()).addAll(related);
        }
        return related;
    }

    private Map<Integer, Set<Integer>> createGraph(int[][] prerequisites){
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for(int[] prerequisite : prerequisites){
            graph.computeIfAbsent(prerequisite[0],k -> new HashSet<>()).add(prerequisite[1]);
        }
        return graph;
    }

    public static void main(String[] args) {
        CourseSchedule4TopologicalSortListOfQueries courseSchedule4 = new CourseSchedule4TopologicalSortListOfQueries();

        int[][] prerequisites = {{0,1},{1,2},{2,3},{3,4}};
        int[][] queries = {{0,4},{4,0},{1,3},{3,0}};
        courseSchedule4.checkIfPrerequisite(5, prerequisites, queries);
    }
}
