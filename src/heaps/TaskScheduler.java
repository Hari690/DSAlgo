package heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
Given a characters array tasks, representing the tasks a CPU needs to do,
where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time.
For each unit of time, the CPU could complete either one task or just be idle.

However, there is a non-negative integer n that represents the cooldown period
between two same tasks (the same letter in the array), that is that there must be at
least n units of time between any two same tasks.

Return the least number of units of times that the CPU will take to finish all the given tasks.
 */
class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        Map<Character,Integer> map = new HashMap<>();

        for(char task : tasks)
            map.put(task, map.getOrDefault(task,0)+1);

        PriorityQueue<Map.Entry<Character,Integer>> pq = new PriorityQueue<>((e1, e2)->
        {
            if(e1.getValue()==e2.getValue())
                return e2.getKey()-e1.getKey();
            else
                return e2.getValue()-e1.getValue();
        });

        int count=0;
        pq.addAll(map.entrySet());
        while(pq.size()>0) {
            int i=n+1; // interval size.
            List<Map.Entry<Character,Integer>> list = new ArrayList();
            while(pq.size()>0 && i>0) {
                i--; // reduce size of interval.
                Map.Entry<Character,Integer> entry = pq.poll();
                entry.setValue(entry.getValue()-1);
                if(entry.getValue()>0)
                    list.add(entry);
                count++;
            }
            pq.addAll(list);
            if(i>0 && pq.size()>0) {
                count+=i;
            }
        }
        return count;
    }
}
