package bits;

/**
 * Given a positive integer num, output its complement number. The complement strategy is to flip the bits of its binary representation.
 */
public class ComplementNumber {

    // Since i + i` = 1111111 , then we need to find first no with all 1s greater than num and subtract num from it.
    public int findComplement(int num) {
        int i=0;
        int j=0;

        while(i<num) {
            i+= Math.pow(2,j);
            j++;
        }

        return i-num;
    }
}
