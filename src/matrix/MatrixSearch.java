package matrix;

/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
 */
public class MatrixSearch {

    public static void main(String[] args) {
        int[][] matrix={{1,3}};
        new MatrixSearch().searchMatrix(matrix,3);
    }

    boolean binarySearch1D(int arr[], int K)
    {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // if element found return true
            if (arr[mid] == K) {
                return true;
            }

            // if middle less than K then
            // skip the left part of the
            // array else skip the right part
            if (arr[mid] < K) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        // if not found return false
        return false;
    }

    public boolean searchMatrix(int[][] matrix, int K) {
        if(matrix.length!=0 && matrix[0].length==0){
            return false;
        }
        int low = 0;
        int high = matrix.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;


            // if the element lies in the range
            // of this row then call
            // 1-D binary search on this row
            if (K >= matrix[mid][0]
                    && K <= matrix[mid][matrix[mid].length - 1]) {
                return binarySearch1D(matrix[mid], K);
            }

            // if the element is less then the
            // starting element of that row then
            // search in upper rows else search
            // in the lower rows
            if (K < matrix[mid][0]) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }

        // if not found
        return false;
    }
}
