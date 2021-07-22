package heaps;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        if(k == nums.length) return nums;
        int[] result = new int[k];

        Map<Integer, Integer> counts = new HashMap();
        for(int x: nums){
            counts.put(x, counts.getOrDefault(x, 0) + 1);
        }

        // Using map to define comparator of heap i.e 2 elements comparison
        Queue<Integer> PQ = new PriorityQueue<>((a, b) -> counts.get(b) - counts.get(a));
        for(int x: counts.keySet()){
            PQ.add(x);
        }

        for(int i = 0; i < k; ++i)
            result[i] = PQ.poll();
        return result;
    }
}
