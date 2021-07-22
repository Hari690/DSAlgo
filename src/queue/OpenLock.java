package queue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The
 * wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
 * Each move consists of turning one wheel one slot.
 *
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 *
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning
 * and you will be unable to open it.
 *
 * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to
 * open the lock, or -1 if it is impossible.
 *
 * BFS
 */
public class OpenLock {

    public static void main(String[] args) {
        String[] array = new String[1];
        //openLock(array, "1234");
        System.out.println("hi".substring(0,0));
        System.out.println("hi".substring(1));
    }

    public static int openLock(String[] deadends, String target) {
        Set<String> dead_ends = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        visited.add("0000");
        Queue<String> queue = new LinkedList<>();
        queue.add("0000");

        int level = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size>0) {
                String position = queue.poll();
                if(dead_ends.contains(position)) {
                    size--;
                    continue;
                }

                if(position.equals(target)) {
                    return level;
                }

                StringBuilder sb = new StringBuilder(position);
                for(int i=0;i <4;i++) {
                    char current_pos = sb.charAt(i);
                    String s1 = sb.substring(0,i) + (current_pos=='9' ? 0: current_pos-'0'+1) +sb.substring(i+1);
                    String s2 = sb.substring(0,i) + (current_pos=='0' ? 9: current_pos-'0'-1) +sb.substring(i+1);

                    if(!visited.contains(s1) && !dead_ends.contains(s1)) {
                        queue.offer(s1);
                        visited.add(s1);
                    }
                    if(!visited.contains(s2) && !dead_ends.contains(s2)) {
                        queue.offer(s2);
                        visited.add(s2);
                    }
                }
                size--;
            }
            level++;
        }

        return -1;
    }
}
