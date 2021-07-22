package problems;

public class RemoveElement {

    public static void main(String[] args) {
        int nums[] = {0,1,2,2,3,0,4,2};
        int val = 2;
        System.out.println(new RemoveElement().removeElement(nums, 2));
    }

    public int removeElement(int[] nums, int val) {
        int index = 0;
        for( int no : nums) {
            if(no!=val){
                nums[index++]=no;
            }
        }
        return index;
    }
}
