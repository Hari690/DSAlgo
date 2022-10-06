package dynamicprogramming;

public class MinCostRemoveConsecutiveColors {
    public int minCost(String colors, int[] neededTime) {
        int minCost = 0;
        char prev = '*';
        int prevCost = 0;
        for(int i=0;i<colors.length();i++) {
            char c = colors.charAt(i);
            if(prev==c) {
                // remove prev character
                if(prevCost<neededTime[i]) {
                    minCost+=prevCost;
                    prevCost=neededTime[i];
                    // remove current character
                } else {
                    minCost+=neededTime[i];
                }
            } else {
                prev = c;
                prevCost = neededTime[i];
            }
        }
        return minCost;
    }

    public static void main(String[] args) {
        MinCostRemoveConsecutiveColors minCostRemoveConsecutiveColors = new MinCostRemoveConsecutiveColors();
        minCostRemoveConsecutiveColors.minCost("aaabbbabbbb", new int[]{3,5,10,7,5,3,5,5,4,8,1});
    }
}
