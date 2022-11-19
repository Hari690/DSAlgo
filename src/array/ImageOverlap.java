package array;

/**
 * You are given two images, img1 and img2, represented as binary, square matrices of size n x n. A binary matrix has only 0s and 1s as values.
 * We translate one image however we choose by sliding all the 1 bits left, right, up, and/or down any number of units. We then place it on top of the other image. We can then calculate the overlap by counting the number of positions that have a 1 in both images.
 * Note also that a translation does not include any kind of rotation. Any 1 bits that are translated outside of the matrix borders are erased.
 * Return the largest possible overlap.
 * Input: img1 = [[1,1,0],[0,1,0],[0,1,0]], img2 = [[0,0,0],[0,1,1],[0,0,1]]
 * Output: 3
 * Explanation: We translate img1 to right by 1 unit and down by 1 unit.
 */
public class ImageOverlap {
    public int largestOverlap(int[][] a, int[][] b) {
        int max = 0;
        for (int x = -a.length+1; x < a.length; x++) {
            for (int y = -a[0].length+1; y < a.length; y++) {
                max = Math.max(max, overlapCount(a, b, x, y));
            }
        }
        return max;
    }

    private int overlapCount(int[][] a, int[][] b, int x, int y) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (i - x >= 0 && j - y >= 0 && i - x < a.length && j - y < a[0].length) {
                    if (a[i][j] == b[i - x][j - y] && a[i][j] == 1) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
