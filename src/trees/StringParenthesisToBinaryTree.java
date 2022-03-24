package trees;

/**
 * You need to construct a binary tree from a string consisting of parenthesis and integers.
 * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.
 * You always start to construct the left child node of the parent first if it exists.
 * Example:
 * Input: "4(2(3)(1))(6(5))"
 * Output: return the tree root node representing the following tree:
 *
 *        4
 *      /   \
 *     2     6
 *    / \   /
 *   3   1 5
 */
public class StringParenthesisToBinaryTree {
    class Solution {
        public TreeNode str2tree(String s) {
            if (s.isEmpty())
                return null;
            return helper(s);
        }
        private int i = 0;
        private TreeNode helper(final String s) {
            final int start = i; // start index of val
            if (s.charAt(i) == '-')
                ++i;
            while (i < s.length() && Character.isDigit(s.charAt(i)))
                ++i;

            final int val = Integer.parseInt(s.substring(start, i));
            TreeNode root = new TreeNode(val);

            // left child
            if (i < s.length() && s.charAt(i) == '(') {
                ++i; // '('
                root.left = helper(s);
                ++i; // ')'
            }
            // right child
            if (i < s.length() && s.charAt(i) == '(') {
                ++i; // '('
                root.right = helper(s);
                ++i; // ')'
            }
            return root;
        }
    }
}
