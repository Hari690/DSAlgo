package graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * You are given two jugs with capacities jug1Capacity and jug2Capacity liters. There is an infinite amount of water supply available.
 * Determine whether it is possible to measure exactly targetCapacity liters using these two jugs.
 *
 * If targetCapacity liters of water are measurable, you must have targetCapacity liters of water contained within one or both buckets by
 * the end.
 *
 * Operations allowed:
 *
 * Fill any of the jugs with water.
 * Empty any of the jugs.
 * Pour water from one jug into another till the other jug is completely full, or the first jug itself is empty.
 */
/*
    If we taking the shifting water between jugs out of picture, we are effectively using a single vessel and adding/removing
    jug1/jug2 amount water until we reach our destination with any jug so key is to decompose the problem.
 */
public class WaterJugProblem {
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);
        visited.add(0);

        // both are filled.
        int maxCapacity = jug1Capacity+jug2Capacity;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                int node = queue.poll();
                if(node==targetCapacity)
                    return true;
                visited.add(node);
                if(node+jug1Capacity>=0 && node+jug1Capacity<=maxCapacity && !visited.contains(node+jug1Capacity))
                    queue.add(node+jug1Capacity);
                if(node+jug2Capacity>=0 && node+jug2Capacity<=maxCapacity && !visited.contains(node+jug2Capacity))
                    queue.add(node+jug2Capacity);
                if(node-jug1Capacity>=0 && node-jug1Capacity<=maxCapacity && !visited.contains(node-jug1Capacity))
                    queue.add(node-jug1Capacity);
                if(node-jug2Capacity>=0 && node-jug2Capacity<=maxCapacity && !visited.contains(node-jug2Capacity))
                    queue.add(node-jug2Capacity);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WaterJugProblem waterJugProblem = new WaterJugProblem();
        System.out.println(waterJugProblem.canMeasureWater(3,5,4));
    }
}
