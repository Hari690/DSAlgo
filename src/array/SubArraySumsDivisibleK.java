package array;

public class SubArraySumsDivisibleK {

    // Running sum and check if there's constant difference in remainders. Get that count where remainders are same
    // and they do combination with those.
    public static void main(String[] args) {
        int[] A = {4,5,0,-2,-3,1};
         System.out.println(new SubArraySumsDivisibleK().subarraysDivByK(A, 5));
    }

    public int subarraysDivByK(int[] A, int K) {
        int[] counts = new int[K];
        int sum = 0;
        for (int x : A) {
            sum += (x%K + K)%K;
            counts[sum % K]++;
        }

        int result = counts[0];
        for(int c: counts){
            result += (c*(c-1))/2;
        }
        return result;
    }
}
