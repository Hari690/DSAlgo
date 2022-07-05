package trees;

/**
 * You are given the root of a binary tree. We install cameras on the tree nodes where each camera at a node can monitor its parent, itself, and its immediate children.
 *
 * Return the minimum number of cameras needed to monitor all nodes of the tree.
 *
 * Input: root = [0,0,null,0,0]
 * Output: 1
 * Explanation: One camera is enough to monitor all nodes if placed as shown.
 *
 * Input: root = [0,0,null,0,null,0,null,null,0]
 * Output: 2
 * Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.
 */
public class BinaryTreeCameras {
    int count = 0;
    // 0 - no camera
    // 1 - covered by camera
    // 2 - has camera
    public int minCameraCover(TreeNode root) {
        int result = dfs(root);
        return (result==0)?count+1:count;
    }

    private int dfs(TreeNode root) {
        if (root==null) {
            return 1;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        if(left==0 || right==0) {
            count++;
            return 2;
        }
        if(left==2 || right ==2) {
            return 1;
        }
        return 0;
    }
}
