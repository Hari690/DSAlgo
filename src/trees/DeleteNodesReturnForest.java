package trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given the root of a binary tree, each node in the tree has a distinct value.
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 * Return the roots of the trees in the remaining forest. You may return the result in any order.
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 *
 *
 */
public class DeleteNodesReturnForest {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> delete = Arrays.stream(to_delete).boxed().collect(Collectors.toSet());
        List<TreeNode> result = new ArrayList<>();
        if(!delete.contains(root.val))
            result.add(root);
        delete(root, delete, result);
        return result;
    }

    private TreeNode delete(TreeNode root, Set<Integer> delete,  List<TreeNode> result) {
        if(root==null)
            return null;

        TreeNode left = delete(root.left, delete, result);
        TreeNode right = delete(root.right, delete, result);
        if(delete.contains(root.val)) {
            if(left!=null)
                result.add(left);
            if(right!=null)
                result.add(right);
            return null;
        }
        root.left = left;
        root.right = right;
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);
        DeleteNodesReturnForest solution = new DeleteNodesReturnForest();
        int[] to_delete = {3,5};
        solution.delNodes(root, to_delete);
    }
}
