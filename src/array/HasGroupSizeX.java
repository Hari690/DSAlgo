package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * In a deck of cards, each card has an integer written on it.
 *
 * Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more groups of cards, where:
 *
 * Each group has exactly X cards.
 * All the cards in each group have the same integer.
 *
 * Input: deck = [1,2,3,4,4,3,2,1]
 * Output: true
 * Explanation: Possible partition [1,1],[2,2],[3,3],[4,4].
 */
public class HasGroupSizeX {

    /*
        Brute force approach would be to get counts of numbers and keep checking with that until size of array.
        Alternate is to get counts of numbers and find gcd and check if it's greater than 1
     */
    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer,Integer> counts = new HashMap<>();

        for(int no : deck) {
            counts.put(no, counts.getOrDefault(no,0)+1);
        }

        int group=-1;
        for(int value : counts.values()) {
            if(group==-1)
                group = value;
            else {
                group = gcd(group,value);
            }
        }
        return group>=2;
    }

    private int gcd(int x, int y) {
        if(x==0)
            return y;
        return gcd(y%x,x);
    }

    public static void main(String[] args) {
        HasGroupSizeX solution = new HasGroupSizeX();
        int[] deck = {1,1,2,2,2,2};
        solution.hasGroupsSizeX(deck);
    }
}
