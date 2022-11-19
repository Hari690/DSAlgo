package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortWelshAlphabeticalOrder {
    public static void main(String[] args) {
        String[] alpha = new String[]{"a","b","c","ch","d","dd","e", "f", "ff", "g", "ng", "h", "i", "j", "l", "ll", "m", "n", "o", "p", "ph", "r", "rh", "s", "t", "th", "u", "w", "y"};
        List<String> ans = new ArrayList<>(Arrays.asList("ddr","nah","dea","dd","ngah"));

        welshOrder(ans, alpha);
        for(String str : ans) {
            System.out.println(str);
        }
    }

    private static void welshOrder(List<String> ans, String[] alpha) {
        Map<String, List<Integer>> charOrder = new HashMap<>(); // map to hold list of oder of characters for the string in 'ans'
        Map<String, Integer> orderMap = new HashMap<>();
        int idx = 0;
        for(String str : alpha) {
            orderMap.put(str, idx++);
        }

        Collections.sort(ans, (a, b) -> { // this sorts the words in 'ans' in order
            List<Integer> a1;
            if(charOrder.containsKey(a)) {
                a1 = charOrder.get(a);
            } else {
                a1 = splitString(orderMap, a.toCharArray()); // this would be called only once for 'a string'
                charOrder.put(a, a1);
            }

            List<Integer> b1;
            if(charOrder.containsKey(b)) {
                b1 = charOrder.get(b);
            } else {
                b1 = splitString(orderMap, b.toCharArray());
                charOrder.put(b, b1);
            }

            int aIdx = 0, bIdx = 0;

            while(aIdx < a1.size() && bIdx < b1.size()) {
                if(a1.get(aIdx) != b1.get(bIdx))
                    return a1.get(aIdx) - b1.get(bIdx); // pick up index of char one by one and see which one should comes first
                else {
                    aIdx++;
                    bIdx++;
                }
            }
            return a1.size() > b1.size() ? 1 : -1; // if nothing returns from above, meaning both have same prefix then just return whoever is the smaller in length
        });
    }

    private static List<Integer> splitString(Map<String, Integer> map, char[] c) {
        int i=0;
        List<Integer> chars = new ArrayList<>();
        while (i < c.length-1){
            if (map.containsKey(""+c[i]+c[i+1])) { // since there are few string with 2 char so if the current word has those in word then we need to find the order for them
                chars.add(map.get(""+c[i]+c[i+1]));
                i+=2; // since from current word 2 chars are consumed so increase by 2
            }
            else {
                chars.add(map.get(""+c[i]));
                i++;
            }
        }
        if (i<c.length) chars.add(map.get(""+c[i])); // for last char
        return chars;
    }
}
