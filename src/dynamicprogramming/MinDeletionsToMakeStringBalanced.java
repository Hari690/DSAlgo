package dynamicprogramming;

/**
 * You are given a string s consisting only of characters 'a' and 'b'​​​​.
 * You can delete any number of characters in s to make s balanced. s is balanced if there is no pair of indices (i,j) such that i < j and s[i] = 'b' and s[j]= 'a'.
 * Return the minimum number of deletions needed to make s balanced.
 *
 * Example 1:
 * Input: s = "aababbab"
 * Output: 2
 * Explanation: You can either:
 * Delete the characters at 0-indexed positions 2 and 6 ("aababbab" -> "aaabbb"), or
 * Delete the characters at 0-indexed positions 3 and 6 ("aababbab" -> "aabbbb").
 * Example 2:
 *
 * Input: s = "bbaaaaabb"
 * Output: 2
 * Explanation: The only solution is to delete the first two characters.
 */
public class MinDeletionsToMakeStringBalanced {

    /*
        Since we are not sure about which point a's can be converted to b or vice-versa.
        For any point in the string calculate the no of b's we need to delete from beginning plus
        no of a's to delete from that point until end to get it to balanced state.
        So we prepare 2 arrays for it beforehand and then use it to do calculation
        of any split point in the array.
        Calculation at every point involves no of b's to delete until previous index
        and a's to delete from current index.
     */
    public int minimumDeletions(String s) {
        int[] as = new int[s.length()+1];
        int[] bs = new int[s.length()+1];
        int i=0;
        for(char c : s.toCharArray()) {
            as[i+1] = as[i] + ((c=='a')?1:0);
            bs[i+1] = bs[i] + ((c=='b')?1:0);
            i++;
        }
        int min = s.length();
        for(i=0;i<=s.length();i++) {
            min = Math.min(min, bs[i]+as[s.length()]-as[i]);
        }
        return min;
    }
}
