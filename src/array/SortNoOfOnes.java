package array;

import java.util.Arrays;

/**
 * Given an integer array arr. You have to sort the integers in the array in ascending order by the number of 1's in their binary
 * representation and in case of two or more integers have the same number of 1's you have to sort them in ascending order.
 *
 * Return the sorted array.
 */
public class SortNoOfOnes {
    public int[] sortByBits(int[] arr) {
        return Arrays.stream(arr).boxed().sorted((a, b)-> {
            int onesA = noOfOnes(a);
            int onesB = noOfOnes(b);
            if(onesA==onesB) {
                return a-b;
            } else
                return onesA-onesB;
        }).mapToInt(Integer::intValue).toArray();
    }

    private int noOfOnes(Integer a) {
        int count=0;
        while(a!=0) {
            count+=a&1;
            a>>=1;
        }
        return count;
    }
}
