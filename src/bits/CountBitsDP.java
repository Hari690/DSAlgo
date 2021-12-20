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
        int result[] = new int[num + 1];
        int offset = 1;
        for (int index = 1; index < num + 1; ++index){
            if (offset * 2 == index){
                offset *= 2;
            }
            result[index] = result[index - offset] + 1;
        }
        return result;
    }
}
