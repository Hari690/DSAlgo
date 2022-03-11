package linkedlist;

/**
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".
 *
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.
 *
 * Implementation the MyCircularQueue class:
 *
 * MyCircularQueue(k) Initializes the object with the size of the queue to be k.
 * int Front() Gets the front item from the queue. If the queue is empty, return -1.
 * int Rear() Gets the last item from the queue. If the queue is empty, return -1.
 * boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
 * boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
 * boolean isEmpty() Checks whether the circular queue is empty or not.
 * boolean isFull() Checks whether the circular queue is full or not.
 * You must solve the problem without using the built-in queue data structure in your programming language.
 *
 * Example 1:
 * Input
 * ["MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * Output
 * [null, true, true, true, false, 3, true, true, true, 4]
 *
 * Explanation
 * MyCircularQueue myCircularQueue = new MyCircularQueue(3);
 * myCircularQueue.enQueue(1); // return True
 * myCircularQueue.enQueue(2); // return True
 * myCircularQueue.enQueue(3); // return True
 * myCircularQueue.enQueue(4); // return False
 * myCircularQueue.Rear();     // return 3
 * myCircularQueue.isFull();   // return True
 * myCircularQueue.deQueue();  // return True
 * myCircularQueue.enQueue(4); // return True
 * myCircularQueue.Rear();     // return 4
 */
public class CircularQueue {

    /*
        Intuition wise this can be done using linkedlist and array.
        Difference is linkedlist is more memory efficient as it will not need
        to preallocate an array of fixed size.
        In array implementation we can reset the right pointer to beginning
        when we reach the end of array while enqueuing.
        Similarly we can reset the front pointer to beginning while
        dequeuing.
     */
    class Node {
        int val;
        Node next;
        Node prev;
        Node(int val, Node prev, Node next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private int maxSize;
    private Node left;
    private Node right;

    public CircularQueue(int k) {
        this.left = new Node(-1,null,null);
        this.right = new Node(-1,left,null);
        this.left.next = this.right;
        this.maxSize = k;
        this.size=0;
    }

    public boolean enQueue(int value) {
        if(this.isFull())
            return false;
        // enque to right
        Node prev = right.prev;
        Node node = new Node(value, right.prev,right);
        prev.next = node;
        right.prev = node;
        size++;
        return true;
    }

    public boolean deQueue() {
        if(this.isEmpty())
            return false;
        // deque to rear
        left.next.next.prev = left;
        left.next = left.next.next;
        size--;
        return true;
    }

    public int Front() {
        if(this.isEmpty())
            return -1;
        return left.next.val;
    }

    public int Rear() {
        if(this.isEmpty())
            return -1;
        return right.prev.val;
    }

    public boolean isEmpty() {
        return this.size==0;
    }

    public boolean isFull() {
        return this.size==this.maxSize;
    }

    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(3);
        circularQueue.enQueue(1);
        circularQueue.enQueue(2);
        circularQueue.enQueue(3);
        circularQueue.enQueue(4);
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.isFull());
    }
}
