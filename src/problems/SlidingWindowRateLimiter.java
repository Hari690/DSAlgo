package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

interface RateLimiter {
    boolean grantAccess();
}

public class SlidingWindowRateLimiter implements RateLimiter {
    Queue<Long> slidingWindow;
    int timeWindowInSeconds;
    int bucketCapacity;

    public SlidingWindowRateLimiter(int timeWindowInSeconds, int bucketCapacity) {
        this.timeWindowInSeconds = timeWindowInSeconds;
        this.bucketCapacity = bucketCapacity;
        slidingWindow = new ConcurrentLinkedQueue<>();
    }

    @Override
    public boolean grantAccess() {
        long currentTime = System.currentTimeMillis();
        checkAndUpdateQueue(currentTime);
        if(slidingWindow.size() < bucketCapacity){
            slidingWindow.offer(currentTime);
            return true;
        }
        return false;
    }

    private void checkAndUpdateQueue(long currentTime) {
        if(slidingWindow.isEmpty()) return;

        long calculatedTime = (currentTime - slidingWindow.peek())/1000;
        while(calculatedTime >= timeWindowInSeconds){
            slidingWindow.poll();
            if(slidingWindow.isEmpty()) break;
            calculatedTime = (currentTime - slidingWindow.peek())/1000;
        }
    }


    static Map<String, Queue<Long>> holder = new HashMap<>();
    static int TIME_LIMIT = 100;
    static int MAX_HITS = 5;

    public static void main(String[] args) {
        List<Input> inputs = new ArrayList<>();
        inputs.add(new Input("A", 1000));
        inputs.add(new Input("A", 1002));
        inputs.add(new Input("A", 1020));
        inputs.add(new Input("A", 1030));
        inputs.add(new Input("A", 1050));
        inputs.add(new Input("A", 1051));
        inputs.add(new Input("A", 1099));
        inputs.add(new Input("A", 1100));
        inputs.add(new Input("A", 1101));

        for(Input input: inputs) {
            hitAPI(input.clientId, input.timeOfHit);
        }
    }

    public static void hitAPI(String clientId, long timeOfHit) {
        boolean result = isAllowed(clientId, timeOfHit);
        if(result) System.out.println("hit @ " + timeOfHit + "ms");
        else System.out.println("blocked @ " + timeOfHit + "ms");
    }

    public static boolean isAllowed(String clientId, long timeOfHit) {
        boolean result = false;
        if(holder.containsKey(clientId)) {
            //client has hit before, keep polling the queue till you reach a time whose diff with timeOfHit is less than 1000
            Queue<Long> timings = holder.get(clientId);
            while(!timings.isEmpty() && timeOfHit-timings.peek() >= TIME_LIMIT) {
                timings.poll();
            }
            //the remaining queue is the number of hits within the 1000 ms
            if(timings.size() < MAX_HITS) {
                //add the timeOfHit
                timings.add(timeOfHit);
                //update the holder
                holder.put(clientId, timings);
                result = true;
            }
        } else {
            //client hitting first time, simply put it in holder
            Queue<Long> timings = new LinkedList<>();
            timings.add(timeOfHit);
            holder.put(clientId, timings);
            result = true;
        }
        return result;
    }

    static class Input {
        public String clientId;
        public long timeOfHit;
        public Input(String clientId, long timeOfHit) {
            this.clientId = clientId;
            this.timeOfHit = timeOfHit;
        }
    }
}
