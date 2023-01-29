package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 * Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.
 *
 * Example 1:
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 *
 * Example 2:
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 *
 * Example 3:
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 */
public class RestoreIpAddress {
    public List<String> restoreIpAddresses(String s) {
        if(s.length()>12)
            return new ArrayList<>();
        List<String> output = new ArrayList<>();
        dfs(s, output, 0, "", 0);
        return output;
    }

    private void dfs(String ip, List<String> output, int index, String current, int count) {
        if (count > 4) return;
        if (count == 4 && index == ip.length()) output.add(current);

        for (int i=1; i<4; i++) {
            if (index+i <= ip.length()) {
                String s = ip.substring(index,index+i);
                if (!((s.charAt(0)=='0' && s.length()>1) || (i==3 && Integer.parseInt(s) >= 256))) {
                    String temp = current;
                    current = current+s+(count==3?"" : ".");
                    dfs(ip, output, index+i, current, count+1);
                    current = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        RestoreIpAddress restoreIpAddress = new RestoreIpAddress();
        restoreIpAddress.restoreIpAddresses("25525511135");
    }
}
