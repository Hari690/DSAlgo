package stack;

import java.util.Stack;

/**
 * Suppose you are at a party with n people (labeled from 0 to n — 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n — 1 people know him/her but he/she does not know any of them.
 * Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: “Hi, A. Do you know B?” to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
 * You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party. Return the celebrity’s label if there is a celebrity in the party. If there is no celebrity, return -1.
 */
public class FindCelebrityProblem {
    // Returns -1 if celebrity
    // is not present. If present,
    // returns id (value from 0 to n-1).
    static int findCelebrity(int n, int[][] matrix)
    {
        Stack<Integer> st = new Stack<>();
        int c;

        // Step 1 :Push everybody
        // onto stack
        for (int i = 0; i < n; i++)
        {
            st.push(i);
        }

        while (st.size() > 1)
        {
            // Step 2 :Pop off top
            // two persons from the
            // stack, discard one
            // person based on return
            // status of knows(A, B).
            int a = st.pop();
            int b = st.pop();

            // Step 3 : Push the
            // remained person onto stack.
            if (knows(a, b, matrix))
            {
                st.push(b);
            }

            else
                st.push(a);
        }

        // If there are only two people
        // and there is no
        // potential candidate
        if(st.empty())
            return -1;

        c = st.pop();

        // Step 5 : Check if the last
        // person is celebrity or not
        for (int i = 0; i < n; i++)
        {
            // If any person doesn't
            //  know 'c' or 'a' doesn't
            // know any person, return -1
            if (i != c && (knows(c, i, matrix) ||
                    !knows(i, c, matrix)))
                return -1;
        }
        return c;
    }

    // Returns true if a knows
    // b, false otherwise
    static boolean knows(int a, int b, int[][] matrix)
    {
        boolean res = (matrix[a][b] == 1) ?
                true :
                false;
        return res;
    }
}
