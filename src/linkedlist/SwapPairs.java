package linkedlist;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You may not modify the values in the list's nodes. Only nodes itself may be changed.
 */
public class SwapPairs {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        //node.next.next.next = new problems.ListNode(4);

        ListNode listNode = new SwapPairs().swapPairs(node);

        ListNode temp = listNode;
        while(temp!=null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

    public ListNode swapPairs(ListNode head) {
        ListNode temp = head;

        while(temp!=null && temp.next!=null) {
            ListNode next = temp.next.next;
            int val = temp.val;
            temp.val = temp.next.val;
            temp.next.val=val;
            temp = next;
        }

        return head;
    }
}
