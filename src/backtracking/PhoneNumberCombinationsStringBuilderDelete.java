package backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return
 * the answer in any order.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 */
public class PhoneNumberCombinationsStringBuilderDelete {

    public static void main(String[] args) {
        new PhoneNumberCombinationsStringBuilderDelete().letterCombinations("328");
    }

    public List<String> letterCombinations(String digits) {
        if(digits.equals(""))
            return new ArrayList<>();

        Map<String,String> noCombinations = new HashMap<>();
        noCombinations.put("2", "abc");
        noCombinations.put("3", "def");
        noCombinations.put("4", "ghi");
        noCombinations.put("5", "jkl");
        noCombinations.put("6", "mno");
        noCombinations.put("7", "pqrs");
        noCombinations.put("8", "tuv");
        noCombinations.put("9", "wxyz");

        List<String> results = new ArrayList<>();
        letterCombinations(digits, noCombinations,new StringBuilder(), results, 0);
        return results;
    }

    private void letterCombinations(String digits, Map<String, String> noCombinations, StringBuilder combo, List<String> result, int index) {
        if(index==digits.length()) {
            result.add(combo.toString());
            return;
        }

        String combination = noCombinations.get(String.valueOf(digits.charAt(index)));
        for(int i=0; i<combination.length();i++) {
            combo.append(combination.charAt(i));
            letterCombinations(digits, noCombinations, combo, result, index+1);
            combo.setLength(combo.length() - 1);
        }
    }
}
