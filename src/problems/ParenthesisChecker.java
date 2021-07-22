package problems;

import java.util.Stack;

public class ParenthesisChecker {

    public static boolean isValid(String s1) {
        Stack<Character> s = new Stack<>();
        for(int i=0;i<s1.length();i++) {
            char c = '\0';
            if(s1.charAt(i)==')') {
                if(s.size()>0) {
                    c = s.pop();
                }
                if(c!='(') {
                    return false;
                }
            } else if(s1.charAt(i)==']') {
                if(s.size()>0) {
                    c = s.pop();
                }
                if(c!='[') {
                    return false;
                }

            } else if(s1.charAt(i)=='}') {
                if(s.size()>0) {
                    c = s.pop();
                }
                if(c!='{') {
                    return false;
                }

            } else {
                s.push(s1.charAt(i));
            }
        }
        if(s.size()>0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isValid("()"));
    }
}
