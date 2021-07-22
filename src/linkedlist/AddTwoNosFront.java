package linkedlist;

public class AddTwoNosFront {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode tempHead = head;
        int carry = 0;
        while(l1!=null || l2!=null) {
            if(l1!=null){
                head.val+=l1.val;
                l1=l1.next;
            }
            if(l2!=null){
                head.val+=l2.val;
                l2=l2.next;
            }
            head.val = head.val + carry;
            carry = head.val / 10;
            head.val = head.val % 10;
            if( l1!=null || l2!=null || carry!=0) {
                ListNode temp = new ListNode(0);
                head.next = temp;
                head = head.next;
            }

        }
        if ( carry!=0 ) {
            head.val = carry;
        }
        return tempHead;
    }
}
