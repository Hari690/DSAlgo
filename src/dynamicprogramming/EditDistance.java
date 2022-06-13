package dynamicprogramming;

import java.util.Arrays;

/**
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 *
 * You have the following three operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 */
public class EditDistance {

    /*
        c a t
      m 1 2 3
      a 2 1 2
      t 3 2 1
     */
    public static void main(String[] args) {
        System.out.println(new EditDistance().minDistance("cat","mat"));
    }

    public int minDistance(String word1, String word2) {

        if(word1.length()==0 && word2.length()==0) {
            return 0;
        }

        if(word1.length()==0) {
            return word2.length();
        }

        if(word2.length()==0) {
            return word1.length();
        }

        int[][] output = new int[word1.length()+1][word2.length()+1];

        for(int i=0; i < output.length; i++){
            output[0][i] = i;
        }

        for(int i=0; i < output.length; i++){
            output[i][0] = i;
        }

        for(int i=1;i<=word1.toCharArray().length;i++) {
            for(int j=1;j<=word2.toCharArray().length;j++) {
                if(word1.charAt(i-1)==word2.charAt(j-1)) {
                        output[i][j] = output[i-1][j-1];
                    } else{
                        output[i][j] = Math.min(Math.min(output[i-1][j],output[i][j-1]),output[i-1][j-1])+1;
                    }
                }
        }
        return output[word1.length()][word2.length()];
    }

    public int minDistanceTopDown(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];
        for(int i=0;i<word1.length();i++)
            Arrays.fill(dp[i],-1);
        return findMinDistance(0,0,word1,word2, dp);
    }

    private int findMinDistance(int i, int j, String word1, String word2, int[][] dp) {
        if(i==word1.length() && j==word2.length())
            return 0;

        if(i==word1.length())
            return word2.length()-j;

        if(j==word2.length())
            return word1.length()-i;

        if(dp[i][j]!=-1)
            return dp[i][j];

        if(word1.charAt(i)==word2.charAt(j)) {
            int result = findMinDistance(i+1, j+1, word1, word2, dp);
            dp[i][j]=result;
            return result;
        } else {

            // insert
            int max1 = 1 + findMinDistance(i+1, j, word1, word2, dp);
            // delete
            int max2 = 1 + findMinDistance(i, j+1, word1, word2, dp);
            //replace
            int max3 = 1 + findMinDistance(i+1, j+1, word1, word2, dp);

            int result = Math.min(max1, Math.min(max2, max3));
            dp[i][j]=result;
            return result;
        }
    }
}
