package dynamicprogramming;

/**
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 *
 * An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 */
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        int[][] arr = new int[s1.length()+1][s2.length()+1];
        if(s3.length()<s1.length()+s2.length() || s3.length()>s1.length()+s2.length())
            return false;
        return recur(s1,s2,s3,0,0,0, arr);
    }

    private boolean recur(String s1, String s2, String s3, int i, int j, int k, int[][] arr) {
        if(arr[i][j]==1)
            return true;

        if(arr[i][j]==2)
            return false;

        while(i<s1.length() || j<s2.length()) {
            if(i<s1.length() && s1.charAt(i)==s3.charAt(k) &&
                j<s2.length() && s2.charAt(j)==s3.charAt(k)) {
                boolean check1 = recur(s1,s2,s3,i+1,j,k+1, arr);
                boolean check2 = recur(s1,s2,s3,i,j+1,k+1, arr);
                if(check1)
                    arr[i+1][j]=1;
                else if(check2)
                    arr[i][j+1]=1;
                else{
                    arr[i+1][j]=2;
                    arr[i][j+1]=2;
                }
                return check1 || check2;
            }
            if(i<s1.length() && s1.charAt(i)==s3.charAt(k))
                i++;
            else if (j<s2.length() && s2.charAt(j)==s3.charAt(k))
                j++;
            else {
                return false;
            }
            k++;
        }
        return true;
    }
}
