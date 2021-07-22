package problems;

import java.util.Random;

public class Rand10From7 {

    /**
     * The rand7() API is already defined in the parent class SolBase.
     * public int rand7();
     * @return a random integer in the range 1 to 7
     */

    /*
        call fn twice and use it in 2 ways.
     */
    public int rand10() {
        int v1 = rand7(), v2 = rand7();
        while (v1 > 5) {
            v1 = rand7();
        }
        while (v2 == 7) {
            v2 = rand7();
        }
        return (v2 <= 3) ? v1 : v1 + 5;
    }

    private int rand7() {
        return new Random().nextInt(7);
    }

}
