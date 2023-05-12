package dynamicprogramming;

import java.util.Arrays;

public class LongestObstacleCourseEachPostionLISBinarySearch {
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int[] dp = new int[obstacles.length];
        Arrays.fill(dp, 1);
        for(int i=0;i<obstacles.length;i++) {

            // This can be further optimised using Binary search to make this O(log(n)) time.
            for(int j=i-1;j>=0;j--) {
                if(obstacles[i]>=obstacles[j]) {
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }

        return dp;
    }

    public static void main(String[] args) {
        LongestObstacleCourseEachPostionLISBinarySearch solution = new LongestObstacleCourseEachPostionLISBinarySearch();
        int[] obstacles = {1,2,3,2};
        Arrays.stream(solution.longestObstacleCourseAtEachPosition(obstacles)).forEach(System.out::println);
    }
}
