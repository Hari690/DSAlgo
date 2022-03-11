package stack;

import java.util.Deque;
import java.util.LinkedList;

public class RemoveAllAdjacentDuplicatesInString2 {
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
        RemoveAllAdjacentDuplicatesInString2 solution = new RemoveAllAdjacentDuplicatesInString2();
        solution.removeDuplicates("deeedbbcccbdaa",3);
    }
}
