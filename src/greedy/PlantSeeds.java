package greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You have n flower seeds. Every seed must be planted first before it can begin to grow, then bloom. Planting a seed takes time and so does the growth of a seed. You are given two 0-indexed integer arrays plantTime and growTime, of length n each:
 * plantTime[i] is the number of full days it takes you to plant the ith seed. Every day, you can work on planting exactly one seed. You do not have to work on planting the same seed on consecutive days, but the planting of a seed is not complete until you have worked plantTime[i] days on planting it in total.
 * growTime[i] is the number of full days it takes the ith seed to grow after being completely planted. After the last day of its growth, the flower blooms and stays bloomed forever.
 * From the beginning of day 0, you can plant the seeds in any order.
 * Return the earliest possible day where all seeds are blooming.
 *
 * Input: plantTime = [1,4,3], growTime = [2,3,1]
 * Output: 9
 * Explanation: The grayed out pots represent planting days, colored pots represent growing days, and the flower represents the day it blooms.
 * One optimal way is:
 * On day 0, plant the 0th seed. The seed grows for 2 full days and blooms on day 3.
 * On days 1, 2, 3, and 4, plant the 1st seed. The seed grows for 3 full days and blooms on day 8.
 * On days 5, 6, and 7, plant the 2nd seed. The seed grows for 1 full day and blooms on day 9.
 * Thus, on day 9, all the seeds are blooming.
 *
 *
 */
public class PlantSeeds {

    /*
        By sorting based on plantTime we can see result will be not optimal.
        By sorting with largest growthTime we can greedily get the result by adding plantTime in every step.
        Also Imagine all flowers grow first, and then you need to start planting.
     */
    public int earliestFullBloom(int[] plantTimes, int[] growTimes) {
        List<Seed> list = new ArrayList<>();

        for(int i=0;i<plantTimes.length;i++) {
            list.add(new Seed(plantTimes[i], growTimes[i]));
        }

        Collections.sort(list, (a, b) -> b.growTime - a.growTime);

        int max = 0;
        int plantTime = 0;

        for(int i=0;i<list.size();i++) {
            plantTime+=list.get(i).plantTime;
            max = Math.max(max, plantTime+list.get(i).growTime);
        }

        return max;
    }

    class Seed {
        int plantTime;
        int growTime;
        Seed(int plantTime, int growTime) {
            this.plantTime = plantTime;
            this.growTime = growTime;
        }
    }
}
