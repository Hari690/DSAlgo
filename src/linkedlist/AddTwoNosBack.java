package linkedlist;

import java.util.Stack;

public class AddTwoNosBack {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> s1 = new Stack();
        Stack<ListNode> s2 = new Stack();

        while ( l1!=null ) {
            s1.push(l1);
            l1 = l1.next;
        }

        while ( l2!=null ) {
            s2.push(l2);
            l2 = l2.next;
        }

        ListNode head = new ListNode(0);
        int carry = 0;
        while ( s1.size()>0 || s2.size()>0) {
            if ( s1.size()>0) {
                l1 = s1.pop();
            } else {
                l1 = null;
            }
            if ( s2.size()>0) {
                l2 = s2.pop();
            } else {
                l2 = null;
            }
            if(l1!=null){
                head.val+=l1.val;
            }
            if(l2!=null){
                head.val+=l2.val;
            }
            head.val = head.val + carry;
            carry = head.val / 10;
            head.val = head.val % 10;
            if( s1.size()>0 || s2.size()>0 || carry!=0) {
                ListNode prev = head;
                head = new ListNode(0);
                head.next = prev;
            }
        }
        if ( carry!=0 ) {
            head.val = carry;
        }

        return head;
    }

    public static void main(String[] args) {
        AddTwoNosBack addTwoNosBack = new AddTwoNosBack();

        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        addTwoNosBack.addTwoNumbers(l1,l2);
    }
}
