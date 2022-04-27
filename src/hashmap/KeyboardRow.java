package hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyboardRow {
    public String[] findWords(String[] words) {
        Map<Character,Integer> map = new HashMap<>();
        map.put('q', 1);
        map.put('w', 1);
        map.put('e', 1);
        map.put('r', 1);
        map.put('t', 1);
        map.put('y', 1);
        map.put('u', 1);
        map.put('i', 1);
        map.put('o', 1);
        map.put('p', 1);
        map.put('a', 2);
        map.put('s', 2);
        map.put('d', 2);
        map.put('f', 2);
        map.put('g', 2);
        map.put('h', 2);
        map.put('j', 2);
        map.put('k', 2);
        map.put('l', 2);
        map.put('z', 3);
        map.put('x', 3);
        map.put('c', 3);
        map.put('v', 3);
        map.put('b', 3);
        map.put('n', 3);
        map.put('m', 3);

        List<String> result = new ArrayList<>();
        for(String word : words) {
            int row = map.get(Character.toLowerCase(word.charAt(0)));
            boolean isFound = true;
            for(int i=1;i<word.length();i++) {
                if(row!=Character.toLowerCase(word.charAt(i))) {
                    isFound = false;
                    break;
                }
            }
            if(isFound)
                result.add(word);
        }

        String[] output = new String[result.size()];
        for(int i=0;i<result.size();i++)
            output[i]=result.get(i);

        return output;
    }
}
