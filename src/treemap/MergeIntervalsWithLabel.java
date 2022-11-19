package treemap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MergeIntervalsWithLabel {
    public static void main(String[] args) {
        int[][] nums1 = {{0, 3}, {2, 4}, {5,6}};
        char[] chars1 = {'A', 'B', 'C'};
        int[][] nums2 = {{0, 3}, {0, 3}, {2, 4}, {5, 6}};
        char[] chars2 = {'A', 'B', 'C', 'D'};
        for(String s : solve(nums1, chars1))
            System.out.println(s);
        System.out.println("---------------");
        for(String s : solve(nums2, chars2))
            System.out.println(s);
    }

    private static List<String> solve(int[][] nums, char[] chars) {
        List<String> res = new ArrayList<>();
        TreeMap<Integer, List<Node>> tm = new TreeMap<>();
        for(int i=0;i<nums.length;i++) {
            Node n1 = new Node(chars[i], true);
            Node n2 = new Node(chars[i], false);
            tm.putIfAbsent(nums[i][0], new ArrayList<>());
            tm.putIfAbsent(nums[i][1], new ArrayList<>());
            List<Node> lst1 = tm.get(nums[i][0]);
            List<Node> lst2 = tm.get(nums[i][1]);
            lst1.add(n1); lst2.add(n2);
        }
        Set<Character> set = new HashSet<>();
        int prev = 0;

        // simulating a number line with entry and exit, add for entry & remove for exit.
        for(Map.Entry<Integer, List<Node>> e : tm.entrySet()) {
            if(set.size() > 0)
                res.add("(" + prev + ", " + e.getKey() + "): " + set);
            for(Node n : e.getValue()) {
                if(!n.isAdd) {
                    set.remove(n.c);
                }else {
                    set.add(n.c);
                }
            }
            prev = e.getKey();
        }
        return res;
    }

    static class Node{

        char c;
        boolean isAdd;

        public Node(char c, boolean isAdd) {
            super();
            this.c = c;
            this.isAdd = isAdd;
        }

    }
}
