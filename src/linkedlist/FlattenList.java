package linkedlist;

/**
 * You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or
 * may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a
 * multilevel data structure, as shown in the example below.
 *
 * Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the
 * list.
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
    private LNode flatten_rec(LNode head){
        LNode curr = head, tail = head;
        while(curr != null){
            LNode child = curr.child;
            LNode next = curr.next;
            if(child != null){
                LNode _tail = flatten_rec(child);
                _tail.next = next;
                if(next != null) next.prev = _tail;
                curr.next = child;
                curr.child = null;
                child.prev = curr;
                curr = _tail;
            }
            else
                curr = next;
            if(curr != null) tail = curr;
        }
        return tail;
    }
    public LNode flatten(LNode head) {
        if(head != null) flatten_rec(head);
        return head;
    }
}