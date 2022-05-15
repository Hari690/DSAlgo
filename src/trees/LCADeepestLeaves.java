package trees;

/**
 * Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.
 * Recall that:
 * The node of a binary tree is a leaf if and only if it has no children
 * The depth of the root of the tree is 0. if the depth of a node is d, the depth of each of its children is d + 1.
 * The lowest common ancestor of a set S of nodes, is the node A with the largest depth such that every node in S is in the subtree with root A.
 */
public class LCADeepestLeaves {
    class Pair {
        int level;
        TreeNode node;
        Pair(int level, TreeNode node) {
            this.level = level;
            this.node = node;
        }
    }

    /*
        Extension of finding level of a node.
        Idea is until we can find LCA from the root while going up from recursion the left and right level will be same.
        Now above that for any node if left greater than right, then pass left node else pass right node up.
     */
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return findLca(root).node;
    }

    public Pair findLca(TreeNode root) {
        if(root==null)
            return new Pair(0, root);

        Pair left = findLca(root.left);
        Pair right = findLca(root.right);
        // if left level equal right level
        if(left.level==right.level)
            return new Pair(left.level+1, root);
        // if left level greater
        if(left.level>right.level)
            return new Pair(left.level+1, left.node);
        return new Pair(right.level+1, right.node);
    }
}
