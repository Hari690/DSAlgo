package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();

        for ( String str : strs) {
            char[] temp = str.toCharArray();
            Arrays.sort(temp);
            List<String> list = map.get(new String(temp));
            if ( list==null) {
                list = new ArrayList<>();
            }
            list.add(str);
            map.put(new String(temp),list);
        }

        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagramsWithoutSorting(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] ca = new char[26];
            for (char c : s.toCharArray()) ca[c - 'a']++;
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
