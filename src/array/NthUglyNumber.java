package array;

/**
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 * Given an integer n, return the nth ugly number.
 *
 * Example 1:
 * Input: n = 10
 * Output: 12
 * Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 * Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 */
public class NthUglyNumber {

    /*
    Ugly numbers are numbers whose only prime factors are 2, 3 or 5. The sequence 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, … shows the first 11 ugly numbers. By convention, 1 is included.
    Given a number n, the task is to find n’th Ugly number.
    The way they avoid 7 is by multiplying numbers already present in the array. Each factor multiplies the last checked index of that factor.
    Checking number in the array is safe because we already know these numbers are divisible by one of the factors.
    I think the key here is as you mentioned, "including nums with same value.". factor2 and factor3 may both have value = 6,
    but we bump both "6"s together, thus the duplicated 6 won't cause any problem.

     */
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        for(int i=1;i<n;i++){
            int min = Math.min(Math.min(factor2,factor3),factor5);
            ugly[i] = min;
            if(factor2 == min)
                factor2 = 2*ugly[++index2];
            if(factor3 == min)
                factor3 = 3*ugly[++index3];
            if(factor5 == min)
                factor5 = 5*ugly[++index5];
        }
        return ugly[n-1];
    }

    public static void main(String[] args) {
        NthUglyNumber solution = new NthUglyNumber();
        solution.nthUglyNumber(12);
    }
}
