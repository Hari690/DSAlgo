package stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.
 *
 * Implement the FreqStack class:
 * FreqStack() constructs an empty frequency stack.
 * void push(int val) pushes an integer val onto the top of the stack.
 * int pop() removes and returns the most frequent element in the stack.
 * If there is a tie for the most frequent element, the element closest to the stack's top is removed and returned.
 *
 * Example 1:
 * Input
 * ["FreqStack", "push", "push", "push", "push", "push", "push", "pop", "pop", "pop", "pop"]
 * [[], [5], [7], [5], [7], [4], [5], [], [], [], []]
 * Output
 * [null, null, null, null, null, null, null, 5, 7, 5, 4]
 *
 * Explanation
 * FreqStack freqStack = new FreqStack();
 * freqStack.push(5); // The stack is [5]
 * freqStack.push(7); // The stack is [5,7]
 * freqStack.push(5); // The stack is [5,7,5]
 * freqStack.push(7); // The stack is [5,7,5,7]
 * freqStack.push(4); // The stack is [5,7,5,7,4]
 * freqStack.push(5); // The stack is [5,7,5,7,4,5]
 * freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
 * freqStack.pop();   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
 * freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
 * freqStack.pop();   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7].
 */
public class MaxFrequencyStack {

    /*
    HashMap of Stacks
    This approach contains hashmap of stacks(i.e stacks associated with a particular frequency and pushing and poping elements according to their frequencies)
    -> Read comments for more understanding
     */
    Map<Integer,Integer> cnt;//frequency count for every digit
    int max;//max frequency present for any num(we don't know which)

    //used to store frequency-wise stacks of inputs.eg.
    //([2,1,2,5,5,5])(1 freq-[2,1,5],2 freq-[2,5],3 freq-[5])
    Map<Integer, ArrayList<Integer>> stack;//stack assigned with num of frequencies
    public MaxFrequencyStack() {
        //initialise our members
        cnt=new HashMap<>();
        stack=new HashMap<>();
        max=0;
    }

    public void push(int val) {
        //increment val count
        int valCnt=cnt.getOrDefault(val,0)+1;
        cnt.put(val,valCnt);
        //if current cal count is more than max
        if(valCnt>max){
            max=valCnt;
            stack.put(max,new ArrayList<>());//add new stack for this new max count
        }
        stack.get(valCnt).add(val);//add this num to its stacks
    }

    public int pop() {
        //remove last element of max freq
        int res=stack.get(max).remove(stack.get(max).size()-1);
        cnt.put(res,cnt.get(res)-1);//reduce freq of number
        if(stack.get(max).size()==0){//if now this freq have no num left
            max--;//reduce the freq
        }
        return res;
    }
}
