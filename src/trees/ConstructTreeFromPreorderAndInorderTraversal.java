package trees;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder
 * is the inorder traversal of the same tree, construct and return the binary tree.
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 */
public class ConstructTreeFromPreorderAndInorderTraversal {
    /*
        Idea is to pick up first element in preorder traversal and set as root.
        Then find the index of same element in inorder traversal. Left side of that is left tree
        and right side of that is the right tree. Similarly adjust index in preorder array by checking the
        no of to take in the array based on preorder array.
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        Map<Integer,Integer> inMap = new HashMap<>();

        for(int i=0;i<inorder.length;i++)
            inMap.put(inorder[i], i);

        return buildTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, inMap);
    }

    private TreeNode buildTree(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart,
                               int iEnd,  Map<Integer,Integer> inMap) {

        if(pStart>pEnd || iStart>iEnd)
            return null;

        int iIndex = inMap.get(preorder[pStart]);
        int numsLeft = iIndex - iStart;

        TreeNode root = new TreeNode(preorder[pStart]);
        root.left = buildTree(preorder, pStart+1, pStart+numsLeft, inorder, iStart, iIndex-1, inMap);
        root.right = buildTree(preorder, pStart+numsLeft+1, pEnd, inorder, iIndex+1, iEnd, inMap);
        return root;

    }

    public static void main(String[] args) {

        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};

        ConstructTreeFromPreorderAndInorderTraversal c = new ConstructTreeFromPreorderAndInorderTraversal();

        c.buildTree(preorder, inorder);

    }
}
