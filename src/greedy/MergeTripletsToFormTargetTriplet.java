package greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * A triplet is an array of three integers. You are given a 2D integer array triplets, where triplets[i] = [ai, bi, ci] describes the ith
 * triplet. You are also given an integer array target = [x, y, z] that describes the triplet you want to obtain.
 *
 * To obtain target, you may apply the following operation on triplets any number of times (possibly zero):
 *
 * Choose two indices (0-indexed) i and j (i != j) and update triplets[j] to become [max(ai, aj), max(bi, bj), max(ci, cj)].
 * For example, if triplets[i] = [2, 5, 3] and triplets[j] = [1, 7, 5], triplets[j] will be updated to [max(2, 1), max(5, 7), max(3, 5)]
 * = [2, 7, 5].
 * Return true if it is possible to obtain the target triplet [x, y, z] as an element of triplets, or false otherwise.
 */
public class MergeTripletsToFormTargetTriplet {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean first = false,second = false,third = false;
        for(int[] triplet : triplets) {
            if (!(triplet[0] > target[0] || triplet[1] > target[1] || triplet[2] > target[2])) {
                if (triplet[0] == target[0])
                    first = true;
                if (triplet[1] == target[1])
                    second = true;
                if (triplet[2] == target[2])
                    third = true;
            }
        }
        return first && second && third;
    }
}
