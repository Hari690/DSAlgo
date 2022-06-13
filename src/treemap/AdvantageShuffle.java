package treemap;

import java.util.TreeMap;

/**
 * You are given two integer arrays nums1 and nums2 both of the same length. The advantage of nums1 with respect to nums2 is the number of indices i for which nums1[i] > nums2[i].
 * Return any permutation of nums1 that maximizes its advantage with respect to nums2.
 * Example 1:
 *
 * Input: nums1 = [2,7,11,15], nums2 = [1,10,4,11]
 * Output: [2,11,7,15]
 * Example 2:
 *
 * Input: nums1 = [12,24,8,32], nums2 = [13,25,32,11]
 * Output: [24,32,8,12]
 */
public class AdvantageShuffle {
    public int[] advantageCount(int[] A, int[] B) {
        //Use TreeMap to sort
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int a : A) {
            tm.put(a, tm.getOrDefault(a, 0) + 1);
        }

        for(int i=0; i<B.length; i++) {
            int val = B[i];

            //find the higher value
            Integer tmp = tm.ceilingKey(val+1);
            //if no higher value, use the least key
            if(tmp == null) {
                tmp = tm.firstKey();
            }
            //store to results
            A[i] = tmp;

            //reduce key count or remove when 0
            int cnt = tm.get(tmp);
            if(cnt > 1) {
                tm.put(tmp, cnt-1);
            } else {
                tm.remove(tmp);
            }

        }
        return A;
    }
}
