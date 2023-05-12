package trees;

import java.util.LinkedList;
import java.util.Queue;

public class WidthOfBinaryTree {
    class Pair {
        TreeNode node;
        int pos;
        Pair(TreeNode node, int pos) {
            this.node = node;
            this.pos = pos;
        }
    }

    /*
        Calculate child positions similar to heap and take diff between last node in level and first one.
     */
    public int widthOfBinaryTree(TreeNode root) {
        //root.val = 0;
        var maxWidth = 1;

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));

        while (!q.isEmpty()) {
            var head = q.peek();
            var tail = head;

            for (var i = q.size(); i > 0; i--) {
                tail = q.poll();
                if (tail.node.left != null) {
                    Pair pair = new Pair(tail.node.left, 2*tail.pos);
                    q.add(pair);
                }
                if (tail.node.right != null) {
                    Pair pair = new Pair(tail.node.right, 2*tail.pos+1);
                    q.add(pair);
                }
            }
            maxWidth = Math.max(maxWidth, tail.pos - head.pos + 1);
        }
        return maxWidth;
    }
}
