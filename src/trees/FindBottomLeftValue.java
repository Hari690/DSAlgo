package trees;

/**
 * Given the root of a binary tree, return the leftmost value in the last row of the tree.
 * Input: root = [2,1,3]
 * Output: 1
 */
public class FindBottomLeftValue {
    public int findBottomLeftValue(TreeNode root) {
        int[] arr = new int[2];
        findBottomLeftValue(root, 1, arr);
        return arr[1];
    }

    private void findBottomLeftValue(TreeNode root, int depth, int[] arr) {
        if(root==null) {
            return;
        }
        if(depth>arr[0]) {
            arr[0]=depth;
            arr[1]=root.val;
        }
        findBottomLeftValue(root.left, depth+1, arr);
        findBottomLeftValue(root.right, depth+1, arr);
    }
}
