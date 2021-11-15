package array;

import java.util.Random;

/**
 * Given the API rand7() that generates a uniform random integer in the range [1, 7], write a function rand10() that generates a uniform
 * random integer in the range [1, 10]. You can only call the API rand7(), and you shouldn't call any other API. Please do not use a
 * language's built-in random API.
 *
 * Each test case will have one internal argument n, the number of times that your implemented function rand10() will be called while
 * testing. Note that this is not an argument passed to rand10().
 */
public class Rand10FromRand7 {
    Random random = new Random();
    public int rand10() {
        // roll once and spread into 5 values.
        // roll again and see if we can return one of the 5 or return a+1.
        int a = rand7();
        while(a>5) {
            a=rand7();
        }
        int b = rand7();
        while(b>6) {
            b=rand7();
        }
        if(b<=3)
            return a;
        else
            return a+5;
    }

    private int rand7() {
        return random.nextInt(7)+1;
    }
}
