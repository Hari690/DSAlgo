package trees;

import java.util.Stack;

public class KthSmallestBST {

    int count = 0;
    int result = Integer.MIN_VALUE;

    public int kthSmallestRecursive(TreeNode root, int k) {
        traverse(root, k);
        return result;
    }

    public void traverse(TreeNode root, int k) {
        if(root == null) return;
        traverse(root.left, k);
        count ++;
        if(count == k) result = root.val;
        traverse(root.right, k);
    }

    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        int count = 0;

        while(!stack.isEmpty() || p != null) {
            if(p != null) {
                stack.push(p);  // Just like recursion
                p = p.left;

            } else {
                TreeNode node = stack.pop();
                if(++count == k) return node.val;
                p = node.right;
            }
        }
        return Integer.MIN_VALUE;
    }
}
