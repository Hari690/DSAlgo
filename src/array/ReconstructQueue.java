package array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * You are given an array of people, people, which are the attributes of some people in a queue (not necessarily in order). Each
 * people[i] = [hi, ki] represents the ith person of height hi with exactly ki other people in front who have a height greater than or
 * equal to hi.
 *
 * Reconstruct and return the queue that is represented by the input array people. The returned queue should be formatted as an array
 * queue, where queue[j] = [hj, kj] is the attributes of the jth person in the queue (queue[0] is the person at the front of the queue).
 */
public class ReconstructQueue {
    public int[][] reconstructQueue(int[][] people) {
        //pick up the tallest guy first
        //when insert the next tall guy, just need to insert him into kth position
        //repeat until all people are inserted into list
        Arrays.sort(people,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0]!=o2[0]?-o1[0]+o2[0]:o1[1]-o2[1];
            }
        });
        List<int[]> res = new LinkedList<>();
        for(int[] cur : people){
            res.add(cur[1],cur);
        }
        return res.toArray(new int[people.length][]);
    }
}
