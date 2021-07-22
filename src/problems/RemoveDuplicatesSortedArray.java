package problems;

public class RemoveDuplicatesSortedArray {

    public static void main(String[] args) {
        int[] nums = {-3,-1,-1,0,0,0,0,0,2};
        new RemoveDuplicatesSortedArray().removeDuplicates(nums);
    }

    public int removeDuplicates(int[] nums) {

        if(nums.length==2) {
            return nums.length;
        }

        int repeat = 1;
        int total = 0;
        for(int i=1;i<nums.length-total;i++) {
            if(nums[i]!=nums[i-1]) {
                if(repeat>2) {
                    total+=repeat-2;
                    int j= i;
                    int k=i-repeat+2;
                    while(k<nums.length && j<nums.length) {
                        int temp = nums[j];
                        nums[j] = nums[k];
                        nums[k] = temp;
                        k++;
                        j++;
                    }
                    i=i-repeat+2;
                }
                repeat=1;
            } else {
                repeat++;
            }
        }
        if(repeat>2) {
            total+=repeat-2;
        }
        return nums.length-total;
    }
}
