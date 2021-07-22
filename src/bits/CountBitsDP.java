package bits;

/**
 * Given an integer num, return an array of the number of 1's in the binary representation of
 * every number in the range [0, num].
 */
/*
When it comes to even numbers, i.e, 2,4,6,8, their binary should be like '10', '100', '110', '1000' so one notable difference is their unit digit is always 0,
which means when you call >> 1- shift one bit rightwards and also means dividing by 2- would cause no change to the count of '1' in the binary string.
Vice versa, when you meet odd numbers, shifting one bit rightwards always eliminates one '1' digit from original binary string,
that is why we should "compensate" one '1' character to the count.
To sum up, when you meet even number the count of '1's is always the same as its half number, on the other hand, remember to plus one to the odds' half number.
 */
public class CountBitsDP {
    public int[] countBits(int num) {

        int[] res = new int[num + 1];
        res[0] = 0;

        for(int i = 1; i <= num; i++){
            if((i & 1) == 0){
                // 110 is same as 011 which we can get by right shifting and getting previously calculated.
                // i.e 6 is same as 3.
                res[i] = res[i >> 1];
            }else{
                // odd numbers just take previous and add one.
                res[i] = res[i - 1] + 1;
            }
        }

        return res;
    }
}
