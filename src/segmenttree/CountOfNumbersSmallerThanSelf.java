package segmenttree;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example 1:
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Example 2:
 * Input: nums = [-1]
 * Output: [0]
 * Example 3:
 *
 * Input: nums = [-1,-1]
 * Output: [0,0]
 */
public class CountOfNumbersSmallerThanSelf {
    /*
        Create a segment tree with min and max value from the array and start storing counts
        of values against the index of that element.
        No count of items smaller than any element will be count sum of values
        in segment tree where it lies between num-1 and 1;
     */
    class SegmentTreeNode {
        int start, end;
        SegmentTreeNode left, right;
        int sum;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
            this.sum = 0;
        }
    }

    SegmentTreeNode root = null;

    private SegmentTreeNode buildTree(int start, int end) {
        SegmentTreeNode node = new SegmentTreeNode(start, end);
        if (start == end) {
            return node;
        } else {
            int mid = start  + (end - start) / 2;
            node.left = buildTree(start, mid);
            node.right = buildTree(mid + 1, end);
        }
        return node;
    }

    void update(int i, int val) {
        update(root, i, val);
    }

    void update(SegmentTreeNode root, int pos, int val) {
        if (root.start == root.end) {
            root.sum+= val;
        } else {
            int mid = root.start + (root.end - root.start) / 2;
            if (pos <= mid) {
                update(root.left, pos, val);
            } else {
                update(root.right, pos, val);
            }
            root.sum = root.left.sum + root.right.sum;
        }
    }

    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }

    public int sumRange(SegmentTreeNode root, int start, int end) {
        if(start>end || root==null)
            return 0;
        if (root.end == end && root.start == start) {
            return root.sum;
        } else {
            int mid = root.start + (root.end - root.start) / 2;
            if (end <= mid) {
                return sumRange(root.left, start, end);
            } else if (start >= mid+1) {
                return sumRange(root.right, start, end);
            }  else {
                return sumRange(root.right, mid+1, end) + sumRange(root.left, start, mid);
            }
        }
    }

    private void createTree(int min, int max) {
        this.root = buildTree(min, max);
    }

    public List<Integer> countSmaller(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = 0;

        for(int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        createTree(min, max);
        int[] counts = new int[nums.length];
        for(int i=nums.length-1;i>=0;i--) {
            update(nums[i], 1);
            counts[i] = sumRange(min, nums[i]-1);
        }
        List<Integer> ans = new ArrayList<>();
        for(int i=0;i<counts.length;i++) {
            ans.add(counts[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        CountOfNumbersSmallerThanSelf countOfNumbersSmallerThanSelf = new CountOfNumbersSmallerThanSelf();
        int[] arr = {5,2,6,1};

        System.out.println(countOfNumbersSmallerThanSelf.countSmaller(arr));
    }
}
