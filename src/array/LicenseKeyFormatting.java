package array;

/**
 * You are given a license key represented as a string s that consists of only alphanumeric characters and dashes. The string is
 * separated into n + 1 groups by n dashes. You are also given an integer k.
 *
 * We want to reformat the string s such that each group contains exactly k characters, except for the first group, which could be
 * shorter than k but still must contain at least one character. Furthermore, there must be a dash inserted between two groups, and you
 * should convert all lowercase letters to uppercase.
 *
 * Return the reformatted license key.
 */
public class LicenseKeyFormatting {

    public String licenseKeyFormatting(String s, int k) {
        StringBuilder output = new StringBuilder();
        int j=0;
        for( int i=s.length()-1;i>=0;i--) {
            if( s.charAt(i)!='-') {
                if ( j==k) {
                    output.append("-");
                    j =0;
                }
                output.append(Character.toUpperCase(s.charAt(i)));
                j++;
            }
        }
        return output.reverse().toString();
    }
}
