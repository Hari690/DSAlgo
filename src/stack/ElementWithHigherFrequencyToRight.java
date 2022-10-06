package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
/**
 * Given an array, for each element find the value of the nearest element to the
 * right which is having a frequency greater than as that of the current element.
 * If there does not exist an answer for a position, then make the value ‘-1’.
 *
 * Input : a[] = [1, 1, 2, 3, 4, 2, 1]
 *
 * 1 = 3
 * 2 = 2
 * 3 = 1
 * 4 = 1
 *
 * Output : [-1, -1, 1, 2, 2, 1, -1]
 */
public class ElementWithHigherFrequencyToRight {
    public int[] solve(int[] n, int len) {
        Map<Integer, Integer> intCount = new HashMap<>();
        for (int num: n) {
            intCount.put(num, intCount.getOrDefault(num, 0) + 1);
        }

        int[] counts = new int[len];
        for (int i=0 ; i<len ; ++i) {
            counts[i] = intCount.get(n[i]);
        }

        int[] answer = new int[len];
        Stack<Integer> monotonic = new Stack<>();
        for (int i=len-1 ; i>=0 ; --i) {
            int curr = counts[i];
            while (!monotonic.isEmpty() && counts[monotonic.peek()] <= curr) {
                monotonic.pop();
            }
            if (monotonic.isEmpty()) {
                answer[i] = -1;
            } else {
                answer[i] = n[monotonic.peek()];
            }
            monotonic.push(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] n = new int[]{1, 1, 2, 3, 4, 2, 1};
        ElementWithHigherFrequencyToRight obj = new ElementWithHigherFrequencyToRight();
        int[] answer = obj.solve(n, n.length);
        for (int a: answer) {
            System.out.print(a + " ");
        }
    }
}
