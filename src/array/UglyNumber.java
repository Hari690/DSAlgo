package array;

import java.util.Arrays;
import java.util.List;

/**
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 *
 * Given an integer n, return true if n is an ugly number.
 */
public class UglyNumber {
    public boolean isUgly(int n) {
        if(n<=0)
            return false;

        List<Integer> factors = Arrays.asList(2,3,5);

        for(int factor: factors) {
            while(n%factor==0)
                n/=factor;
        }

        return n==1;
    }
}
