package array;

public class GCDOfStrings {
    public String gcdOfStrings(String str1, String str2) {
        if(str2.length()>str1.length())
            return gcdOfStrings(str2, str1);

        // str1 is longer
        for(int j=str2.length();j>=0;j--) {
            String sub = str2.substring(0,j);
            if(str1.contains(sub) && sub.length()>0) {
                String val = sub;
                while(val.length()<=str1.length()) {
                    if(str1.equals(val))
                        return sub;
                    System.out.println(val);
                    val+=sub;
                }
            }
        }

        return "";
    }

    /*
        If there exists a valid gcd (length > 0), for each input string the gcd will repeat throughout the entire string.
        therefore it must also repeat throughout the entire concatenation of both strings.
        since it repeats through the entirety of the concatenation, the order of the concatenation must not matter. take examples 1 and 4:
        ABCABC + ABC == ABC + ABCABC
        ABCDEF + ABC != ABC + ABCDEF
        once it is established there is a valid gcd, the number of characters to repeat will be the gcd of the lengths
     */
    public String gcdOfStringsEucledian(String str1, String str2) {
        if (!(str1+str2).equals(str2+str1))  return "";

        int gcdVal = gcd(str1.length() , str2.length());
        return str2.substring(0, gcdVal);
    }

    public static int gcd(int p, int q) {
        if (q == 0) return p;
        else return gcd(q, p % q);
    }

    public static void main(String[] args) {
        GCDOfStrings gcdOfStrings = new GCDOfStrings();

        gcdOfStrings.gcdOfStrings("TAUXXTAUXXTAUXXTAUXXTAUXX", "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX");
    }
}
