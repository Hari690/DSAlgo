package bits;

/**
 * Given two integers a and b, return the sum of the two integers without using the operators + and -.
 *
 */
/*
 XOR followed by AND.
 */
public class AddNumbers {
    // Use & operator to get carry.
    // Use ^ to perform addition without carry.
    public int getSum(int a, int b) {
        while( b!=0) {
            int carry = a&b;
            a = a^b;
            // no to add is carry left shifted by 1.
            b = carry<<1;
        }
        return a;
    }
}
