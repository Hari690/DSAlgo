package dynamicprogramming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or
 * more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 */
public class WordBreak {
    public boolean wordBreakBruteForce(String s, List<String> wordDict) {
        // put all words into a hashset
        Set<String> set = new HashSet<>(wordDict);
        return wb(s, set);
    }

    private boolean wb(String s, Set<String> set) {
        int len = s.length();
        if (len == 0) {
            return true;
        }
        for (int i = 1; i <= len; ++i) {
            if (set.contains(s.substring(0, i)) && wb(s.substring(i), set)) {
                return true;
            }
        }
        return false;
    }

    public boolean wordBreakTopDownDp(String s, List<String> wordDict) {
        // put all words into a hashset
        Set<String> set = new HashSet<>(wordDict);
        Map<String,Boolean> map = new HashMap<>();
        return wb(s, set, map);
    }

    private boolean wb(String s, Set<String> set, Map<String,Boolean> map) {
        int len = s.length();
        if(map.containsKey(s))
            return map.get(s);
        if (len == 0) {
            return true;
        }
        for (int i = 1; i <= len; ++i) {
            if (set.contains(s.substring(0, i)) && wb(s.substring(i), set, map)) {
                return true;
            }
        }
        map.put(s, false);
        return false;
    }

    public boolean wordBreakBottomUpDp(String s, List<String> words) {
        Set<String> dict = new HashSet<>(words);
        boolean[] f = new boolean[s.length() + 1];
        f[0] = true;

        //Second DP
        for(int i=1; i <= s.length(); i++){
            for(int j=0; j < i; j++){
                if(f[j] && dict.contains(s.substring(j, i))){
                    f[i] = true;
                    break;
                }
            }
        }

        return f[s.length()];
    }

}
