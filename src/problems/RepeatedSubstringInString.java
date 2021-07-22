package problems;

/**
 * Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
 */
public class RepeatedSubstringInString {
    public boolean repeatedSubstringPattern(String str) {
        int l = str.length();
        for(int i=l/2;i>=1;i--) {
            if(l%i==0) {
                int m = l/i;
                String subS = str.substring(0,i);
                int j;
                for(j=0;j<m;j++) {
                    if(!subS.equals(str.substring(j*i,i+j*i))) break;
                }
                if(j==m)
                    return true;
            }
        }
        return false;
    }
}
