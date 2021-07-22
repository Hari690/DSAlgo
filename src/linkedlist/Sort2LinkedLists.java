package linkedlist;

public class Sort2LinkedLists {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode output = null;
        ListNode head = null;
        if(l1!=null && l2!=null && l1.val<=l2.val) {
            output = head = new ListNode(l1.val);
            l1 = l1.next;
        } else if(l1!=null && l2!=null && l1.val>l2.val) {
            output = head = new ListNode(l2.val);
            l2 = l2.next;
        }

        while(l1!=null && l2!=null) {
            if(l1.val<=l2.val) {
                ListNode newNode = new ListNode(l1.val);
                ListNode current = output;
                if(current!=null) {
                    current.next = newNode;
                }
                output = newNode;
                l1 = l1.next;
            } else if (l2.val<l1.val) {
                ListNode newNode = new ListNode(l2.val);
                ListNode current = output;
                if(current!=null) {
                    current.next = newNode;
                }
                output = newNode;
                l2 = l2.next;
            }
        }
        while(l1!=null) {
            ListNode newNode = new ListNode(l1.val);
            ListNode current = output;
            if(current!=null) {
                current.next = newNode;
            }
            if ( head == null) {
                head = newNode;
            }
            l1 = l1.next;
        }
        while(l2!=null) {
            ListNode newNode = new ListNode(l2.val);
            ListNode current = output;
            if(current!=null) {
                current.next = newNode;
            }
            if ( head == null) {
                head = newNode;
            }
            l2 = l2.next;
        }
        if(output!=null) {
            output.next = null;
        }
        return head;
    }

    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;
        ListNode head1 = l1;

        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(2);
        ListNode p3 = new ListNode(3);
        p1.next = p2;
        p2.next = p3;
        ListNode head2 = p1;

        mergeTwoLists(head1, head2);

    }
}
