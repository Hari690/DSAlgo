package array;

/**
 * You are given a list of songs where the ith song has a duration of time[i] seconds.
 *
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we want the number of
 * indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
 */
public class PairSongsDurationDivisible60 {
    public int numPairsDivisibleBy60(int[] times) {
        int [] modTimes = new int[60];
        for(int songTime : times)
            modTimes[songTime % 60]++;

        // will contain numbers from 0 to 30.
        int totalPairs = 0;
        totalPairs += findPairs(modTimes[0]);
        totalPairs += findPairs(modTimes[30]);

        for(int i = 1; i <= 29; i++)
            totalPairs += modTimes[i] * modTimes[60 - i];

        return totalPairs;
    }

    // special case no of unique pairs e.g 3 2 1 0 is n*(n-1)/2
    public int findPairs(int n){
        return n*(n-1)/2;
    }

    public static void main(String[] args) {
        int[] times = {30,20,150,100,40};
        PairSongsDurationDivisible60 pairSongsDurationDivisible60 = new PairSongsDurationDivisible60();
        pairSongsDurationDivisible60.numPairsDivisibleBy60(times);
    }
}
