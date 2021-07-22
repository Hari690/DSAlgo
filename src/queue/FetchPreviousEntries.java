package queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Find all (t-3000) entries
 */
public class FetchPreviousEntries {
    Queue<Integer> queue;
    public FetchPreviousEntries() {
        queue = new LinkedList();
    }

    public int ping(int t) {
        queue.offer(t);
        while(queue.peek()>t-3000) {
            queue.poll();
        }
        return queue.size();
    }
}
