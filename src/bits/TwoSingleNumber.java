package bits;

class TwoSingleNumber {

    // 1. xor together
    // 2. find first bit which is different
    // 3. divide into two groups based on if that bit is set
    // 4. XOR independently.
    public int[] singleNumber(int[] nums) {
        int xy = 0;
        for(int n: nums) xy ^= n;
        xy &= -xy;
        int[] result = new int[2];
        for(int n: nums){
            if((xy & n) == 0) result[0] ^= n;
            else result[1] ^= n;
        }
        return result;
    }
}