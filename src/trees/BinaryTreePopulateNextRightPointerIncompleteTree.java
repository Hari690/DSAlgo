package trees;

/**
 * Given a binary tree
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 */
public class BinaryTreePopulateNextRightPointerIncompleteTree {

    /*
    Idea is to iteratively connect nodes by maintaining pointers at two levels.
    head level will go through current level of nodes.
    temp level will start from dummy go through child level of nodes connecting them if they are not null.
    End of inner loop set first level as next of dummy node from second level.
     */
    public Node connect(Node root) {
        if(root==null)
            return null;

        // head is at top level and temp is at below level.
        // connect temp to head left and right.
        // use dummy to keep track of first node in a level and se head.
        Node head = root;
        while(head!=null) {
            Node dummy = new Node(0);
            Node temp = dummy;

            while(head!=null) {
                if(head.left!=null) {
                    temp.next = head.left;
                    temp=temp.next;
                }
                if(head.right!=null) {
                    temp.next = head.right;
                    temp=temp.next;
                }
                head = head.next;
            }
            head = dummy.next;
        }
        return root;
    }
}
