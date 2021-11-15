package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 *
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2. Elements that do not appear in
 * arr2 should be placed at the end of arr1 in ascending order.
 */
public class RelativeSortArray {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer,Integer> map = new HashMap<>();

        int max = 0;
        for(int i=0;i<arr2.length;i++) {
            map.put(arr2[i], i);
            max = Math.max(max,i);
        }
        final int localMax = max;

        return Arrays.stream(arr1).boxed().sorted((a,b)-> {
            if (map.containsKey(a) && map.containsKey(b))
                return map.get(a) - map.get(b);
            else if(map.containsKey(a))
                return map.get(a)-(localMax+b);
            else if(map.containsKey(b))
                return (localMax+a)-map.get(b);
            else
                return (localMax+a) - (localMax+b);
        }).mapToInt(Integer::intValue).toArray();
    }
}
