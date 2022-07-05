package linkedlist;

/**
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 */
public class ReverseNodesInGroupsK {
    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummy = new ListNode(-1);
        dummy.next=head;
        ListNode prevGroup = dummy;

        while(true) {
            // get kth node;
            ListNode end = getKthNode(prevGroup, k);
            if(end==null)
                break;
            // reverse between head and end.
            ListNode prev = end.next;
            ListNode groupNext = end.next;
            ListNode curr = prevGroup.next;
            // also setting next groups beginning as next of this group's beginning that's why setting prev to end.next.
            while(curr!=groupNext) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            ListNode temp = prevGroup.next;
            prevGroup.next = end;
            prevGroup = temp;
        }
        return dummy.next;
    }

    private ListNode getKthNode(ListNode listNode, int k) {
        while (listNode!=null && k>0) {
            listNode = listNode.next;
            k--;
        }
        return listNode;
    }

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        ReverseNodesInGroupsK reverseNodesInGroupsK = new ReverseNodesInGroupsK();
        ListNode newHead = reverseNodesInGroupsK.reverseKGroup(one, 2);
        System.out.println(newHead);
    }
}
