package stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative
 * meaning left). Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same
 * size, both will explode. Two asteroids moving in the same direction will never meet.
 */
public class AsteroidCollission {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> s = new LinkedList<>();
        for(int i: asteroids){
            if(i > 0){
                s.push(i);
            }else{// i is negative
                // pop until value in stack is equal or lesser in abs value.
                while(!s.isEmpty() && s.peek() > 0 && s.peek() < Math.abs(i)){
                    s.pop();
                }
                // stack empty or value in stack negative, add current value.
                if(s.isEmpty() || s.peek() < 0){
                    s.push(i);
                // value on top is opposite i.e sum upto 0
                }else if(i == -s.peek()){
                    s.pop(); //equal
                }
            }
        }
        int[] res = new int[s.size()];
        for(int i = res.length - 1; i >= 0; i--){
            res[i] = s.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] asteroids = {2,-1,1,-2};
        AsteroidCollission solution = new AsteroidCollission();
        solution.asteroidCollision(asteroids);
    }
}
