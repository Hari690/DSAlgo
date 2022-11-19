package problems;

public class RemoveDuplicatesSortedArray {

    public static void main(String[] args) {
        int[] nums = {-3,-1,-1,0,0,0,0,0,2};
        new RemoveDuplicatesSortedArray().removeDuplicates(nums);
    }

    public int removeDuplicates(int[] nums) {
        int j=1;
        for(int i=1;i<nums.length;i++) {
            if(nums[i]!=nums[i-1]){
                nums[j]=nums[i];
                j++;
            }
        }

        return j;
    }
}
