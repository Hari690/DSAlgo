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
        Map<Character, Integer> counts = new HashMap();
        for (char t : tasks) {
            counts.put(t, counts.getOrDefault(t, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue(counts.size(), Collections.reverseOrder());
        pq.addAll(counts.values());

        int result = 0;
        while (!pq.isEmpty()) {
            int time = 0;
            List<Integer> tmp = new ArrayList();
            for (int i = 0; i < n + 1; ++i) {
                if (!pq.isEmpty()) {
                    tmp.add(pq.remove() - 1);
                    time++;
                }
            }
            for (int t : tmp) {
                if (t > 0) {
                    pq.add(t);
                }
            }
            result += pq.isEmpty() ? time : n + 1;
        }
        return result;
    }
}
