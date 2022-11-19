package array;

public class ShortestPalindrome {

    /*
        This can also be done by KMP by appending reverse first, finding longest prefix which is also a suffix.
        Reducing that from the reversed part of string and appending the remaining at the front.
     */
    public String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s);
        String t = sb.reverse().toString();
        for (int i = 0; i <= t.length(); i++) {
            if (s.startsWith(t.substring(i))) {
                return t.substring(0, i) + s;
            }
        }
        return t + s;
    }

    public static void main(String[] args) {
        ShortestPalindrome shortestPalindrome = new ShortestPalindrome();
        shortestPalindrome.shortestPalindrome("aacecaaa");
    }
}
