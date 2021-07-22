package backtracking;

import java.util.HashMap;
import java.util.Map;

public class FrogJump {
    Map<Integer,Integer> stonesMap = new HashMap();
    public boolean canCross(int[] stones) {
        if( stones.length==2 && (stones[0]+1)!=stones[1]) {
            return false;
        }
        for ( int i=0;i<stones.length;i++) {
            stonesMap.put(stones[i],i);
        }
        return canCross(stones, 1,0, stones[stones.length-1]);
    }

    private boolean canCross(int[] stones, int jump, int index, int finalStone) {
        System.out.println(jump);
        if((stones[index]+jump) == finalStone
        || (stones[index]+jump-1) == finalStone
        || (stones[index]+jump+1) == finalStone) {
            return true;
        } else if (jump > finalStone || jump <= 0) {
            return false;
        } else {
            //int k=1;
            boolean result = false;
            //while(((index + k) < stones.length) && (stones[index+k]<=(stones[index]+jump+1))) {
                if (stonesMap.containsKey(stones[index] + jump)) {
                    result = canCross(stones, jump, stonesMap.get(stones[index] + jump), finalStone);
                }
                if (stonesMap.containsKey(stones[index] + jump - 1) && !result) {
                    result = canCross(stones, jump - 1, stonesMap.get(stones[index] + jump-1), finalStone);
                }
                if (stonesMap.containsKey(stones[index] + jump + 1) && !result) {
                    result = canCross(stones, jump + 1, stonesMap.get(stones[index] + jump+1), finalStone);
                }
                if ( result==true) {
                    return true;
                }
                //k++;
            //}
            return false;
        }
    }

    public static void main(String[] args) {
        FrogJump frogJump = new FrogJump();
        int[] stones = {0,1,3,5,6,8,12,17};
        System.out.println(frogJump.canCross(stones));
    }
}
