package stack;

import java.util.Stack;

public class CandyCrush1D {
    public String solution(String s) {
        Stack<Character> stack = new Stack<>();
        Stack<Integer> occurence = new Stack<>();
        for (int i = 0; i < s.length(); ) {
            char c = s.charAt(i);
            if (stack.isEmpty() || c != stack.peek()) {
                if (!stack.isEmpty() && occurence.peek() >= 3) {
                    stack.pop();
                    occurence.pop();
                } else {
                    stack.push(c);
                    occurence.push(1);
                    i++;
                }
            } else {
                int count = occurence.pop();
                occurence.push(count + 1);
                i++;
            }
        }

        if (occurence.peek() >= 3) {
            stack.pop();
            occurence.pop();
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            char c = stack.pop();
            int count = occurence.pop();
            while (count > 0) {
                sb.append(c);
                count--;
            }
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        CandyCrush1D solution = new CandyCrush1D();
        System.out.println(solution.solution("aaabbbc"));
        System.out.println(solution.solution("aabbbacd"));
        System.out.println(solution.solution("aabbccddeeedcba"));
        System.out.println(solution.solution("aaabbbacd"));
    }
}
