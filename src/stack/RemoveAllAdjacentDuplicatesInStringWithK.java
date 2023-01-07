package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.
 * We repeatedly make k duplicate removals on s until we no longer can.
 * Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.
 *
 * Example 1:
 * Input: s = "abcd", k = 2
 * Output: "abcd"
 * Explanation: There's nothing to delete.
 *
 * Example 2:
 * Input: s = "deeedbbcccbdaa", k = 3
 * Output: "aa"
 * Explanation:
 * First delete "eee" and "ccc", get "ddbbbdaa"
 * Then delete "bbb", get "dddaa"
 * Finally delete "ddd", get "aa"
 *
 * Example 3:
 * Input: s = "pbbcggttciiippooaais", k = 2
 * Output: "ps"
 */
public class RemoveAllAdjacentDuplicatesInStringWithK {
    class Value {
        char c;
        int no;
        Value(char c, int occ) {
            this.c = c;
            this.no = occ;
        }
    }
    public String removeDuplicates(String s, int k) {
        Deque<Value> stack = new LinkedList<>();

        int i=0;
        while(i<s.length()) {
            char c = s.charAt(i);
            if(stack.isEmpty() || stack.peek().c!=c) {
                Value value = new Value(c, 1);
                stack.push(value);
            } else {
                if(stack.peek().no==k-1)
                    stack.pop();
                else
                    stack.peek().no+=1;
            }
            i++;
        }

        StringBuilder str = new StringBuilder();
        while(!stack.isEmpty()) {
            Value value = stack.pop();
            for(i=0;i<value.no;i++)
                str.append(value.c);
        }
        return str.reverse().toString();
    }

    public static void main(String[] args) {
        RemoveAllAdjacentDuplicatesInStringWithK solution = new RemoveAllAdjacentDuplicatesInStringWithK();
        solution.removeDuplicates("deeedbbcccbdaa",3);
    }
}
