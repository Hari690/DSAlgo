package recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WordBreak {
    Map<String, List<String>> cache;

    private List<String> wordBreak(String s, List<String> wordDict) {
        if (cache.containsKey(s)) {
            return cache.get(s);
        }
        List<String> result = new ArrayList<>();
        for (String w : wordDict) {
            if (s.substring(0, w.length()).equals(w)) {
                if (w.length() == s.length()) {
                    result.add(w);
                } else {
                    List<String> tmp = wordBreak(s.substring(w.length()), wordDict);
                    for (String t : tmp) {
                        result.add(w + " " + t);
                    }
                }
            }
        }
        cache.put(s, result);
        return result;
    }
}
