package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given the root of a binary tree, return all duplicate subtrees.
 *
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with the same node values.
 *
 * Example 1:
 * Input: root = [1,2,3,4,null,2,4,null,null,4]
 * Output: [[2,4],[4]]
 *
 * Input: root = [2,1,1]
 * Output: [[1]]
 */
public class FindDuplicateSubtrees {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();

        check(root, map, list);
        return list;
    }

    private String check(TreeNode root, Map<String, Integer> map, List<TreeNode> list) {
        if(root==null)
            return "";
        String left = check(root.left, map, list);
        String right = check(root.right, map, list);

        // this is so we don't add the current node to the deup string before diving into the children.
        String key = root.val + "#" + left + "#" + right;
        map.put(key, map.getOrDefault(key, 0)+1);
        if(map.get(key)==2)
            list.add(root);
        return key;
    }
}
