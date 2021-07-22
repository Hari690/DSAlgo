package problems;

public class ReverseString2 {
    public String reverseStr(String s, int k) {
        int i=0;
        int n;
        char str[] = s.toCharArray();
        int j=Math.min(i+k-1,s.length()-1);
        while (true) {
            n=i;
            while (i<j){
                char temp=str[i];
                str[i]=str[j];
                str[j]=temp;
                i++;
                j--;
            }
            i = n + 2*k;
            if(i>=s.length()) {
                break;
            }
        }
        return new String(str);
    }

    public static void main(String[] args) {
        new ReverseString2().reverseStr("abcdefg", 9);
    }
}
