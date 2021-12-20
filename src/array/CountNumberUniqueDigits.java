package array;

public class CountNumberUniqueDigits {
    public int countNumbersWithUniqueDigits(int n) {
        if(n==0)
            return 1;
        int sum = 10;
        int i=n;
        int digits = 9;
        int mul = 9;
        while(i>1) {
            digits = digits*mul;
            sum+=digits;
            i--;
            mul--;
        }
        return sum;
    }
}
