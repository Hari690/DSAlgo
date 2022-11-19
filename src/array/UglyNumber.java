package array;

import java.util.Arrays;
import java.util.List;

/**
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 *
 * Given an integer n, return true if n is an ugly number.
 */
public class UglyNumber {
    public boolean isUgly(int num) {
        if (num <= 0) {return false;}
        if (num == 1) {return true;}
        if (num % 2 == 0) {
            return isUgly(num/2);
        }
        if (num % 3 == 0) {
            return isUgly(num/3);
        }
        if (num % 5 == 0) {
            return isUgly(num/5);
        }
        return false;
    }
}
