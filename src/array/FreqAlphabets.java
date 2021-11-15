package array;

/**
 * Given a string s formed by digits ('0' - '9') and '#' . We want to map s to English lowercase characters as follows:
 *
 * Characters ('a' to 'i') are represented by ('1' to '9') respectively.
 * Characters ('j' to 'z') are represented by ('10#' to '26#') respectively.
 * Return the string formed after mapping.
 *
 * It's guaranteed that a unique mapping will always exist.
 */
public class FreqAlphabets {
    public String freqAlphabets(String s) {
        StringBuilder output = new StringBuilder();
        for(int i=s.length()-1;i>=0;i--) {
            if(s.charAt(i)=='#') {
                String part = s.substring(i-2,i);
                output.append(String.valueOf((char)(Integer.parseInt(part)+'a')));
            } else {
                output.append(String.valueOf((char)(Integer.parseInt(String.valueOf(s.charAt(i)))+'a')));
            }
        }
        return output.toString();
    }
}
