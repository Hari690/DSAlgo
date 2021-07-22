package trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.
 If the tree has more than one mode, return them in any order.
 **/
public class ModesBST {
    Integer prev = null;
    int count = 1;
    int max = 0;

    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];

        List<Integer> list = new ArrayList<>();
        traverse(root, list);

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) res[i] = list.get(i);
        return res;
    }

    private void traverse(TreeNode root, List<Integer> modes) {
        if (root == null) return;
        traverse(root.left, modes);
        if (prev != null) {
            if (root.val == prev)
                count++;
            else
                count = 1;
        }
        if (count > max) {
            max = count;
            modes.clear();
            modes.add(root.val);
        } else if (count == max) {
            modes.add(root.val);
        }
        prev = root.val;
        traverse(root.right, modes);
    }
}
