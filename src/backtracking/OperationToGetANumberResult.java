package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string num that contains only digits and an integer target, return all possibilities to insert the binary operators
 * '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target value.
 * Note that operands in the returned expressions should not contain leading zeros.
 *
 * Example 1:
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
 *
 * Example 2:
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 * Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
 *
 * Example 3:
 * Input: num = "3456237490", target = 9191
 * Output: []
 * Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
 */
public class OperationToGetANumberResult {
    public List<String> addOperators(String num, int target) {
        List<String> list = new ArrayList<>();
        getResult(0, target, num, list,0l,0l,"");
        return list;
    }

    private void getResult(int index, int target, String num, List<String> list, Long curr,Long prev, String ex) {
        if (index>=num.length()) {
            // to avoid overflow we up-cast result into a Long and use that for comparison.
            if(curr.equals((long) target))
                list.add(ex);
            return;
        }

        for(int i=index;i<num.length();i++) {
            // since the string has nos only we can use substring in for loop for finding all internal nos.
            String val = num.substring(index, i+1);
            // cases like "05" need to be removed but not "0".
            if(val.startsWith("0") && val.length()>1)
                continue;
            Long no = Long.parseLong(val);
            // base case
            if(ex.length()==0) {
                getResult(i+1, target, num, list, curr+no, no, String.valueOf(no));
            } else {
                getResult(i+1, target, num, list, curr+no, no,ex+"+"+no);
                getResult(i+1, target, num, list, curr-no, -no,ex+"-"+no);
                // to handle consecutive multiplications like 4*5*6 we need to keep track of prev = 20 for current = 6
                // as per bodmas we need to apply multiplication before addition and subtraction so we rewind the
                // result removing previous no's addition or subtraction so we can apply multiplication.
                getResult(i+1, target, num, list, curr-prev+prev*no, no*prev, ex+"*"+no);
            }
        }
    }

    public static void main(String[] args) {
        OperationToGetANumberResult operationToGetANumberResult = new OperationToGetANumberResult();

        System.out.println(operationToGetANumberResult.addOperators("2147483648", -2147483648));
    }
}
