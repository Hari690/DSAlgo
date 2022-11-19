package array;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

/**
 * Given a class like the following:
 * class Randomizer {
 *
 * 	int n, k;
 * 	Randomizer(int n, int k) {
 * 		this.n = n;
 * 		this.k = k;
 * }
 *
 *  	int getRandom() {
 *        }
 * }
 * Implement the getRandom method. getRandom returns a random number in the range [1,n] but a number once generated should not be generated again in next k turns. For example:
 *
 * n = 5
 * k = 3
 *
 * getRandom() returns 2 (assume)
 * getRandom() return x
 * getRandom() returns y
 * getRandom() returns z
 * getRandom() returns w
 *
 * now as per the constraint x, y and z cannot be 2. But w can be 2. Similarly y, z and w cannot be x.
 */
public class KRandomizer {
    int n, k;
    Queue<Integer> arrayDeque;
    KRandomizer(int n, int k) {
        this.n = n;
        this.k = k;
        this.arrayDeque = new ArrayDeque<>();
    }

    int getRandom() {
        Random random = new Random();
        int num = random.nextInt(n) +1;
        while(arrayDeque.contains(num)){
            num = random.nextInt(n)+1;
        }
        arrayDeque.offer(num);
        if(arrayDeque.size() > k)
            arrayDeque.poll();
        return num;
    }

    public static void main(String[] args) {
        KRandomizer randomizer = new KRandomizer(5,3);
        System.out.println(randomizer.getRandom());
        System.out.println(randomizer.getRandom());
        System.out.println(randomizer.getRandom());
        System.out.println(randomizer.getRandom());
        System.out.println(randomizer.getRandom());
        System.out.println(randomizer.getRandom());
        System.out.println(randomizer.getRandom());
    }
}
