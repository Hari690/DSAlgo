package treemap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * A transaction is possibly invalid if:
 *
 * the amount exceeds $1000, or;
 * if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
 * You are given an array of strings transaction where transactions[i] consists of comma-separated values representing the name, time (in minutes), amount, and city of the transaction.
 * Return a list of transactions that are possibly invalid. You may return the answer in any order.
 *
 * Example 1:
 * Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
 * Output: ["alice,20,800,mtv","alice,50,100,beijing"]
 * Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.
 * Example 2:
 * Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
 * Output: ["alice,50,1200,mtv"]
 * Example 3:
 * Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
 * Output: ["bob,50,1200,mtv"]
 */
public class InvalidTransactions {
    public List<String> invalidTransactions(String[] transactions) {
        Map<String, TreeMap<Integer, String>> lastSeen = new HashMap<>();
        List<String> res = new LinkedList<>();
        for (String transaction : transactions) {
            String[] parts = transaction.split(",");
            String name = parts[0];
            Integer time = Integer.valueOf(parts[1]);
            String city = parts[3];
            if(lastSeen.containsKey(name) && lastSeen.get(name).containsKey(time))
                res.add(transaction);
            lastSeen.computeIfAbsent(name, (x) -> new TreeMap<>()).put(time, city);
        }

        for (String transaction : transactions) {
            String[] parts = transaction.split(",");
            Integer amount = Integer.valueOf(parts[2]);
            if (amount > 1000) {
                res.add(transaction);
                continue;
            }

            String name = parts[0];
            Integer time = Integer.valueOf(parts[1]);
            String city = parts[3];
            TreeMap<Integer, String> lastSeenForName = lastSeen.get(name);
            var suspicious = lastSeenForName.subMap(time - 60, true, time + 60, true);
            for (var entry : suspicious.entrySet()) {
                if (!entry.getValue().equals(city)) {
                    res.add(transaction);
                    // to not repeat adding the same transaction again.
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        InvalidTransactions invalidTransactions = new InvalidTransactions();
        String[] trans = {"alice,20,800,mtv","bob,50,1200,mtv","alice,20,800,mtv","alice,50,1200,mtv","alice,20,800,mtv","alice,50,100,beijing"};
        invalidTransactions.invalidTransactions(trans);
    }
}
