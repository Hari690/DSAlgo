package array;

/**
 * Given an array of integers arr, return true if we can partition the array into three non-empty parts with equal sums.
 *
 * Formally, we can partition the array if we can find indexes i + 1 < j with (arr[0] + arr[1] + ... + arr[i] == arr[i + 1] + arr[i + 2]
 * + ... + arr[j - 1] == arr[j] + arr[j + 1] + ... + arr[arr.length - 1])
 */
public class PartitionThreeEqual {
    public boolean canThreePartsEqualSum(int[] arr) {
        int total = 0;
        for(int i=0;i<arr.length;i++) {
            total+=arr[i];
        }
        if(total%3!=0)
            return false;
        int partSum = total/3;
        int mainTotal = total;
        total = 0;
        int count = 0;
        int i=0;
        for(i=0;i<arr.length;i++) {
            total+=arr[i];
            if(total==partSum) {
                total = 0;
                count++;
            }
        }
        return count>=3;
    }
}
