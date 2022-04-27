package stack;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * Example 1: Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
 * Example 2: Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6
 * at depth 3; 1 + 4 * 2 + 6 * 3 = 27)
 */
public class NestedListSumWeight {
    public int depthSum(List<NestedInteger> nestedList) {
        return getSum(nestedList, 1);
    }

    private int getSum(List<NestedInteger> nestedList, int level) {
        int total = 0;
        for(NestedInteger item : nestedList) {
            if(item.isInteger()) {
                total+=level*item.getInteger();
            } else {
                total+=getSum(item.getList(), level+1);
            }
        }
        return total;
    }

    public int depthSumIterative(List<NestedInteger> nestedList) {
        Queue<NestedInteger> q = new LinkedList<>();
        Queue<Integer> w = new LinkedList<>();
        for (NestedInteger ni:nestedList) {
            q.add(ni);
            w.add(1);
        }
        int count = 0;
        while (!q.isEmpty()) {
            NestedInteger ni = q.poll();
            int weight = w.poll();
            if (ni.isInteger()) {
                count += ni.getInteger() * weight;
                continue;
            }
            for (NestedInteger n:ni.getList()) {
                q.add(n);
                w.add(weight + 1);
            }
        }
        return count;
    }
}
