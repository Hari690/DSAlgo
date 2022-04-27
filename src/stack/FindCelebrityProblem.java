package stack;

import java.util.Stack;

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
