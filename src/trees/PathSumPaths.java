package trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.
 * A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 */
public class PathSumPaths {

    public static void main(String[] args) {

        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(4);
        node.right = new TreeNode(8);
        node.left.left = new TreeNode(11);
        node.right.left = new TreeNode(13);
        node.right.right = new TreeNode(4);
        node.left.left.left = new TreeNode(7);
        node.left.left.right = new TreeNode(2);
        node.right.right.right = new TreeNode(1);

//        problems.TreeNode node = new problems.TreeNode(1);
//        node.left = new problems.TreeNode(2);

        List<Integer> output = new ArrayList<>();
        List<List<Integer>> allOutput = new ArrayList<>();

        new PathSumPaths().hasPathSum(node,22, output, allOutput);
        System.out.println(allOutput);

    }

    public void hasPathSum(TreeNode root, int sum, List<Integer> output, List<List<Integer>> allOutput) {
        if(root==null){
            return;
        }
        hasPathSum1(root, 0, sum, output, allOutput);
    }

    public void hasPathSum1(TreeNode root, int sum, int total, List<Integer> output, List<List<Integer>> allOutput) {
        if(root==null) {
            return;
        }
        // only for root.
        if(root.left==null && root.right==null) {
            if ((sum + root.val) == total) {
                output.add(root.val);
                allOutput.add(new ArrayList<>(output));
                output.remove(output.size()-1);
            }
        }
        output.add(root.val);
        hasPathSum1(root.left, sum + root.val, total, output, allOutput);
        output.remove(output.size()-1);

        output.add(root.val);
        hasPathSum1(root.right, sum + root.val, total, output, allOutput);
        output.remove(output.size()-1);

    }

}
