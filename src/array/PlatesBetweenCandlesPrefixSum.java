package array;

import java.util.ArrayList;
import java.util.List;

/**
 * There is a long table with a line of plates and candles arranged on top of it. You are given a 0-indexed string s consisting of characters '*' and '|' only, where a '*' represents a plate and a '|' represents a candle.
 * You are also given a 0-indexed 2D integer array queries where queries[i] = [lefti, righti] denotes the substring s[lefti...righti] (inclusive). For each query, you need to find the number of plates between candles that are in the substring. A plate is considered between candles if there is at least one candle to its left and at least one candle to its right in the substring.
 * For example, s = "||**||**|*", and a query [3, 8] denotes the substring "*||**|". The number of plates between candles in this substring is 2, as each of the two plates has at least one candle in the substring to its left and right.
 * Return an integer array answer where answer[i] is the answer to the ith query.
 * Input: s = "**|**|***|", queries = [[2,5],[5,9]]
 * Output: [2,3]
 * Explanation:
 * - queries[0] has two plates between candles.
 * - queries[1] has three plates between candles.
 *
 * Input: s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
 * Output: [9,0,0,0,0]
 * Explanation:
 * - queries[0] has nine plates between candles.
 * - The other queries have zero plates between candles.
 */
public class PlatesBetweenCandlesPrefixSum {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        List<Integer> presum = new ArrayList<>();
        int[] closestLeft = new int[n], closestRight = new int[n];
        int sum = 0, index = -1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*') sum++;
            else {
                // for every candle store the no of plates before it.
                presum.add(sum);
                index++;
            }
            // find the closest left and right candle index and store it.
            closestLeft[i] = index;
        }

        index = presum.size();;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '|') index--;
            closestRight[i] = index;
        }

        // total is diff of candles between next left and last right
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0], end = queries[i][1];
            int left = closestRight[start];
            int right = closestLeft[end];
            if (left < right) res[i] = presum.get(right) - presum.get(left);
        }
        return res;
    }
}
