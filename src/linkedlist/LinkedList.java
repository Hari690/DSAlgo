package linkedlist;

/**
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.
 */
public class LinkedList {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;

        while(curr!=null) {
            ListNode temp = curr;
            while(temp!=null && temp.val==curr.val){
                temp = temp.next;
            }
            curr.next = temp;
            curr = curr.next;
        }
        return head;
    }
}
