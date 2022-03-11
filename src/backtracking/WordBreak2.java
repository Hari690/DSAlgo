package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 */
public class WordBreak2 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        List<String> result = new ArrayList<>();

        dfs(s, set, result, "");

        return result;
    }

    private void dfs(String s, Set<String> set, List<String> result, String current) {
        if(s.length()==0) {
            result.add(current.substring(0,current.length()-1));
            return;
        }

        for(int i=0;i<=s.length();i++) {
            if(set.contains(s.substring(0,i)))
                dfs(s.substring(i), set, result, current+s.substring(0,i)+" ");
        }
    }
}
