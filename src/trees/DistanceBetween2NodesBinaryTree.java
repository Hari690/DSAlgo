package trees;

/**
 * Write a function that given a BST, it will return the distance (number of edges) between 2 nodes.
 * For example, given this tree
 *          5
 *         / \
 *        3   6
 *       / \   \
 *      2   4   7
 *     /         \
 *    1           8
 * The distance between 1 and 4 is 3: [1 -> 2 -> 3 -> 4]
 * The distance between 1 and 8 is 6: [1 -> 2 -> 3 -> 5 -> 6 -> 7 -> 8]
 */
public class DistanceBetween2NodesBinaryTree {
    public int bstDistance(TreeNode root, int node1, int node2) {
        if (root == null) return -1;
        TreeNode lca = lca(root, node1, node2);
        return getDistance(lca, node1) + getDistance(lca, node2);
    }

    private int getDistance(TreeNode src, int dest) {
        if (src.val == dest) return 0;
        TreeNode node = src.left;
        if (src.val < dest) {
            node = src.right;
        }
        return 1 + getDistance(node, dest);
    }

    private TreeNode lca(TreeNode root, int p, int q) {
        if(root == null) {
            return null;
        }
        if(root.val == p) {
            return root;
        }
        if(root.val == q) {
            return root;
        }
        TreeNode result1 = lca(root.left, p, q);
        TreeNode result2 = lca(root.right, p, q);
        if(result1!=null && result2!=null) {
            return root;
        }
        if(result1!=null)
            return result1;
        if(result2!=null)
            return result2;
        return null;

    }
}
