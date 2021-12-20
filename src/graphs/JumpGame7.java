package graphs;

import java.util.LinkedList;
import java.util.Queue;

public class JumpGame7 {

    public boolean canReach(String s, int minJump, int maxJump) {
        // boolean[] dp=new boolean[s.length()];
        // dp[0] =true;
        // for(int i=0;i<s.length();i++) {
        //     if(dp[i]==true) {
        //         for(int j=i+minJump;j<=Math.min(i+maxJump, s.length()-1);j++) {
        //         if(dp[j])
        //             continue;
        //         if(s.charAt(j)=='0')
        //             dp[j]=true;
        //         }
        //     }
        // }
        // return dp[s.length()-1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int maxRight=0;
        while(queue.size()>0) {
            int i = queue.poll();
            for(int j=Math.max(i+minJump,maxRight);j<=Math.min(i+maxJump, s.length()-1);j++) {
                if(s.charAt(j)=='0') {
                    if(j==s.length()-1)
                        return true;
                    queue.add(j);
                }
                maxRight = j;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new JumpGame7().canReach("0000000000",1,1);
    }
}
