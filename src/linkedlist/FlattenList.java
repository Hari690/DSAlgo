package linkedlist;

/**
 * You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or
 * may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a
 * multilevel data structure, as shown in the example below.
 *
 * Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the
 * list.
 * 1. Start form the head , move one step each time to the next node
 * 2. When meet with a node with child, say node p, follow its child chain to the end and connect the tail node with p.next, by doing this we merged the child chain back to the main thread
 * 3. Return to p and proceed until find next node with child.
 * 4. Repeat until reach null
 */
class LNode {
    int val;
    LNode next;
    LNode prev;
    LNode child;
    LNode() {}
    LNode(int val) { this.val = val; }
    LNode(int val, LNode next) { this.val = val; this.next = next; }
}

class FlattenList {
    public Node flatten(Node head) {
        if( head == null) return head;
        // Pointer
        Node p = head;
        while( p!= null) {
            /* CASE 1: if no child, proceed */
            if( p.child == null ) {
                p = p.next;
                continue;
            }
            /* CASE 2: got child, find the tail of the child and link it to p.next */
            Node temp = p.child;
            // Find the tail of the child
            while( temp.next != null )
                temp = temp.next;
            // Connect tail with p.next, if it is not null
            temp.next = p.next;
            if( p.next != null )  p.next.prev = temp;
            // Connect p with p.child, and remove p.child
            p.next = p.child;
            p.child.prev = p;
            p.child = null;
        }
        return head;
    }
}