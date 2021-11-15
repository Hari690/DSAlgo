package trees;

/**
 * You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums using the following
 * algorithm:
 *
 * Create a root node whose value is the maximum value in nums.
 * Recursively build the left subtree on the subarray prefix to the left of the maximum value.
 * Recursively build the right subtree on the subarray suffix to the right of the maximum value.
 * Return the maximum binary tree built from nums.
 */
public class ConstructMaxBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return recurse(nums, 0, nums.length);
    }

    private TreeNode recurse(int[] nums, int p, int q) {

        if(p<0 || q<0 || p>nums.length || q>nums.length)
            return null;

        int index = p;
        for( int i=p;i<q;i++) {
            if(nums[i] > nums[index]) {
                index = i;
            }
        }
        System.out.println(nums[index]);

        TreeNode node = new TreeNode(nums[index]);
        node.left = recurse(nums, p, index-1);
        node.right = recurse(nums, index+1, q);
        return node;
    }

    public static void main(String[] args) {
        int[] arr = {3,2,1,6,0,5};
        ConstructMaxBinaryTree solution = new ConstructMaxBinaryTree();
        solution.constructMaximumBinaryTree(arr);
    }
}
