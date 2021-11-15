package array;

/**
 * Given a fixed-length integer array arr, duplicate each occurrence of zero, shifting the remaining elements to the right.
 *
 * Note that elements beyond the length of the original array are not written. Do the above modifications to the input array in place and
 * do not return anything.
 */
public class DuplicateZeroes {
    public void duplicateZeros(int[] arr) {
        int j=0;
        for(int i=0;i<arr.length;i++) {
            if(arr[j]==0) {
                arr[j++]=0;
                if(j<arr.length)
                    arr[j++]=0;
            }
            else
                arr[j++]=arr[i];
            if(j==arr.length)
                break;
        }
    }

    public static void main(String[] args) {
        DuplicateZeroes duplicateZeroes = new DuplicateZeroes();
        int[] arr = {1,0,2,3,0,4,5,0};
        duplicateZeroes.duplicateZeros(arr);
    }
}
