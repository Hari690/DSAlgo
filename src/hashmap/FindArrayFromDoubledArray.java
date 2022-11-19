package hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * An integer array original is transformed into a doubled array changed by appending twice
 * the value of every element in original, and then randomly shuffling the resulting array.
 *
 * Given an array changed, return original if changed is a doubled array.
 * If changed is not a doubled array, return an empty array.
 * The elements in original may be returned in any order.
 *
 * Input: changed = [1,3,4,2,6,8]
 * Output: [1,3,4]
 * Explanation: One possible original array could be [1,3,4]:
 * - Twice the value of 1 is 1 * 2 = 2.
 * - Twice the value of 3 is 3 * 2 = 6.
 * - Twice the value of 4 is 4 * 2 = 8.
 * Other original arrays could be [4,3,1] or [3,1,4].
 */
public class FindArrayFromDoubledArray {

    /*
        When we sort the array they appear in the order of picking elements.
        So afterwards by maintaining count in HashMap we can check if criteria has been met.
     */
    public int[] findOriginalArray(int[] changed) {
        if(changed.length%2!=0)
            return new int[0];

        Map<Integer,Integer> map = new HashMap<>();

        for(int no : changed)
            map.put(no, map.getOrDefault(no,0)+1);

        int i=0;
        int[] result = new int[changed.length/2];
        // check from beginning if no and double exists. If so mark it.
        Arrays.sort(changed);
        for (int no : changed){
            if(map.containsKey(no) && map.get(no)>0 && map.containsKey(no*2) && map.get(no*2)>0) {
                map.put(no, map.getOrDefault(no,0)-1);
                map.put(no*2, map.getOrDefault(no*2,0)-1);
                result[i++] = no;
            }
        }

        for(Map.Entry<Integer,Integer> e : map.entrySet()) {
            if(e.getValue()>0)
                return new int[0];
        }

        return result;
    }

    public static void main(String[] args) {
        FindArrayFromDoubledArray solution = new FindArrayFromDoubledArray();
        int[] arr = {2,1,2,4,2,4};
        solution.findOriginalArray(arr);
    }
}
