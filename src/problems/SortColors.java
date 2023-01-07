package problems;

/**
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * You must solve this problem without using the library's sort function.
 *
 * Example 1:
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Example 2:
 *
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 */
public class SortColors {

    public static void main(String[] args) {
        int[] nums = {2,0,2,1,1,0};
        new SortColors().sortColors(nums);
        System.out.println(nums);
    }

    public void sortColors(int[] nums) {

        // 2-pass
//        int count0 = 0, count1 = 0, count2 = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] == 0) {count0++;}
//            if (nums[i] == 1) {count1++;}
//            if (nums[i] == 2) {count2++;}
//        }
//        for(int i = 0; i < nums.length; i++) {
//            if (i < count0) {nums[i] = 0;}
//            else if (i < count0 + count1) {nums[i] = 1;}
//            else {nums[i] = 2;}
//        }

        // 1-pass
//        int n0 = -1, n1 = -1, n2 = -1;
//        for (int i = 0; i < n; ++i) {
//            if (A[i] == 0)
//            {
//                A[++n2] = 2; A[++n1] = 1; A[++n0] = 0;
//            }
//            else if (A[i] == 1)
//            {
//                A[++n2] = 2; A[++n1] = 1;
//            }
//            else if (A[i] == 2)
//            {
//                A[++n2] = 2;
//            }
//        }

        int p1 = 0, p2 = nums.length - 1, index = 0;
        while (index <= p2) {
            if (nums[index] == 0) {
                nums[index] = nums[p1];
                nums[p1] = 0;
                p1++;
            }
            if (nums[index] == 2) {
                nums[index] = nums[p2];
                nums[p2] = 2;
                p2--;
                index--;
            }
            index++;
        }
    }
}
