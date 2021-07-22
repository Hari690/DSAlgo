package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *  There are a total of n courses you have to take labelled from 0 to n - 1.
 *
 * Some courses may have prerequisites, for example, if prerequisites[i] = [ai, bi] this means you must take the course bi before the
 * course ai.
 *
 * Given the total number of courses numCourses and a list of the prerequisite pairs, return the ordering of courses you should take to
 * finish all courses.
 *
 * If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 */
public class CourseSchedule {

    // Detecting cycle in a directed graph
    private boolean dfsTop(List<Integer>[] graph, int[] visited, int course, Stack<Integer> output){
        // state 1 for being visited
        if(visited[course]==1)
            return false;

        // already visited skip it
        if(visited[course] == 2)
            return true;

        // mark as being visited
        visited[course] = 1;

        for(int neighbour : graph[course]){
            if(!dfsTop(graph,visited,neighbour,output))
                return false;
        }

        // state 2 for completely visited
        visited[course] = 2;

        output.add(course);
        return true;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new LinkedList[numCourses];
        for(int i = 0; i < numCourses; ++i)
            adj[i] = new LinkedList<>();
        for(int[] courses: prerequisites)
            adj[courses[1]].add(courses[0]);

        Stack<Integer> s = new Stack<>();
        int[] visited = new int[numCourses];
        for(int i = 0; i < numCourses; ++i)
            if(visited[i] == 0 && !dfsTop(adj, visited, i, s)) return new int[0];
        int[] result = new int[s.size()];
        int i = 0;
        while (!s.empty()) {
            result[i++] = s.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        CourseSchedule courseSchedule = new CourseSchedule();
        int[][] courses = {{1,0}};
        Arrays.stream(courseSchedule.findOrder(2, courses)).forEach(System.out::println);
    }

    /**
     * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites
     * where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
     *
     * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
     * Return true if you can finish all courses. Otherwise, return false.
     * @param numCourses
     * @param prerequisites
     * @return
     */
    // It boils down to finding cycle in directed graph.
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];

        // build adjacency - list array of list
        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList();

        // visited array
        int[] visited = new int[numCourses];

        // populate adjacency list
        for(int i=0; i<prerequisites.length;i++){
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        for(int i=0; i<numCourses; i++){
            if(!dfs(graph,visited,i))
                return false;
        }
        return true;
    }

    private boolean dfs(ArrayList[] graph, int[] visited, int course){
        // state 1 for being visited
        if(visited[course]==1)
            return false;

        // already visited skip it
        if(visited[course] == 2)
            return true;

        // mark as being visited
        visited[course] = 1;

        for(int i=0; i<graph[course].size();i++){
            if(!dfs(graph,visited,(int)graph[course].get(i)))
                return false;
        }

        // state 2 for completely visited
        visited[course] = 2;
        return true;
    }

}
