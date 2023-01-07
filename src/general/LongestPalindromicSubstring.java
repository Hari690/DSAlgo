package general;

public class LongestPalindromicSubstring {
    int index = 0;
    int maxLength = 1;
    public String longestPalindrome(String s) {
        for( int i=0;i<s.length()-maxLength/2;i++) {
            getLongestPalindrome(i,i,s);
            getLongestPalindrome(i,i+1,s);
        }
        return s.substring(index, index+maxLength);
    }

    private void getLongestPalindrome(int i,int j, String s) {
        while( i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)) {
            i--;
            j++;
        }
        // since now the pointers would have gone outside bound.
        if( (j-i-1)>maxLength ) {
            maxLength = j-i-1;
            index = i+1;
        }
    }
}
