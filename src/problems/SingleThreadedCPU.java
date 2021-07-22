package problems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SingleThreadedCPU {

    public int[] getOrder(int[][] tasks) {

        int[][] tasksWithIndex = new int[tasks.length][3];
        int i = 0;
        for(int[] task: tasks) {
            tasksWithIndex[i][0] = task[0];
            tasksWithIndex[i][1] = task[1];
            tasksWithIndex[i][2] = i++;
        }

        Arrays.sort(tasksWithIndex, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->{
            if(a[1]==b[1])
                return a[2]-b[2];
            return a[1]-b[1];
        });

        int[] output = new int[tasks.length];
        i = 0;
        int j = 0;
        int time = tasksWithIndex[j][0];
        while(i<output.length) {
            if(time<=tasksWithIndex[j][0]) {
                minHeap.offer(tasksWithIndex[j]);
            }
            if(minHeap.size()==0) {
                time = tasksWithIndex[j++][0];
                continue;
            }
            int[] task = minHeap.poll();
            output[i++] = task[2];
        }

        return output;
    }
}
