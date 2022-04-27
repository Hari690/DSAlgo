package problems;

/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 */
public class ValidPalindromeDeleteOne {
    public boolean validPalindrome(String s) {
        if( s==null || s.length()<=1) {
            return true;
        }
        int i=0;
        int j=s.length()-1;
        while (i<j) {
            if( s.charAt(i)!=s.charAt(j)){
                return checkPalindrome(s,i+1,j) || checkPalindrome(s,i,j-1);
            }
            i++;
            j--;
        }
        return true;
    }

    boolean checkPalindrome(String s, int i, int j) {
        if( s==null || s.length()<=1) {
            return true;
        }
        while (i<j) {
            if( s.charAt(i++)!=s.charAt(j--)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindromeDeleteOne validPalindrome = new ValidPalindromeDeleteOne();
        validPalindrome.validPalindrome("abca");
    }

    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length-1;
        System.out.println(i);
        System.out.println(j);

        while( i < j) {
            if( i<j && !Character.isLetter(s.charAt(i))) {
                i++;
            }
            if( i<j && !Character.isLetter(s.charAt(j))) {
                j--;
            }
            if( i>=j ) {
                return new String(chars);
            }
            char temp = chars[i];
            chars[i++] = chars[j];
            chars[j--] = temp;
        }
        return new String(chars);
    }
}
