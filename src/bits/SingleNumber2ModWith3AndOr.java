package bits;

/**
 * Given an integer array nums where every element appears three times except for one,
 * which appears exactly once. Find the single element and return it.
 */
public class SingleNumber2ModWith3AndOr {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 1, 0, 1, 99};
        System.out.println(new SingleNumber2ModWith3AndOr().singleNumber2(nums));
    }

    public int singleNumber(int[] nums) {

        int maxNo = nums[0];
        for (int i = 0; i < nums.length; i++) {
            maxNo = Math.max(maxNo, nums[i]);
        }

        int sum = 0;
        int no = 0;
        int k = 0;
        while (maxNo != 0) {
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i] % 2;
                nums[i] >>= 1;
            }
            no += Math.pow(2, k) * (sum % 3);
            //System.out.println(no);
            maxNo >>= 1;
            k++;
            sum = 0;
        }
        return no;
    }

    /**
     * The usual bit manipulation code is bit hard to get and replicate. I like to think about the number in 32 bits and just count how
     * many 1s are there in each bit, and sum %= 3 will clear it once it reaches 3. After running for all the numbers for each bit, if we
     * have a 1, then that 1 belongs to the single number, we can simply move it back to its spot by doing ans |= sum << i;
     *
     * This has complexity of O(32n), which is essentially O(n) and very easy to think and implement. Plus, you get a general solution
     * for any times of occurrence. Say all the numbers have 5 times, just do sum %= 5.
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        int res = 0;
        for(int i = 0; i < 32; i++){
            int sum = 0;
            for(int n: nums)
                // check if i'th bit is set and add.
                if((n >> i & 1) == 1)
                    sum++;
            sum %= 3;
            // check if i'th bit is present and or
            if(sum==1)
                res |= 1<<i;
        }
        return res;
    }
}
