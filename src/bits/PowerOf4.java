package bits;

public class PowerOf4 {

    /*
        Given an integer n, return true if it is a power of four. Otherwise, return false.
        An integer n is a power of four, if there exists an integer x such that n == 4^x.
     */
    boolean isPowerOfFour(int num) {
        //& the number with previous and also check if it gives reminder of one when divided by 3.
        return (num > 0) && ((num & (num-1)) == 0) && (num % 3 == 1);

        // Log4(n) shift right by 2 and shift left and see if both are same.
    /*if(num < 1) return false;
    int n = num, count = 0;
    while(n > 1){
        n >>= 2;
        count += 2;
    }
    return (n << count) == num;
    */
    }
}
