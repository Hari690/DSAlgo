package heaps;

import java.util.PriorityQueue;

/**
 * Joseph is standing at k+1th position in a queue at an insurance company's office and there are n counters at the office, ith counter takes time[i] time (minutes) to process a request. The security guard assigns a counter to the person standing in the front of the queue as soon as a counter is available or if multiple counters are available, then the security official assigns the counter with minimum id (consider id as index). What would be the time at which Joseph's request would be processed aka the ending time when Joseph leaves the office?
 * Example case
 *
 * n = 3
 * times = [3,2,5]
 * k = 4
 * Person 1: assign counter 0 (available counter with minimum index)
 * Person 2: assign counter 1 (available counter with minimum index)
 * Person 3: assign counter 2 (available counter with minimum index)
 * Person 4: assign counter 1 (available counter with minimum index)
 * Person 5 (Joesph): assign counter 0 (available counter with minimum index)
 */
public class CounterAssignment {
    public static void main(String[] args) {
        int [] counter = new int[]{3,2,5};
        int k=4;
        System.out.println(findTotalTime(counter,4));
    }

    public static int findTotalTime(int[] counter, int k){
        int currentTime=0;
        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a,b)->(a[0] <= b[0] ? Integer.compare(a[1],b[1]) : Integer.compare(a[0],b[0])));

        for(int i=0;i<counter.length;i++)
            pq.add(new int[]{0,i});

        int endTime=0;
        for(int i=0;i<=k;i++){
            int info[] = pq.poll();
            int counterTime = info[0];
            int index = info[1];
            endTime=counterTime;
            counterTime+=counter[index];
            pq.add(new int[]{counterTime,index});
            if(currentTime<counterTime)
                currentTime=counterTime;
        }

        return currentTime+endTime;
    }
}
