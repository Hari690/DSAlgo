package linkedlist;

import java.util.PriorityQueue;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 */
public class MergeKLists {

    public static void main(String[] args) {

        ListNode[] lists = new ListNode[3];

        lists[0] = new ListNode(1);
        lists[0].next = new ListNode(4);
        lists[0].next.next = new ListNode(5);

        lists[1] = new ListNode(1);
        lists[1].next = new ListNode(3);
        lists[1].next.next = new ListNode(4);

        lists[2] = new ListNode(2);
        lists[2].next = new ListNode(6);

        mergeKLists1(lists);

    }


    public static ListNode mergeKLists1(ListNode[] lists) {
        ListNode output = new ListNode(0);
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a,b)->a.val-b.val);
        for ( ListNode list : lists) {
            if(list!=null)  minHeap.offer(list);
        }

        ListNode temp = output;
        while(minHeap.size()>0) {
            ListNode node = minHeap.poll();
            output.next = node;
            output = output.next;
            if(node.next!=null)
                minHeap.offer(node.next);
        }

        return temp.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
