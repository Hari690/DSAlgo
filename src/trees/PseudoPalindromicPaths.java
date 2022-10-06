package trees;

/**
 * Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
 * Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
 * Input: root = [2,3,1,3,1,null,1]
 * Output: 2
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the red path [2,3,3],
 * the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic paths since the red path [2,3,3]
 * can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * Input: root = [2,1,1,1,3,null,null,null,null,null,1]
 * Output: 1
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * Example 3:
 * Input: root = [9]
 * Output: 1
 */
public class PseudoPalindromicPaths {
    int count=0;
    public int pseudoPalindromicPaths(TreeNode root) {
        int[] map = new int[10];
        getPaths(root, map);
        return count;
    }

    private void getPaths(TreeNode node, int[] map) {
        if(node==null)
            return;

        map[node.val]++;
        getPaths(node.left, map);
        getPaths(node.right, map);
        if(node.left==null && node.right==null)
            if(checkPalindrome(map)) {
                count++;
            }
        map[node.val]--;
    }

    private boolean checkPalindrome(int[] map) {
        boolean seenOne = false;
        for(int val : map) {
            if(val%2==1) {
                if(seenOne)
                    return false;
                seenOne = true;
            }
        }
        return true;
    }
}
