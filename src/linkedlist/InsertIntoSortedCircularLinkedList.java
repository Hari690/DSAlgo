package linkedlist;

/**
 * Description
 * Given a Circular Linked List node, which is sorted in non-descending order, write a function to insert a value insertVal into the list such that it remains a sorted circular list. The given node can be a reference to any single node in the list and may not necessarily be the smallest value in the circular list.
 * If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the circular list should remain sorted.
 * If the list is empty (i.e., the given node is null), you should create a new single circular list and return the reference to that single node. Otherwise, you should return the originally given node.
 *
 * Input: head = [3,4,1], insertVal = 2
 * Output: [3,4,1,2]
 * Explanation: In the figure above, there is a sorted circular list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list. The new node should be inserted between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.
 *
 *
 */
public class InsertIntoSortedCircularLinkedList {
    public Node insert(Node head, int insertVal) {
        Node insertNode = new Node(insertVal);

        //Base case
        if(head == null){
            insertNode.next = insertNode;
            return insertNode;
        };

        //Pointer
        Node cur = head;

        //Find the right position to insert
        while(cur.next != head){
            int curVal = cur.val, nexVal = cur.next.val;

            // the point of change in sorted list
            if(curVal > nexVal){
                //Case 1: insertVal bigger than all neighbor nodes
                if(curVal < insertVal && nexVal < insertVal) break;
                //Case 2: insertVal smaller than all neighbor nodes
                if(curVal > insertVal && nexVal > insertVal) break;
            }
            //Case 3 insertVal is in between neighbor nodes
            if(curVal <= insertVal && insertVal <= nexVal) break;
            cur = cur.next;
        }
        //Insert at the current position
        Node nex = cur.next;
        cur.next = insertNode;
        insertNode.next = nex;
        return head;
    }
}
