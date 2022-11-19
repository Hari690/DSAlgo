package array;

import java.util.Arrays;

/**
 * Give an array of salaries. The total salary has a budget. At the beginning, the total salary of employees is larger than the budget. It is required to find the number k, and reduce all the salaries larger than k to k, such that the total salary is exactly equal to the budget.
 *
 * Example 1:
 * Input: salaries = [100, 300, 200, 400], budget = 800
 * Output: 250
 * Explanation: k should be 250, so the total salary after the reduction 100 + 250 + 200 + 250 is exactly equal to 800.
 * You can assume that solution always exists.
 *
 * Sort - [100, 200, 300, 400 ]
 * Replace maximum value with k, [ 100, 200, 300, k ]
 * Calculate k , k = (800-100-200-300) = 200
 * If k is less than the next max, Repeat from step 2 , else done.
 * 2*. [100, 200, k, k ]
 * 3* Calculate k , k = (800-100-200)/2 = 250
 * 4* Done..
 */
public class AdjustSalaryToWithinCap {
    public int[] adjustSalary(int[] sal, int budget) {
        Arrays.sort(sal);

        int maxIndex = sal.length - 1;
        int sum = sum(sal, maxIndex);
        int rem = budget - sum;
        int k = rem / (sal.length-maxIndex);
        while(maxIndex-1>=0 && k<sal[maxIndex-1]) {
            maxIndex--;
            sum = sum(sal, maxIndex);
            rem = budget - sum;
            k = rem / (sal.length-maxIndex);
        }

        for(int i=0;i<sal.length;i++) {
            if(sal[i]>k)
                sal[i]=k;
        }
        return sal;
    }

    private int sum(int[] arr, int k) {
        int sum = 0;
        for(int i=0;i<k;i++) {
            sum+=arr[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        AdjustSalaryToWithinCap adjustSalaryToWithinCap = new AdjustSalaryToWithinCap();
        int[] arr = {100,300,200,400};
        int[] res = adjustSalaryToWithinCap.adjustSalary(arr, 800);
        Arrays.stream(res).forEach(System.out::println);
    }
}
