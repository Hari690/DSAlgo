package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a wooden stick of length n units. The stick is labelled from 0 to n. For example, a stick of length 6 is labelled as follows:
 *
 * Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.
 *
 * You should perform the cuts in order, you can change the order of the cuts as you wish.
 *
 * The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts. When you cut a stick, it will be split into two smaller sticks (i.e. the sum of their lengths is the length of the stick before the cut). Please refer to the first example for a better explanation.
 *
 * Return the minimum total cost of the cuts.
 * Example 1:
 *
 * Input: n = 7, cuts = [1,3,4,5]
 * Output: 16
 * Explanation: Using cuts order = [1, 3, 4, 5] as in the input leads to the following scenario:
 *
 * The first cut is done to a rod of length 7 so the cost is 7. The second cut is done to a rod of length 6 (i.e. the second part of the first cut), the third is done to a rod of length 4 and the last cut is to a rod of length 3. The total cost is 7 + 6 + 4 + 3 = 20.
 * Rearranging the cuts to be [3, 5, 1, 4] for example will lead to a scenario with total cost = 16 (as shown in the example photo 7 + 4 + 3 + 2 = 16).
 * Example 2:
 *
 * Input: n = 9, cuts = [5,6,1,4,2]
 * Output: 22
 * Explanation: If you try the given cuts ordering the cost will be 25.
 * There are much ordering with total cost <= 25, for example, the order [4, 6, 5, 2, 1] has total cost = 22 which is the minimum possible.
 */
public class MinCostCutStick {

    class Pair<K,V> {
        K key;
        V value;
        Pair(K k, V v) {
            this.key = k;
            this.value = v;
        }
    }

    /*
        Keep maintaining the range of cuts and pruning the tree using the range of cuts while applying it.
        O(M^3) since loop inside recursive function.
     */
    public int minCost(int n, int[] cuts) {
        Map<Pair<Integer,Integer>,Integer> cache = new HashMap<>();
        return cut(cuts, 0, n, cache);
    }

    private int cut(int[] cuts, int left, int right, Map<Pair<Integer,Integer>,Integer> cache) {
        if(right-left<=1)
            return 0;

        Integer output = cache.get(new Pair(left,right));
        if(output!=null)
            return output;

        int result = Integer.MAX_VALUE;
        for(int cut : cuts) {
            if(cut>left && cut<right) {
                result = Math.min(result, right-left+cut(cuts, left, cut, cache)+cut(cuts, cut, right, cache));
            }
        }

        int value = (result==Integer.MAX_VALUE)?0:result;
        cache.put(new Pair(left,right),value);
        return value;
    }

    public static void main(String[] args) {
        MinCostCutStick minCostCutStick = new MinCostCutStick();
        int[] cuts = {1,3,4,5};
        System.out.println(minCostCutStick.minCost(7, cuts));
    }
}
