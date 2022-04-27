package linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the
 * list, or null.
 *
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to
 * the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the
 * copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new
 * list should point to nodes in the original list.
 *
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y
 * in the copied list, x.random --> y.
 *
 * Return the head of the copied linked list.
 *
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 *
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 */
class NodeRandom {
    int val;
    NodeRandom next;
    NodeRandom random;

    public NodeRandom(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
public class CopyListRandomPointer {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public Node copyRandomList(Node head) {
        Node temp1 = head;
        Node newHead = null;
        Node prev = null;
        Map<Node,Node> map = new HashMap<>();
        while(temp1!=null) {
            Node node = new Node(temp1.val);
            if(newHead==null)
                newHead = node;
            if(prev!=null)
                prev.next = node;
            prev = node;
            map.put(temp1, node);
            temp1 = temp1.next;
        }

        temp1 = head;
        Node temp2 = newHead;
        while(temp1!=null) {
            if(temp1.random!=null)
                temp2.random = map.get(temp1.random);
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        return newHead;
    }
}
