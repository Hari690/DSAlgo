package heaps;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a non-empty list of words, return the k most frequent elements.
 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower
 * alphabetical order comes first.
 */
public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {

        List<String> result = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<words.length; i++)
        {
            if(map.containsKey(words[i]))
                map.put(words[i], map.get(words[i])+1);
            else
                map.put(words[i], 1);
        }

        // use key and value to maintain sorted order
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
            (a,b) -> a.getValue()==b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue()-b.getValue()
        );

        // add at beginning as it's a minHeap
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
