package trees;

import problems.TreeNode;

public class BinaryTreeFromInorderAndPreorderTraversal {

    public static void main(String[] args) {

        int[] inorder = {9,3,15,20,7};
        int[] preorder = {3,9,20,15,7};

        new BinaryTreeFromInorderAndPreorderTraversal().buildTree(preorder, inorder);

    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0 , 0, inorder.length-1);
    }


    public TreeNode buildTree(int[] preorder, int[] inorder, int preIndex, int inStartIndex, int inEndIndex) {

        if(inStartIndex>inEndIndex || (preIndex>preorder.length-1)) {
            return null;
        }

        TreeNode node = new TreeNode(preorder[preIndex]);

        int found = 0;
        // find the inorder position
        for(int i=inStartIndex;i<=inEndIndex;i++) {
            if(preorder[preIndex]==inorder[i]) {
                found = i;
                break;
            }
        }

        // for left we take the next node in preorder index based as next node and set left and right nodes based on inorder found index
        node.left = buildTree(preorder, inorder, preIndex+1, inStartIndex, found-1);
        //for right we skip left nodes in preorder array after checking in inorder array and scan the right nodes of inorder found index
        node.right = buildTree(preorder, inorder, preIndex+found-inStartIndex+1, found+1, inEndIndex);

        return node;

    }
}
