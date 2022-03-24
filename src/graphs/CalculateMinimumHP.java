package graphs;

/**
 * The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of m x n rooms laid out in a 2D grid. Our valiant knight was initially positioned in the top-left room and must fight his way through dungeon to rescue the princess.
 *
 * The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
 *
 * Some of the rooms are guarded by demons (represented by negative integers), so the knight loses health upon entering these rooms; other rooms are either empty (represented as 0) or contain magic orbs that increase the knight's health (represented by positive integers).
 *
 * To reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 *
 * Return the knight's minimum initial health so that he can rescue the princess.
 *
 * Note that any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 */
public class CalculateMinimumHP {

    /*
        Intuition go from bottom right to top left.
     */
    public int calculateMinimumHP(int[][] d) {
        int[][] dp = new int[d.length][d[0].length];

        for( int i=d.length-1;i>=0;i--) {
            for( int j=d[0].length-1;j>=0;j--) {
                int val = 0;
                if(i==d.length-1 && j==d[0].length-1) {
                    if(d[i][j]<0)
                        dp[i][j]=Math.abs(d[i][j]);
                } else if(j==d[0].length-1) {
                    val = d[i][j]-dp[i+1][j];
                    dp[i][j]=(val>0)?0:Math.abs(val);
                } else if (i==d.length-1) {
                    val = d[i][j]-dp[i][j+1];
                    dp[i][j]=(val>0)?0:Math.abs(val);
                } else {
                    val=d[i][j]-Math.min(dp[i+1][j],dp[i][j+1]);
                    dp[i][j]=(val>0)?0:Math.abs(val);
                }
            }
        }
        return dp[0][0]+1;
    }
}
