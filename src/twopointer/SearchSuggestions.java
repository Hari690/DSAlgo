package twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an array of strings products and a string searchWord.
 * Design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.
 * Return a list of lists of the suggested products after each character of searchWord is typed.
 *
 * Example 1:
 *
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 * Example 2:
 *
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * Example 3:
 *
 * Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 * Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 */
public class SearchSuggestions {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();
        Arrays.sort(products);
        int left = 0;
        int right = products.length-1;
        for(int i=0;i<searchWord.length();i++) {
            while(left<=right && (products[left].length()<=i || products[left].charAt(i)!=searchWord.charAt(i))) {
                left++;
            }
            while(left<=right && products[right].charAt(i)!=searchWord.charAt(i)) {
                right--;
            }
            List<String> list = new ArrayList<>();
            for(int j=left;j<left+3 && j<=right;j++) {
                list.add(products[j]);
            }
            result.add(list);
        }
        return result;
    }
}
