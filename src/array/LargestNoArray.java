package array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a list of non-negative integers nums, arrange them such that they form the largest number.
 *
 * Note: The result may be very large, so you need to return a string instead of an integer.
 *
 * Input: nums = [10,2]
 * Output: "210"
 */
public class LargestNoArray {

    // Custom comparator and sorting.
    public String largestNumber(int[] nums) {

        String[] strNums = new String[nums.length];

        int i=0;
        for( int num : nums) {
            strNums[i++] = String.valueOf(num);
        }

        Comparator<String> cmp = (o1, o2) -> {
            String s1 = o1+o2;
            String s2 = o2+o1;
            return s2.compareTo(s1);
        };

        Arrays.sort(strNums, cmp);

        if(strNums[0].charAt(0) == '0')
            return "0";

        StringBuilder output = new StringBuilder();

        for( String str : strNums) {
            output.append(str);
        }

        return output.toString();

    }

    public static void main(String[] args) {
        int[] arr = {10,2};
        new LargestNoArray().largestNumber(arr);
    }
}
