package dynamicprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.
 *
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 */
public class ConcatWords {
    private static  Map<String,Boolean> check = new HashMap<>();
    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> output = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for( String word : words) {
            set.add(word);
        }
        for( String word : words) {
            if(check(set, word)) {
                output.add(word);
            }
        }
        return output;
    }

    private static boolean check(Set<String> set, String word) {
        if(check.containsKey(word))
            return check.get(word);

        boolean result = false;
        for(int i=1;i<word.length();i++) {
            if(set.contains(word.substring(0,i))) {
                if(set.contains(word.substring(i)) || check(set, word.substring(i))) {
                    result = true;
                    break;
                }
            }
        }
        check.put(word,result);
        return result;
    }
}
