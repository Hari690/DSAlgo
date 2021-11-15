package linkedlist;//Given the head of a linked list, return the list after sorting it in ascending order.

import linkedlist.ListNode;

//Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
// Input: head = [4,2,1,3]
// Output: [1,2,3,4]
class SortList {

    public ListNode sortList(ListNode head) {
        // terminating condition
        if (head == null || head.next == null)
            return head;
          
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev= null;
        
        while(fast!=null && fast.next!=null) {
            prev= slow;
            slow=slow.next;
            fast=fast.next.next;
        }
        
        prev.next = null;
        
        // splitting up the list into parts
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        
        // merging the pieces
        return merge(l1,l2);
    }
    
    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode handler = head;
        while(l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                handler.next = l1;
                l1 = l1.next;
            } else {
                handler.next = l2;
                l2 = l2.next;
            }
            handler = handler.next;
        }
        
        if (l1 != null) {
            handler.next = l1;
        } else if (l2 != null) {
            handler.next = l2;
        }
        
        return head.next;
    }
}