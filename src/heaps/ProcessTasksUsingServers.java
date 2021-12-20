package heaps;

import java.util.PriorityQueue;

/**
 * You are given two 0-indexed integer arrays servers and tasks of lengths n​​​​​​ and m​​​​​​ respectively. servers[i] is the weight of
 * the i​​​​​​th​​​​ server, and tasks[j] is the time needed to process the j​​​​​​th​​​​ task in seconds.
 *
 * Tasks are assigned to the servers using a task queue. Initially, all servers are free, and the queue is empty.
 *
 * At second j, the jth task is inserted into the queue (starting with the 0th task being inserted at second 0). As long as there are
 * free servers and the queue is not empty, the task in the front of the queue will be assigned to a free server with the smallest
 * weight, and in case of a tie, it is assigned to a free server with the smallest index.
 *
 * If there are no free servers and the queue is not empty, we wait until a server becomes free and immediately assign the next task. If
 * multiple servers become free at the same time, then multiple tasks from the queue will be assigned in order of insertion following the
 * weight and index priorities above.
 *
 * A server that is assigned task j at second t will be free again at second t + tasks[j].
 *
 * Build an array ans​​​​ of length m, where ans[j] is the index of the server the j​​​​​​th task will be assigned to.
 *
 * Return the array ans​​​​.
 */
public class ProcessTasksUsingServers {
    public int[] assignTasks(int[] servers, int[] tasks) {
        //since if there are multiple tasks that need to be assigned, we need to assign in  the order of index
        //so we can go through tasks from left to right
        //and ask what could be the server for this task
        //For which server it can be assigned, it is determined by which servers are avaiable
        //we just need to choose from the servers that are available with smallest weight
        //Like in priorityQueue
        //So if we have a group of servers with their own next available time
        //if next avaibable time <= the execution time of the task, we need to choose the server with smallest weight
        //can we have 2 PQ, one is sorted by weight and index, this is currently avaiable pq
        //one is sorted by available time, this is currently used server pq
        //[weight, index, avaialbe_time]
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> (a[0] != b[0]) ? (a[0] - b[0]) : (a[1] - b[1]));
        PriorityQueue<int[]> usedQueue = new PriorityQueue<>((a, b)->(a[2] != b[2]) ? (a[2] - b[2]) : ((a[0] != b[0]) ? (a[0] - b[0]) : (a[1] - b[1])));
        int n = servers.length;
        int m = tasks.length;
        //O(nLogn)
        for (int i = 0; i < n; i++) {
            freeServers.add(new int[] {servers[i], i, 0});
        }
        int[] res = new int[m];
        //O(m * Logn)
        for (int i = 0; i < m; i++) {
            int t = tasks[i];
            while (!usedQueue.isEmpty() && usedQueue.peek()[2] <= i) {
                freeServers.add(usedQueue.poll());
            }
            //If there is no free servers now, we can find the used server with smallest available time
            if (freeServers.isEmpty()) {
                int[] cur = usedQueue.poll();
                res[i] = cur[1];
                cur[2] += t;
                usedQueue.add(cur);
            } else {
                int[] cur = freeServers.poll();
                res[i] = cur[1];
                cur[2] = i + t;
                usedQueue.add(cur);
            }
        }
        return res;
    }
}
