package problems;

/**
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 * Given a string s, return true if it is a palindrome, or false otherwise.*
 *
 * Example 1:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 *
 * Example 2:
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 *
 * Example 3:
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric characters.
 * Since an empty string reads the same forward and backward, it is a palindrome.
 */
public class Palindrome {
    public boolean isPalindrome(String s) {
        if(s.length() <= 1) return true;
        int st = 0, en = s.length()-1;
        while(st < en){
            while(st < en && !Character.isLetterOrDigit(s.charAt(st))) st++;
            while(st < en && !Character.isLetterOrDigit(s.charAt(en))) en--;
            if(st < en && Character.toLowerCase(s.charAt(st)) != Character.toLowerCase(s.charAt(en))) return false;
            st++;
            en--;
        }
        return true;
    }

    /*
        Reverse the number and compare.
     */
    public boolean isPalindromeNumber(int x) {
        if(x<0 || (x!=0 && x%10==0))
            return false;
        int res = 0;
        while(x>res){
            res = res*10 + x%10;
            x = x/10;
        }
        /*
        x == res / 10 is for odd number of digits. Suppose number is 45654, now it will come out of loop when x < res i.e. x = 45 and res = 456,
        so to truncate the 6 of res, we use x = res / 10. But this is not the case of even digits. Suppose number is 456654, it will come out of loop when x == res
        i.e. x = 456 and res = 456, so no need to truncate & we can use x == res directly for it.
         */
        return (x==res || x==res/10);
    }
}
