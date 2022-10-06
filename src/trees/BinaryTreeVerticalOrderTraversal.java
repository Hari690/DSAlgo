package trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column.
 * There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
 * Return the vertical order traversal of the binary tree.
 */
class TreeNodeLevel {
    int row;
    int col;
    TreeNode node;
    TreeNodeLevel(TreeNode n, int row, int col) {
        this.node = n;
        this.row = row;
        this.col = col;
    }
}
public class BinaryTreeVerticalOrderTraversal {

    /*
        TreeMap with row as key would ensure ordering per row.
        In the end sort the buckets based on col number.
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer,List<TreeNodeLevel>> map = new TreeMap<>();
        Queue<TreeNodeLevel> queue = new LinkedList<>();
        queue.offer(new TreeNodeLevel(root, 0, 0));
        int col=0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                TreeNodeLevel nodeLevel = queue.poll();
                List<TreeNodeLevel> list = map.get(nodeLevel.row);
                if(list==null) {
                    list = new ArrayList<>();
                    map.put(nodeLevel.row, list);
                }
                list.add(nodeLevel);
                if(nodeLevel.node.left!=null)
                    queue.offer(new TreeNodeLevel(nodeLevel.node.left, nodeLevel.row-1, col));
                if(nodeLevel.node.right!=null)
                    queue.offer(new TreeNodeLevel(nodeLevel.node.right, nodeLevel.row+1, col));
            }
            col++;
        }

        List<List<Integer>> result = new ArrayList<>();
        for(Map.Entry<Integer,List<TreeNodeLevel>> entry : map.entrySet()) {
            Collections.sort(entry.getValue(),(a, b)->{
                if(a.col==b.col)
                    return a.node.val-b.node.val;
                else
                    return a.col-b.col;
            });
            result.add(entry.getValue().stream().map(s->s.node.val).collect(Collectors.toList()));
        }

        return result;

    }
}
