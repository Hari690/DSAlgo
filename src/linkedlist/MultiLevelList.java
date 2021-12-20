package linkedlist;


class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};

class MultiLevelList {
    Node newHead = null;
    Node newNode = null;
    Node newPrev = null;

    public Node flatten(Node head) {
        recurse(head);
        return newHead;
    }

    private Node recurse(Node head) {
        if(head==null) {
            return null;
        }

        newNode = new Node();
        newNode.val = head.val;
        newNode.prev = newPrev;
        if(newHead==null)
            newHead = newNode;
        if(newPrev!=null)
            newPrev.next = newNode;
        newPrev = newNode;

        Node next = head.next;
        if(head.child!=null) {
            recurse(head.child);
        }
        return recurse(next);
    }


    // flattentail: flatten the node "head" and return the tail in its child (if exists)
    // the tail means the last node after flattening "head"

    // Five situations:
    // 1. null - no need to flatten, just return it
    // 2. no child, no next - no need to flatten, it is the last element, just return it
    // 3. no child, next - no need to flatten, go next
    // 4. child, no next - flatten the child and done
    // 5. child, next - flatten the child, connect it with the next, go next

    private Node flattentail(Node head) {
        if (head == null) return head; // CASE 1
        if (head.child == null) {
            if (head.next == null) return head; // CASE 2
            return flattentail(head.next); // CASE 3
        } else {
            Node child = head.child;
            head.child = null;
            Node next = head.next;
            Node childtail = flattentail(child);
            head.next = child;
            child.prev = head;
            if (next != null) { // CASE 5
                childtail.next = next;
                next.prev = childtail;
                return flattentail(next);
            }
            return childtail; // CASE 4
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        Node node5 = new Node();
        Node node6 = new Node();
        node1.val=1;
        node2.val=2;
        node3.val=3;
        node1.child=node2;
        node2.child=node3;
        //        node4.val=4;
        //        node3.child=node4;
        //        node4.prev=null;
        //        node5.val=5;
        //        node4.next=node5;
        //        node5.prev=node4;
        //        node6.val=6;
        //        node3.next=node6;
        //        node6.prev=node3;
        Node head = new MultiLevelList().recurse(node1);
        System.out.println(head.val);
    }
}
