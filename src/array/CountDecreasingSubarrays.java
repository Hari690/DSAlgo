package array;

public class CountDecreasingSubarrays {
    long countDecreasingSubarrays(int[] arr) {
        long res = 1;
        int i = 0;
        for(int j = 1; j < arr.length; j++){ // Each loop add how many subarrays end with j
            if(arr[j] < arr[j-1]){
                res += j-i+1; // Any array from [i...j] to [j..j] is decreasing
            } else{
                i = j;
                res += 1; // Only [j..j] is decreasing
            }
        }
        return res;
    }
}
