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
public class BinaryTreePopulateNextPointerIncompleteTree {
    public Node connect(Node root) {
        if(root==null)
            return null;

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
