package recursion;

public class Subsequence {
    public boolean isSubsequence(String s, String t) {
        if(s.length()==0) return true;
        if(t.length()==0) return false;
        if ( s.charAt(0)==t.charAt(0))
            return isSubsequence(s.substring(1),t.substring(1));
        return isSubsequence(s,t.substring(1));
    }

    public boolean isSubsequenceI(String s, String t) {
        if (s.length() == 0) return true;
        int indexS = 0, indexT = 0;
        while (indexT < t.length()) {
            if (t.charAt(indexT) == s.charAt(indexS)) {
                indexS++;
                if (indexS == s.length()) return true;
            }
            indexT++;
        }
        return false;
    }
}
