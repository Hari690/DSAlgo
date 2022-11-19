package problems;

import java.util.HashMap;
import java.util.Map;

// Main class should be named 'Solution'

// You are given a very long text and an order string. We need to verify that given text follows the order rules defined in the order string.

// For example if order string is “abcd”, we need to implement a verification logic that checks that character “b” doesn’t appear before “a”, character “c” doesn’t appear before “b” and so on.

// Text:                aaabbbbcccc
// Order string:    	acb
// Result:              false

class Solution {

    public static void main(String[] args) {
        //System.out.println("Hello, World");

        Solution solution = new Solution("acb");

        //System.out.println(solution.checkOrder("aaabbbbcccc"));

        System.out.println(solution.checkOrder("aaabbbbcccc", "abc"));

        System.out.println(solution.checkOrder("aaaxxbbbbcccc", "abc"));

        System.out.println(solution.checkOrder("aaaxxabbbbccccxx", "abc"));

        System.out.println(solution.checkOrder("xxaaabxxaxxbbbbccccxx", "abc"));

        // ab
        // bc
        // text - ca
        //
    }

    Map<Character,Integer> orderMap = new HashMap<>();
    int prevOrder = -1;

    Solution(String order) {
        int index = 0;
        for(Character c : order.toCharArray()) {
            orderMap.put(c, index++);
        }
    }

    public boolean checkOrder(String text, String order)  {
        for(int i=1;i<text.toCharArray().length;i++) {
            if(orderMap.get(text.charAt(i))==null) {
                continue;
            }
            if(orderMap.get(text.charAt(i)) < prevOrder) {
                return false;
            }
            prevOrder = orderMap.get(text.charAt(i));
        }
        return true;
    }

    public void clear() {
        prevOrder = -1;
    }

}

