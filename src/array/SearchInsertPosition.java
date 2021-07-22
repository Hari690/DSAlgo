package array;

/**
 * Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where
 * it would be if it were inserted in order.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        if(target>nums[nums.length-1]){
            return nums.length;
        }

        int l=0;
        int r=nums.length-1;

        while(l<=r){
            int m = l+(r-l)/2;
            if(target>nums[m]){
                l=m+1;
            }else if(target<nums[m]){
                r=m-1;
            } else {
                return m;
            }
        }

        return l;
    }
}
