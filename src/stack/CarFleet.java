package stack;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class CarFleet {
    public int carFleet(int target, int[] positions, int[] speed) {
        Map<Integer,Integer> map = new TreeMap<>(Collections.reverseOrder());

        for(int i=0;i<positions.length;i++) {
            map.put(positions[i], speed[i]);
        }

        Deque<Map.Entry<Integer,Integer>> stack = new LinkedList<>();
        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if(!stack.isEmpty() && ((target-(float)stack.peek().getKey())/stack.peek().getValue())<((target-(float)entry.getKey())/entry.getValue())) {
                // stack.pop();
                // stack.push(entry);
                stack.push(entry);
            } else if(stack.isEmpty()) {
                stack.push(entry);
            }
            System.out.println(target+" "+entry.getKey()+" "+entry.getValue());
            System.out.println("current="+(float)(target-entry.getKey()/entry.getValue()));
            System.out.println("peek="+(float)(target-(stack.peek().getKey()/stack.peek().getValue())));
        }
        return stack.size();
    }

    public static void main(String[] args) {
        CarFleet carFleet = new CarFleet();
        int[] position = {6,8};
        int[] speed = {3,2};

        carFleet.carFleet(10, position, speed);
    }
}
