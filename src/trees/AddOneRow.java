package trees;

/**
 * Given the root of a binary tree and two integers val and depth, add a row of nodes with value val at the given depth depth.
 *
 * Note that the root node is at depth 1.
 *
 * The adding rule is:
 *
 * Given the integer depth, for each not null tree node cur at the depth depth - 1, create two tree nodes with value val as cur's left
 * subtree root and right subtree root.
 * cur's original left subtree should be the left subtree of the new left subtree root.
 * cur's original right subtree should be the right subtree of the new right subtree root.
 * If depth == 1 that means there is no depth depth - 1 at all, then create a tree node with value val as the new root of the whole
 * original tree, and the original tree is the new root's left subtree.
 * Input: root = [4,2,6,3,1,5], val = 1, depth = 2
 * Output: [4,1,1,2,null,null,6,3,1,5]
 */
public class AddOneRow {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if(depth==1) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        dfs(root,val,depth,1);
        return root;
    }

    private void dfs(TreeNode node, int val, int depth, int current) {
        if(node==null)
            return;

        if(current == depth-1) {
            TreeNode left = new TreeNode(val);
            left.left = node.left;
            TreeNode right = new TreeNode(val);
            right.right = node.right;
            node.left=left;
            node.right=right;
            return;
        }

        dfs(node.left, val, depth, current+1);
        dfs(node.right, val, depth, current+1);
    }

    public static void main(String[] args) {
        AddOneRow solution = new AddOneRow();

        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(3);
        node.left.right = new TreeNode( 1);

        solution.addOneRow(node, 1, 3);
    }
}
