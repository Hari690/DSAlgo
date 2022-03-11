package linkedlist;

import java.util.Random;
/**
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
 *
 * Implement the Solution class:
 * Solution(ListNode head) Initializes the object with the head of the singly-linked list head.
 * int getRandom() Chooses a node randomly from the list and returns its value.
 * All the nodes of the list should be equally likely to be chosen.
 * Input
 * ["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * Output
 * [null, 1, 3, 2, 2, 3]
 *
 * Explanation
 * Solution solution = new Solution([1, 2, 3]);
 * solution.getRandom(); // return 1
 * solution.getRandom(); // return 3
 * solution.getRandom(); // return 2
 * solution.getRandom(); // return 2
 * solution.getRandom(); // return 3
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 */
public class LinkedListReservoirSampling {
    private Random random = null;
    private ListNode h = null;
    public LinkedListReservoirSampling(ListNode head) {
        h = head;
        random = new Random();
    }

    /*
        Idea is to traverse list of unknown size once and find random in that traversal.
        So we start and as the size of list increases we can find random using current size
        until the end of list.
     */
    public int getRandom() {
        ListNode head = h;
        int val = head.val;
        head = head.next;
        int i=1;
        while(head!=null) {
            if(random.nextInt(i+1)==i)
                val = head.val;
            i++;
            head = head.next;
        }
        return val;
    }
}
