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

class FlattenMultilevelList {
    public Node flatten(Node head) {
        flat(head);
        return head;
    }

    private ListEnds flat(Node head) {
        Node node = head;
        Node prev = null;
        while(node!=null) {
            if(node.child!=null) {
                ListEnds item = flat(node.child);
                item.tail.next=node.next;
                // Make all 4 required connections because of next and prev;
                if(node.next!=null)
                    node.next.prev = item.tail;
                node.next = item.head;
                if(item.head!=null)
                    item.head.prev = node;
                node.child=null;
                node = item.tail;
            }
            prev = node;
            node = node.next;
        }
        return new ListEnds(head, prev);
    }

    class ListEnds {
        Node head;
        Node tail;
        ListEnds(Node head, Node tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node3.child = node7;
        node8.next = node9;
        node9.next = node10;
        node7.next = node8;
        node8.child = node11;
        node11.next = node12;
        node2.prev=node1;
        node3.prev=node2;
        node4.prev=node3;
        node5.prev=node4;
        node6.prev=node5;
        node8.prev=node7;
        node9.prev=node8;
        node10.prev=node9;
        node12.prev=node11;
        FlattenMultilevelList flattenList = new FlattenMultilevelList();
        flattenList.flatten(node1);
    }
}