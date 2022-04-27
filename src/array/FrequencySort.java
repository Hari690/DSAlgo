package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FrequencySort {
    public int[] frequencySort(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        Set<Map.Entry<Integer,Integer>> set = map.entrySet();
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(set);
        Collections.sort(list, (a, b)-> {
                    if(a.getValue()!=b.getValue())
                        return a.getValue()-b.getValue();
                    else
                        return b.getKey().compareTo(a.getKey());
                }
        );
        int[] result = new int[nums.length];
        int i=0;
        for(Map.Entry<Integer,Integer> entry : list) {
            for(int j=0;j<entry.getValue();j++)
                result[i++]=entry.getKey();
        }
        return result;
    }
}
