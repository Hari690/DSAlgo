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
        List<String> list = new ArrayList<>();
        List<List<String>> output = new ArrayList<>();
        check(s,0,list,output);
        return output;
    }

    private void check(String s, int i, List<String> list, List<List<String>> output) {
        if(i==s.length()) {
            output.add(new ArrayList<>(list));
            return;
        }

        for(int k=i;k<s.length();k++) {
            if(checkPalindrome(s,i,k)) {
                list.add(s.substring(i,k+1));
                // keep checking until the end.
                check(s,k+1,list,output);
                list.remove(list.size()-1);
            }
        }
    }

    private boolean checkPalindrome(String s, int i, int j) {
        while(i<j) {
            if(s.charAt(i)!=s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}
