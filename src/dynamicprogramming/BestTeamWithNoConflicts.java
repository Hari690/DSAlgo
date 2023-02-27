package dynamicprogramming;

import java.util.Arrays;

/**
 * You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with the highest overall score. The score of the team is the sum of scores of all the players in the team.
 * However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player has a strictly higher score than an older player. A conflict does not occur between players of the same age.
 * Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the ith player, respectively, return the highest overall score of all possible basketball teams.
 *
 * Example 1:
 * Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
 * Output: 34
 * Explanation: You can choose all the players.
 *
 * Example 2:
 * Input: scores = [4,5,6,5], ages = [2,1,2,1]
 * Output: 16
 * Explanation: It is best to choose the last 3 players. Notice that you are allowed to choose multiple people of the same age.
 *
 * Example 3:
 * Input: scores = [1,2,3,5], ages = [8,9,10,1]
 * Output: 6
 * Explanation: It is best to choose the first 3 players.
 */
public class BestTeamWithNoConflicts {

    /*
    Similar to longest increasing subsequence problem

    Fairly easy DP
    Idea is to first sort the players by their age in ascending order so that we don't have to always check both the scores and the age to see whether these two players
    can be in the same team.
    We sort first in the descending order of the ages.
    Now we know that for any player i, we can choose any player from 0 to i-1 as long as that player has higher score than this i-th player.
    dp[i] stores the maximum score that can be obtained when i-th player is included and all other players are between indices 0 and i-1.
    Once we get the answer for all indices, we can simply find the max and that will be the answer.
    Code would help understand this even more easily.
     */
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = ages.length;
        int[][] candidate = new int[n][2];

        for(int i=0; i<n; i++) {
            candidate[i][0] = ages[i];
            candidate[i][1] = scores[i];
        }
        Arrays.sort(candidate, (a,b) -> a[0] == b[0] ? a[1]-b[1] : a[0]-b[0]);
        int[] dp = new int[n];
        dp[0] = candidate[0][1];
        int max = dp[0];
        for(int i=1; i<n; i++) {
            // atleast it will be that number alone
            dp[i] = candidate[i][1];
            for(int j=0; j<i; j++) {
                if(candidate[j][1] <= candidate[i][1]) {
                    // add all sub-problems before with that number to find best value for that number
                    dp[i] = Math.max(dp[i], candidate[i][1]+dp[j]);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        BestTeamWithNoConflicts bestTeamWithNoConflicts = new BestTeamWithNoConflicts();
        int[] scores = {1,2,3,5};
        int[] ages = {8,9,10,1};
        System.out.println(bestTeamWithNoConflicts.bestTeamScore(scores, ages));
    }
}
