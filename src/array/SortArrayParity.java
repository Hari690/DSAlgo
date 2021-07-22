package array;

public class SortArrayParity {
    public int[] sortArrayByParity(int[] A) {
        int index = 0;
        int i = 0;
        while(i<A.length) {
            if( A[i]%2 == 0) {
                int temp = A[i];
                A[i] = A[index];
                A[index++] = temp;
            }
            i++;
        }
        return A;
    }
}
