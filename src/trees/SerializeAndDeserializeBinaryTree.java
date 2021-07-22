package trees;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or
 * memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization
 * algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to
 * the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow
 * this format, so please be creative and come up with different approaches yourself.
 */
public class SerializeAndDeserializeBinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null) {
            return "X";
        }
        String left = serialize(root.left);
        String right = serialize(root.right);
        return root.val + ","+ left +","+ right;
    }

    // use a queue to setup sequence of nodes
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        System.out.println(data);
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(data.split(",")));
        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(Queue<String> queue) {
        String node = queue.poll();
        if(node.equals("X")) {
            return null;
        }
        TreeNode treeNode = new TreeNode(Integer.valueOf(node));
        treeNode.left = deserializeHelper(queue);
        treeNode.right = deserializeHelper(queue);
        return treeNode;
    }
}