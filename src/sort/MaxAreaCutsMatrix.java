package sort;

import java.util.Arrays;

/**
 * You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:
 *
 * horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
 * verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
 * Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts.
 * Since the answer can be a large number, return this modulo 109 + 7.
 *
 * Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
 * Output: 4
 * Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts.
 * After you cut the cake, the green piece of cake has the maximum area.
 *
 * Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
 * Output: 6
 * Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts.
 * After you cut the cake, the green and yellow pieces of cake have the maximum area.
 */
public class MaxAreaCutsMatrix {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int maxWidth=0;
        int maxHeight=0;
        int i=0;

        for(i=0;i<verticalCuts.length;i++) {
            maxWidth = Math.max(maxWidth,(i>0)?(verticalCuts[i]-verticalCuts[i-1]):verticalCuts[i]);
        }
        maxWidth = Math.max(maxWidth,w-verticalCuts[i-1]);


        for(i=0;i<horizontalCuts.length;i++) {
            maxHeight = Math.max(maxHeight,(i>0)?(horizontalCuts[i]-horizontalCuts[i-1]):horizontalCuts[i]);

        }
        maxHeight = Math.max(maxHeight,h-horizontalCuts[i-1]);

        return (int)(1l*maxWidth*maxHeight%1000000007);
    }
}
