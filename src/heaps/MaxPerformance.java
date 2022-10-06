package heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * You are given two integers n and k and two integer arrays speed and efficiency both of length n. There are n engineers numbered from 1 to n. speed[i] and efficiency[i] represent the speed and efficiency of the ith engineer respectively.
 * Choose at most k different engineers out of the n engineers to form a team with the maximum performance.
 * The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency among their engineers.
 * Return the maximum performance of this team. Since the answer can be a huge number, return it modulo 109 + 7.
 *
 * Example 1:
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
 * Output: 60
 * Explanation:
 * We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.
 * Example 2:
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
 * Output: 68
 * Explanation:
 * This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
 * Example 3:
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
 * Output: 72
 */
public class MaxPerformance {

    /*
        Sort first based on efficiency and then form a sliding window with size k and heap to store speeds along with sum of vals in the heap(sliding window).
        Min value in heap so we can pop out lowest value and sum will be maintained with speed of all values in heap.
     */
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] arr = new int[speed.length][2];

        for(int i=0;i<speed.length;i++) {
            arr[i][0]=efficiency[i];
            arr[i][1]=speed[i];
        }

        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long sum=0,result=0;
        for(int i=0;i<arr.length;i++) {
            sum+=arr[i][1];
            pq.offer(arr[i][1]);
            if(pq.size()>k) {
                int s = pq.poll();
                sum-=s;
            }
            //System.out.println(arr[i][0]+" "+sum);
            result = Math.max(result,arr[i][0]*sum);
        }
        return (int) (result % (long)(1e9 + 7));
    }
}
