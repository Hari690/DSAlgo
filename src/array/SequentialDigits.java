package array;

import java.util.ArrayList;
import java.util.List;

/**
 * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
 * Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
 */
public class SequentialDigits {

    // Generate nos between range using 2 loops and keep adding to result.
    public List<Integer> sequentialDigits(int low, int high) {
        String digits = "123456789";
        List<Integer> result = new ArrayList();
        int nl = String.valueOf(low).length();
        int nh = String.valueOf(high).length();

        for(int i = nl; i <= nh; ++i){
            for(int j = 0; j < 10-i; ++j){
                int num = Integer.parseInt(digits.substring(j, j+i));
                if(num >= low && num <= high) result.add(num);
            }
        }
        return result;
    }
}
