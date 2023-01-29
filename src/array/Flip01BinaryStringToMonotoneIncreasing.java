package array;

/**
 * A binary string is monotone increasing if it consists of some number of 0's (possibly none), followed by some number of 1's (also possibly none).
 * You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.
 * Return the minimum number of flips to make s monotone increasing.
 **
 * Example 1:
 * Input: s = "00110"
 * Output: 1
 * Explanation: We flip the last digit to get 00111.
 *
 * Example 2:
 * Input: s = "010110"
 * Output: 2
 * Explanation: We flip to get 011111, or alternatively 000111.
 *
 * Example 3:
 *
 * Input: s = "00011000"
 * Output: 2
 * Explanation: We flip to get 00000000.
 */
public class Flip01BinaryStringToMonotoneIncreasing {

    /*
        We loop through the string.
        If we got a 1, go on. No need to flip. We just increment the 1 counter.
        If we got a 0, we increment the flips counter.
        Now, we have two options. Either to flip the new zero to one or to flip all previous ones to zeros.
        So we take the min between flips and counter.
     */
    public int minFlipsMonoIncr(String s) {
        int i=0;
        while(i<s.length() && s.charAt(i)=='0') {
            i++;
        }

        int zeroesToOnes = 0,ones = 0;
        for(int k=i;k<s.length();k++) {
            if(s.charAt(k)=='0')
                zeroesToOnes++;
            else
                ones++;
            if(zeroesToOnes>ones)
                zeroesToOnes = ones;
        }

        return zeroesToOnes; // can be also ones to 0, both ways. Look at example
    }

    public static void main(String[] args) {
        Flip01BinaryStringToMonotoneIncreasing flipStringToMonotoneIncreasing = new Flip01BinaryStringToMonotoneIncreasing();
        //flipStringToMonotoneIncreasing.minFlipsMonoIncr("10011111110010111011");
        flipStringToMonotoneIncreasing.minFlipsMonoIncr("10011");
    }
}
