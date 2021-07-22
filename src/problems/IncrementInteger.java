package problems;

/**
 * Given a non-empty array of decimal digits representing a non-negative integer, increment one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contains a single
 * digit.
 *
 * You may assume the integer does not contain any leading zero, except the number 0 itself.x§
 */
public class IncrementInteger {

    public static void main(String[] args) {
        IncrementInteger ii = new IncrementInteger();
        int[] digits = {9,8,7};
        int[] result = ii.plusOne(digits);
        for(int i=0;i<result.length;i++) {
            System.out.println(result[i]);
        }
    }

    public int[] plusOne(int[] digits) {

//        if(digits.length==1) {
//            digits[0]=digits[0]+1;
//            return digits;
//        }

        int carry=0;
        int sum = 1;
        for(int i=digits.length-1;i>=0;i--) {
            sum = sum + carry + digits[i];
            if(sum==10) {
                digits[i]=0;
                carry=1;
                sum = 0;
            } else {
                digits[i]=sum;
                carry = 0;
                break;
            }
        }
        if(carry==1) {
            int[] output = new int[digits.length+1];
            for(int i=digits.length-1;i>=0;i--) {
                output[i] = digits[i];
            }
            output[0] = 1;
            return output;
        }
        return digits;
    }
}
