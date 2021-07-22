package linkedlist;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come
 * before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 */
public class PartitionList {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        ListNode node = new ListNode(4);
        head.next = node;
        node.next = new ListNode(3);
        node = node.next;
        node.next = new ListNode(2);
        node = node.next;
        node.next  = new ListNode(5);
        node = node.next;
        node.next = new ListNode(2);
        node.next.next = null;
        int val = 3;
        new PartitionList().partition(head, val);
    }

    public ListNode partition(ListNode head, int x) {
        ListNode node = head;
        ListNode left = new ListNode(-1);
        ListNode leftHead = left;
        ListNode right = new ListNode(-1);
        ListNode rightHead = right;
        while(node!=null) {
            if (node.val < x) {
                left.next = new ListNode(node.val);
                left = left.next;
            } else {
                right.next = new ListNode(node.val);
                right = right.next;
            }
            node = node.next;
        }
        left.next = rightHead.next;
        return leftHead.next;
    }
}
