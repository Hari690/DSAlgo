package trees;

import java.util.HashMap;
import java.util.Map;

/**
 * There is a binary tree rooted at 0 consisting of n nodes. The nodes are labeled from 0 to n - 1. You are given a 0-indexed integer array parents representing the tree, where parents[i] is the parent of node i. Since node 0 is the root, parents[0] == -1.
 * Each node has a score. To find the score of a node, consider if the node and the edges connected to it were removed. The tree would become one or more non-empty subtrees. The size of a subtree is the number of the nodes in it. The score of the node is the product of the sizes of all those subtrees.
 * Return the number of nodes that have the highest score.
 *
 * Input: parents = [-1,2,0,2,0]
 * Output: 3
 * Explanation:
 * - The score of node 0 is: 3 * 1 = 3
 * - The score of node 1 is: 4 = 4
 * - The score of node 2 is: 1 * 1 * 2 = 2
 * - The score of node 3 is: 4 = 4
 * - The score of node 4 is: 4 = 4
 * The highest score is 4, and three nodes (node 1, node 3, and node 4) have the highest score.
 */
public class CountNodesHighestScoreSubtree {

    int highestScore = 0;
    Map<Integer,Integer> countMap = new HashMap<>();

    public int countHighestScoreNodes(int[] parents) {

        // create a tree first.
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        TreeNode root = createTree(parents, nodeMap);

        calculateChildNodes(root);

        calculateMaxScore(root, parents, nodeMap, root.childNodes);

        return countMap.get(highestScore);
    }

    private void calculateMaxScore(TreeNode root, int[] parents,Map<Integer, TreeNode> nodeMap, int total) {
        if(root==null)
            return;
        int left = 0;
        int right = 0;
        int curr = 0;
        if(root.left!=null)
            left = root.left.childNodes;
        if(root.right!=null)
            right = root.right.childNodes;
        if(nodeMap.get(parents[root.val])!=null)
            curr = total-left-right-1;
        int score = ((left==0)?1:left)*((right==0)?1:right)*((curr==0)?1:curr);
        highestScore = Math.max(highestScore, score);
        countMap.put(score, countMap.getOrDefault(score, 0)+1);
        calculateMaxScore(root.left, parents, nodeMap, total);
        calculateMaxScore(root.right, parents, nodeMap, total);
    }

    private TreeNode createTree(int[] parents, Map<Integer, TreeNode> nodeMap) {
        TreeNode root = new TreeNode();
        nodeMap.put(0, root);
        for(int i = 1; i< parents.length; i++) {
            TreeNode parent = nodeMap.get(parents[i]);
            if(parent==null) {
                parent = new TreeNode(parents[i]);
                nodeMap.put(parents[i], parent);
            }
            TreeNode child = nodeMap.get(i);
            if(child==null) {
                child = new TreeNode(i);
                nodeMap.put(i, child);
            }
            if(parent.left==null)
                parent.left = child;
            else
                parent.right = child;
            nodeMap.put(i, child);
        }
        return root;
    }

    private int calculateChildNodes(TreeNode root) {
        if(root==null)
            return 0;
        root.childNodes = 1 + calculateChildNodes(root.left) + calculateChildNodes(root.right);
        return root.childNodes;
    }

    public static void main(String[] args) {
        CountNodesHighestScoreSubtree countNodesHighestScoreSubtree = new CountNodesHighestScoreSubtree();
        int[] parents = {-1,2,0,2,0};
        countNodesHighestScoreSubtree.countHighestScoreNodes(parents);
    }
}
