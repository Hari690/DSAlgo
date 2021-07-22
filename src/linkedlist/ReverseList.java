package linkedlist;

public class ReverseList {
    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        while(head!=null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);

        ReverseList reverseList = new ReverseList();
        reverseList.reverseList(l1);
    }
}
