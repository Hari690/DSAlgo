package trees;

/**
 * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.
 * Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
 * If no such second minimum value exists, output -1 instead.
 */
public class SecondMinimumBinaryTree {
    public int findSecondMinimumValue(TreeNode root) {
        return findSecondMinValue(root, root.val);
    }


    public int findSecondMinValue(TreeNode root, int min) {
        if(root==null)  return -1;
        if(root.val>min)   return root.val;
        int leftMin = findSecondMinValue(root.left,min);
        int rightMin = findSecondMinValue(root.right,min);
        return (leftMin==-1 || rightMin==-1) ? Math.max(leftMin,rightMin) : Math.min(leftMin,rightMin);
    }
}
