package heaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given n​​​​​​ tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, where tasks[i] = [enqueueTimei,
 * processingTimei] means that the i​​​​​​th​​​​ task will be available to process at enqueueTimei and will take processingTimei to
 * finish processing.
 *
 * You have a single-threaded CPU that can process at most one task at a time and will act in the following way:
 *
 * If the CPU is idle and there are no available tasks to process, the CPU remains idle.
 * If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time. If multiple tasks
 * have the same shortest processing time, it will choose the task with the smallest index.
 * Once a task is started, the CPU will process the entire task without stopping.
 * The CPU can finish a task then start a new one instantly.
 * Return the order in which the CPU will process the tasks.
 */
public class SingleThreadedCPU {
    public int[] getOrder(int[][] tasks) {

        int n = tasks.length;
        int[][] tasksWithIndex = new int[tasks.length][3];
        int i = 0;
        for(int[] task: tasks) {
            tasksWithIndex[i][0] = task[0];
            tasksWithIndex[i][1] = task[1];
            tasksWithIndex[i][2] = i++;
        }

        Arrays.sort(tasksWithIndex, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b)->{
            if(a[1]==b[1])
                return a[2]-b[2];
            return a[1]-b[1];
        });

        int[] output = new int[n];
        i = 0;
        int j = 0;
        int time = tasksWithIndex[j][0];
        while(i<n) {
            while(j<n && tasksWithIndex[j][0]<=time) {
                minHeap.offer(tasksWithIndex[j++]);
            }
            if(minHeap.size()==0) {
                time = tasksWithIndex[j][0];
                continue;
            }
            int[] task = minHeap.poll();
            output[i++] = task[2];
            time+=task[1];
        }

        return output;

    }
}
