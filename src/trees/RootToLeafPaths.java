package trees;

import java.util.ArrayList;
import java.util.List;

public class RootToLeafPaths {

    public List<String> binaryTreePaths(BinaryTreeRightSideView.TreeNode root) {

        List<String> paths = new ArrayList<>();
        if ( root == null ) {
            return paths;
        }

        dfs(root, "", paths);

        return paths;
    }

    private void dfs(BinaryTreeRightSideView.TreeNode root, String path, List<String> paths) {
        // using string and not array
        path += root.val;
        if(root.left==null || root.right==null) {
            paths.add(path);
            return;
        }

        if(root.left!=null) {
            dfs(root.left, path+"->", paths);
        }

        if(root.right!=null) {
            dfs(root.left, path+"->", paths);
        }
    }
}
