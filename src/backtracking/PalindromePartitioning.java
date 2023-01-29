package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning
 * of s.
 *
 * A palindrome string is a string that reads the same backward as forward.
 */
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
        find(s, 0, list, result);
        return result;
    }

    private void find(String s, int index, List<String> list, List<List<String>> result) {
        if(index==s.length()) {
            result.add(new ArrayList<>(list));
            return;
        }

        for(int i=index;i<s.length();i++) {
            if(checkPalindrome(s,index,i)) {
                list.add(s.substring(index,i+1));
                find(s, i+1, list, result);
                list.remove(list.size()-1);
            }
        }
    }

    private boolean checkPalindrome(String s, int i, int j) {
        while(i<=j) {
            if(s.charAt(i)!=s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}
