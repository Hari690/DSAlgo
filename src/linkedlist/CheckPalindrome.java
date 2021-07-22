package linkedlist;

public class CheckPalindrome {
    public boolean isPalindrome(ListNode head) {

        //find mid
        ListNode fast = head, slow = head;

        int length=0;
        while(fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
            length++;
        }

        //reverse from mid
        ListNode end = reverse(slow);

        //keep comparing
        int i=0;
        while(i<length) {
            if(end.val!=head.val)
                return false;
            i++;
            end = end.next;
            head=head.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode newHead = null;
        while( head!=null) {
            ListNode temp = head.next;
            head.next=newHead;
            newHead=head;
            head=temp;
        }
        return newHead;
    }
}
