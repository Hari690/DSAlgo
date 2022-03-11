package trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the sum of values of its deepest leaves.
 * Example 1:
 * Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * Output: 15
 *
 * Example 2:
 * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * Output: 19
 */
public class DeepestLeavesSum {
    public int deepestLeavesSum(TreeNode root) {
        if(root.left==null && root.right==null)
            return root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int sum = 0;
        while(!queue.isEmpty()) {
            sum = 0;
            int size = queue.size();
            for(int i=0;i<size;i++) {
                TreeNode node = queue.poll();
                sum+=node.val;
                System.out.println(node.val+" "+sum);
                if(node.left!=null)
                    queue.add(node.left);
                if(node.right!=null)
                    queue.add(node.right);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);
        root.right.right.right = new TreeNode(8);
        DeepestLeavesSum deepestLeavesSum = new DeepestLeavesSum();
        deepestLeavesSum.deepestLeavesSum(root);
    }
}
