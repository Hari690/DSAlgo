package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * There are n dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
 *
 * After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
 *
 * When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
 *
 * For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.
 *
 * You are given a string dominoes representing the initial state where:
 *
 * dominoes[i] = 'L', if the ith domino has been pushed to the left,
 * dominoes[i] = 'R', if the ith domino has been pushed to the right, and
 * dominoes[i] = '.', if the ith domino has not been pushed.
 * Return a string representing the final state.
 */
public class PushDominoes {
    public String pushDominoes(String dominoes) {
        Queue<Integer> queue = new LinkedList<>();
        List<Character> list = new ArrayList<>(dominoes.length());
        for(int i=0;i<dominoes.length();i++) {
            char c = dominoes.charAt(i);
            if(c=='R' || c=='L')
                queue.add(i);
            list.add(c);
        }

        while(!queue.isEmpty()) {
            int i = queue.poll();
            if(list.get(i)=='L') {
                if(i>0 && list.get(i-1)=='.') {
                    queue.add(i-1);
                    list.set(i-1, 'L');
                }
            } else if (list.get(i)=='R') {
                if(i+1<list.size() && list.get(i+1)=='.') {
                    // we will handle equal force from both direction case only in one direction.
                    if(i+2<list.size() && list.get(i+2)=='L')
                        queue.poll();
                    else {
                        queue.add(i+1);
                        list.set(i+1, 'R');
                    }
                }
            }
        }

        StringBuilder s = new StringBuilder();
        for(int i=0;i<list.size();i++)
            s.append(list.get(i));
        return s.toString();
    }
}
