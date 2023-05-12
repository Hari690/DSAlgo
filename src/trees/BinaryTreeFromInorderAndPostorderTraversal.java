package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree,
 * construct and return the binary tree.
 *
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 *
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 *
 */
public class BinaryTreeFromInorderAndPostorderTraversal {

    /*
        Last item in the postorder traversal will be the root of tree. Use that to find the left and right index from inorder to see what forms the left and right tree of root.
        Since it's a post order traversal the right will be the next element from end. `So we can keep popping from the end of inorder array.
        Optimisation using a map since the nodes contain unique values.
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer,Integer> map=new HashMap<>();
        List<Integer> postorderList = new ArrayList<>();

        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
            postorderList.add(postorder[i]);
        }

        TreeNode root =  helper(0,inorder.length-1,postorderList,map);
        return root;
    }
    public TreeNode helper(int inStart,int inEnd,List<Integer> postorder, HashMap<Integer,Integer> map){
        if(inStart>inEnd || postorder.isEmpty()){
            return null;
        }
        int val = postorder.remove(postorder.size()-1);
        TreeNode root=new TreeNode(val);
        int posOfRoot=map.get(root.val);
        root.right=helper(posOfRoot+1,inEnd,postorder,map);
        root.left=helper(inStart,posOfRoot-1,postorder,map);
        return root;
    }

    public static void main(String[] args) {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};

        BinaryTreeFromInorderAndPostorderTraversal binaryTreeFromInorderAndPostorderTraversal = new BinaryTreeFromInorderAndPostorderTraversal();

        binaryTreeFromInorderAndPostorderTraversal.buildTree(inorder, postorder);
    }
}
