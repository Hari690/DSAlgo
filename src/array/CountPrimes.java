package array;

/**
 * Count the number of prime numbers less than a non-negative number, n.
 */
public class CountPrimes {
    public int countPrimes(int n) {
        if(n<=1)
            return 0;
        boolean[] notPrime = new boolean[n];
        notPrime[0] = true;
        notPrime[1] = true;

        int count = 0;
        for(int i=2;i<n;i++) {
            // optimization we only check if false these would have already been checked
            // by the factors and marked. eg no need to check multiples of 6 if multiples of
            // 3 have been checked.
            if(notPrime[i]==false) {
                count++;
                int k=2;
                while(i*k<n) {
                    notPrime[i*k]=true;
                    k++;
                }
            }
        }

        return count;
    }
}
