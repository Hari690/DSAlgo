package greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of size n that has the following specifications:
 * Each element in the array contains either a policeman or a thief.
 * Each policeman can catch only one thief.
 * A policeman cannot catch a thief who is more than K units away from the policeman.
 * We need to find the maximum number of thieves that can be caught.
 */
public class PolicaCatchThieves {

    // idea is to store index of police and thieves into 2 arrays.
    // Now greedily use 2 indexes to check if police can catch thief at-least k distance nearby.
    // If not we need to increment on or the other index.
    int policeThief(char arr[], int n, int k)
    {
        int res = 0;
        List<Integer> thi = new ArrayList<>();
        List<Integer> pol = new ArrayList<>();

        // store indices in the ArrayList
        for (int i = 0; i < n; i++) {
            if (arr[i] == 'P')
                pol.add(i);
            else if (arr[i] == 'T')
                thi.add(i);
        }

        // track lowest current indices of
        // thief: thi[l], police: pol[r]
        int l = 0, r = 0;
        while (l < thi.size() && r < pol.size()) {

            // can be caught
            if (Math.abs(thi.get(l) - pol.get(r)) <= k) {
                res++;
                l++;
                r++;
            }

            // increment the minimum index
            else if (thi.get(l) < pol.get(r))
                l++;
            else
                r++;
        }
        return res;
    }
}
