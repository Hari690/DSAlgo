package problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FrogJump {

    public static void main(String[] args) {
        int[] stones = {0,2};
        new FrogJump().canCross(stones);
    }

    public boolean canCross(int[] stones) {

        boolean[] stonesReachable = new boolean[stones.length];
        Map<Integer, Set<Integer>> stoneJumps = new HashMap<>();
        stonesReachable[0] = true;
        Set<Integer> jumps;

        if(stones.length>1 && stones[1]-stones[0]<2) {
            jumps = stoneJumps.getOrDefault(stones[0],new HashSet<>());
            jumps.add(1);
            stoneJumps.put(stones[0], jumps);
        }

        for(int i=1;i<stones.length;i++) {
            for(int j=i-1;j>=0 ;j--) {
                if(stoneJumps.containsKey(stones[j])) {
                    for(int jumpLength : stoneJumps.get(stones[j])) {
                        if(stonesReachable[j] && ((stones[j] +jumpLength == stones[i]) ||
                            (stones[j]+jumpLength+1 == stones[i]) ||  (stones[j]+jumpLength-1 == stones[i]))) {
                            stonesReachable[i] = true;
                            if(stones[j]+jumpLength+1 == stones[i]) {
                                jumpLength = jumpLength + 1;
                            }
                            if(stones[j]+jumpLength-1 == stones[i]) {
                                jumpLength = jumpLength - 1;
                            }
                            jumps = stoneJumps.getOrDefault(stones[i],new HashSet<>());
                            jumps.add(jumpLength);
                            stoneJumps.put(stones[i],jumps);
                        }
                    }
                }
            }
        }
        return stonesReachable[stones.length-1];
    }
}
