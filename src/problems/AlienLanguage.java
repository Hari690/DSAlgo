package problems;

import java.util.HashMap;
import java.util.Map;

public class AlienLanguage {
    public boolean isAlienSorted(String[] words, String order) {

        Map<Character,Integer> orderMap = new HashMap<>();

        int index = 0;
        for ( Character c : order.toCharArray()) {
            orderMap.put(c, index++);
        }

        int i = 0;
        while ((i+1) < words.length) {
            int j=0;
            while(j<words[i].length() && j<words[i+1].length()) {
                if ( orderMap.get(words[i].charAt(j)) < orderMap.get(words[i+1].charAt(j))) {
                    break;
                } else if ( orderMap.get(words[i].charAt(j)) == orderMap.get(words[i+1].charAt(j))) {
                    j++;
                } else {
                    return false;
                }
            }
            if( j < words[i].length() && j==words[i+1].length()) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static void main(String[] args) {

        String words[] = {"fxasxpc","dfbdrifhp","nwzgs","cmwqriv","ebulyfyve","miracx","sxckdwzv","dtijzluhts","wwbmnge","qmjwymmyox"};
        String order = "zkgwaverfimqxbnctdplsjyohu";
        System.out.println(new AlienLanguage().isAlienSorted(words, order));
    }
}
