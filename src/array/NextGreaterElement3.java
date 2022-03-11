package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a positive integer n, find the smallest integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive integer exists, return -1.
 *
 * Note that the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.
 * Example 1:
 *
 * Input: n = 12
 * Output: 21
 * Example 2:
 *
 * Input: n = 21
 * Output: -1
 */
public class NextGreaterElement3 {
    /*
        // 12454
     */
    public int nextGreaterElement(int n) {
        if(n==Integer.MAX_VALUE)
            return -1;
        int tempno = n;
        List<Integer> list = new ArrayList<>();
        while(n!=0) {
            list.add(n%10);
            n=n/10;
        }
        int[] arr = new int[list.size()];
        // copy to array backwards.
        int i=list.size()-1;
        for(int no: list)
            arr[i--]=no;
        // find first element from right which breaks the pattern i.e there's a peak.
        i=arr.length-2;
        while(i>=0 && arr[i+1]<=arr[i]) i--;
        if(i>=0) {
            int j=arr.length-1;
            // find first element from end which is greater than the i'th character and swap with that.
            while(arr[i]>=arr[j]) j--;
            swap(arr,i,j);
            // sort from next index until end in descending order.
            Arrays.sort(arr,i+1,arr.length);
        }
        long num=0;
        i=0;
        while(i<arr.length) {
            num=num*10+arr[i++];
        }
        return ((int)num==tempno||num>(long)(Integer.MAX_VALUE))?-1:(int)num;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void main(String[] args) {
        NextGreaterElement3 solution = new NextGreaterElement3();
        System.out.println(solution.nextGreaterElement(12443322));
    }
}
