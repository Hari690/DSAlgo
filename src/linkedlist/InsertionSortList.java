package linkedlist;

/**
 * Given the head of a singly linked list, sort the list using insertion sort, and return the sorted list's head.
 *
 * The steps of the insertion sort algorithm:
 * Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data,
 * finds the location it belongs within the sorted list and inserts it there.
 * It repeats until no input elements remain.
 * The following is a graphical example of the insertion sort algorithm.
 * The partially sorted list (black) initially contains only the first element in the list.
 * One element (red) is removed from the input data and inserted in-place into the sorted list with each iteration.
 */
public class InsertionSortList {

    /*
        In insertion sort we need to check if current value is at correct place, if not iterate from beginning
        and find the right place to put it.
        Use dummy value to head to avoid edge cases.
        When forwarding pointer use temp.next.val to set pointer as we need to insert before temp.
     */
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next=head;

        ListNode current = head.next;
        ListNode prev = head;
        while(current!=null) {
            if(current.val>=prev.val) {
                prev = current;
                current = current.next;
            } else {
                ListNode temp = dummy;
                while(temp.next.val<current.val)
                    temp=temp.next;

                // removing current from current place and move to after temp.
                prev.next=current.next;
                current.next = temp.next;
                temp.next = current;
                current = prev.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        InsertionSortList insertionSortList = new InsertionSortList();
        insertionSortList.insertionSortList(node1);
    }
}
