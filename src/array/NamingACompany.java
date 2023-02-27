package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * You are given an array of strings ideas that represents a list of names to be used in the process of naming a company. The process of naming a company is as follows:
 * Choose 2 distinct names from ideas, call them ideaA and ideaB.
 * Swap the first letters of ideaA and ideaB with each other.
 * If both of the new names are not found in the original ideas, then the name ideaA ideaB (the concatenation of ideaA and ideaB, separated by a space) is a valid company name.
 * Otherwise, it is not a valid name.
 * Return the number of distinct valid names for the company.

 * Example 1:
 *
 * Input: ideas = ["coffee","donuts","time","toffee"]
 * Output: 6
 * Explanation: The following selections are valid:
 * - ("coffee", "donuts"): The company name created is "doffee conuts".
 * - ("donuts", "coffee"): The company name created is "conuts doffee".
 * - ("donuts", "time"): The company name created is "tonuts dime".
 * - ("donuts", "toffee"): The company name created is "tonuts doffee".
 * - ("time", "donuts"): The company name created is "dime tonuts".
 * - ("toffee", "donuts"): The company name created is "doffee tonuts".
 * Therefore, there are a total of 6 distinct company names.
 *
 * The following are some examples of invalid selections:
 * - ("coffee", "time"): The name "toffee" formed after swapping already exists in the original array.
 * - ("time", "toffee"): Both names are still the same after swapping and exist in the original array.
 * - ("coffee", "toffee"): Both names formed after swapping already exist in the original array.
 */
public class NamingACompany {
    public long distinctNames(String[] ideas) {
        Map<Character, Set<String>> map = new HashMap<>();

        for (String idea : ideas) {
            map.computeIfAbsent(idea.charAt(0), k-> new HashSet<>()).add(idea.substring(1));
        }

        long answer = 0;
        for (Map.Entry<Character,Set<String>> c1 : map.entrySet()) {
            for (Map.Entry<Character,Set<String>> c2 : map.entrySet()) {
                if(c1==c2)
                    continue;
                int intersect = 0;
                for(String val : map.get(c1.getKey())) {
                    if(map.get(c2.getKey()).contains(val))
                        intersect++;
                }
                int distinct1 = c1.getValue().size() - intersect;
                int distinct2 = c2.getValue().size() - intersect;
                answer+=distinct1*distinct2;
            }
        }
        return answer;
    }
}
