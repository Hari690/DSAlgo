package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.right = new TreeNode(5);
        node.right.right = new TreeNode(4);
        System.out.println(rightSideView(node));

    }

    public static List<Integer> rightSideView(TreeNode root) {

        List<Integer> values = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        int level = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            int value = 0;
            while(size!=0) {
                TreeNode node = queue.poll();
                if(node!=null) {
                    value = node.val;
                    queue.offer(node.left);
                    queue.offer(node.right);
                }

                size--;
            }
            if(value!=0) {
                values.add(value);
            }
            level++;
        }
        return values;
    }

    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
