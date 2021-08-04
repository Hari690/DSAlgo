package array;

/**
 * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station.
 * You begin the journey with an empty tank at one of the gas stations.
 *
 * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the
 * clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique.
 *
 * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * Output: 3
 */
public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        //determine if we have a solution
        // Idea is based on if there's more total gas than total cost then there's definitely a solution and we just
        // need to find where it starts.
        int total = 0;
        for (int i = 0; i < gas.length; i++) {
            total += gas[i] - cost[i];
        }
        if (total < 0) {
            return -1;
        }

        // Next we traverse array and we don't mind if at any point it does not
        // have enough fuel we just need to find point to right where there's enough fuel and set start there as from
        // there we will accumulate enough fuel to cover the points in beginning also.
        int tank = 0;
        int start = 0;
        for (int i = 0; i < gas.length;i++) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        return start;
    }
}
