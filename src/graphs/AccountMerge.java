package graphs;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and
 * the rest of the elements are emails representing emails of the account.
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both
 * accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A
 * person can have any number of accounts initially, but all of their accounts definitely have the same name.
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest
 * of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 * Example 1:
 * Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Explanation:
 * The first and second John's are the same person as they have the common email "johnsmith@mail.com".
 * The third John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 *
 * Example 2:
 * Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
 * Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 */
public class AccountMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Set<String>> graph = new HashMap<>();  //<email, neighbor nodes>
        Map<String, String> name = new HashMap<>();        //<email, username>
        // Build the graph; map of email - name, map of email to neighbor
        // create the adjacency list such that each email node points to next node in the list.
        // during dfs we can chain together the nodes which have links.
        // eg - 2 - {3,1} , 1 - {2}, 3 - {2}
        for (List<String> account : accounts) {
            String userName = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                name.put(account.get(i), userName);
                if (i == 1)
                    continue;
                graph.computeIfAbsent(account.get(i), k -> new HashSet<>()).add(account.get(i - 1));
                graph.computeIfAbsent(account.get(i-1), k -> new HashSet<>()).add(account.get(i));
            }
        }

        Set<String> visited = new HashSet<>();
        List<List<String>> res = new LinkedList<>();
        // DFS search the graph;
        for (String email : name.keySet()) {
            // to add name to front
            List<String> list = new LinkedList<>();
            if (!visited.contains(email)) {
                visited.add(email);
                dfs(graph, email, visited, list);
                Collections.sort(list);
                list.add(0, name.get(email));
                res.add(list);
            }
        }
        return res;
    }

    public void dfs(Map<String, Set<String>> graph, String email, Set<String> visited, List<String> list) {
        list.add(email);
        for ( String mail : graph.getOrDefault(email, new HashSet<>())) {
            if (!visited.contains(mail)) {
                visited.add(mail);
                dfs(graph, mail, visited, list);
            }
        }
    }

    public static void main(String[] args) {
        AccountMerge accountMerge = new AccountMerge();

        List<List<String>> accounts = List.of(List.of("John","johnsmith@mail.com","john_newyork@mail.com"),List.of("John","johnsmith@mail.com","john00@mail.com"),
                List.of("Mary","mary@mail.com"),List.of("John","johnnybravo@mail.com"));

        accountMerge.accountsMerge(accounts);
    }
}
