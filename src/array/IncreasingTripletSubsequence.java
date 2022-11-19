package array;

public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        int small1 = Integer.MAX_VALUE; // smallest
        int small2 = Integer.MAX_VALUE; // second smallest
        for(int num : nums) {
            if(num<small1) {
                small1 = num;
            } else if (num<small2 && num>small1) {
                small2 = num;
            }

            if(num>small2 & small2>small1)
                return true;
        }
        return false;
    }
}
