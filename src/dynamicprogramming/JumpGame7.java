package dynamicprogramming;

public class JumpGame7 {
    public boolean canReach(String s, int minJump, int maxJump) {
        boolean[] dp=new boolean[s.length()];
        dp[0] =true;
        for(int i=0;i<s.length();i++) {
            System.out.println(i);
            if(dp[i]==true) {
                for(int j=i+minJump;i<=Math.min(i+maxJump, s.length()-1);i++) {
                    if(s.charAt(j)=='0')
                        dp[j]=true;
                }
            }
        }
        for(boolean val : dp)
            System.out.println(val);
        return dp[s.length()-1];
    }

    public static void main(String[] args) {
        JumpGame7 game = new JumpGame7();
        game.canReach("011010", 2, 3);
    }
}
