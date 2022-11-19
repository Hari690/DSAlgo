package heaps;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 */
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

    public List<String> topKFrequentWords(String[] words, int k) {
        List<String> result = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<words.length; i++)
        {
            if(map.containsKey(words[i]))
                map.put(words[i], map.get(words[i])+1);
            else
                map.put(words[i], 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
                (a,b) -> a.getValue()==b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue()-b.getValue()
        );

        for(Map.Entry<String, Integer> entry: map.entrySet())
        {
            minHeap.offer(entry);
            if(minHeap.size()>k)
                minHeap.poll();
        }

        while(!minHeap.isEmpty())
            result.add(0, minHeap.poll().getKey());

        return result;
    }
}
