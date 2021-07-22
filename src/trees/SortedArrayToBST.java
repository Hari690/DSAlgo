package trees;



public class SortedArrayToBST {

    public static void main(String[] args) {

        int[] nums = {-10,-3,0,5,9};

        TreeNode root = new SortedArrayToBST().sortedArrayToBST(nums);

        System.out.println(root);

    }

    public TreeNode sortedArrayToBST(int[] nums) {

        return sortedArrayToBST(nums, 0 , nums.length-1);

    }

    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {

        if(start>end) {
            return null;
        }

        int mid = (start+end)/2;

        TreeNode node = new TreeNode(nums[mid]);

        node.left = sortedArrayToBST(nums, start, mid-1);
        node.right = sortedArrayToBST(nums, mid+1, (end));

        return node;
    }
}
