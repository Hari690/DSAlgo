package array;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 *
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 *
 * The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 *
 *
 *
 * Example 1:
 *
 *
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * Example 2:
 *
 * Input: firstList = [[1,3],[5,9]], secondList = []
 * Output: []
 */
public class IntervalIntersection {

    /* Run 2 pointers from beginning and check while they are less than end of each list
       if the min from both ends creates an interval add it,
       else check which one needs to be incremented.
     */
    public int[][] intervalIntersection(int[][] first, int[][] second) {
        List<int[]> result = new ArrayList<>();
        int i=0,j=0;
        while(i<first.length && j<second.length) {
            int start = Math.max(first[i][0],second[j][0]);
            int end = Math.min(first[i][1],second[j][1]);
            if(end>=start) {
                int[] point = new int[2];
                point[0]=start;
                point[1]=end;
                result.add(point);
            }
            if(end==second[j][1])
                j++;
            else
                i++;
        }
        int[][] array = new int[result.size()][2];
        for(int k=0;k<result.size();k++) {
            array[k]=result.get(k);
        }
        return array;
    }

    public static void main(String[] args) {
        int[][] arr1 = {{0,2},{5,10},{13,23},{24,25}};
        int[][] arr2 = {{1,5},{8,12},{15,24},{25,26}};
        IntervalIntersection solution = new IntervalIntersection();
        solution.intervalIntersection(arr1,arr2);
    }
}
