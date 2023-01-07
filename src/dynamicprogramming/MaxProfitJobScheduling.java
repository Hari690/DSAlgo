package dynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 * You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 *
 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * Output: 120
 * Explanation: The subset chosen is the first and fourth job.
 * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 *
 * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * Output: 150
 * Explanation: The subset chosen is the first, fourth and fifth job.
 * Profit obtained 150 = 20 + 70 + 60.
 *
 * Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * Output: 6
 */
public class MaxProfitJobScheduling {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<Job> list = new ArrayList<>();
        for(int i=0;i<startTime.length;i++) {
            list.add(new Job(startTime[i], endTime[i], profit[i]));
        }
        // So - the goal is to figure out "what is the maximum profit you can achieve if you were to end at time t".
        // You know that given the maximum profits you can achieve from all times [0, t) then you can figure out the maximum
        // profit to achieve time t if you have a job that ends at t. You can either choose to accept the profit of profitEndingAt[t-1]
        // (where you ignore the current job ending at t) or you can choose to accept the job and get
        // profitEndingAt[t - thisJobsDuration] + thisJobsProfit.
        // To do that, you sort by end time because you want to incrementally consider all the possible end times.
        // In the example in the problem the end times are 3,4,5,6 so you first want to consider
        // "ok, what's my maximum profit if I do or do not take job that ends at 3? Then the one that ends at 4?" and so on
        Collections.sort(list, Comparator.comparingInt(a -> a.endTime));

        // we need to ensure that each subsequent job we pick up there's no job before it with a
        // higher value(last entry in map), if so skip it.
        // Current contribution of job is current jobs value plus value from previous job before this start.
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        treeMap.put(0,0);
        for(int i=0;i<list.size();i++) {
            Job job = list.get(i);
            int val = job.profit + treeMap.floorEntry(job.startTime).getValue();
            if (val>treeMap.lastEntry().getValue())
                treeMap.put(job.endTime, val);
        }

        return treeMap.lastEntry().getValue();
    }

    static class Job {
        int startTime;
        int endTime;
        int profit;
        Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,6};
        int[] arr2 = {3,5,10,6,9};
        int[] arr3 = {20,20,100,70,60};

        MaxProfitJobScheduling maxProfitJobScheduling = new MaxProfitJobScheduling();
        maxProfitJobScheduling.jobScheduling(arr1, arr2, arr3);
    }
}
