package graphs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
 * Suppose we need to investigate a mutation from a gene string start to a gene string end where one mutation is defined as one single character changed in the gene string.
 * For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
 * There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
 *
 * Given the two gene strings start and end and the gene bank bank, return the minimum number of mutations needed to mutate from start to end. If there is no such a mutation, return -1.
 * Note that the starting point is assumed to be valid, so it might not be included in the bank.
 *
 * Example 1:
 * Input: start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
 * Output: 1
 * Example 2:
 * Input: start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
 * Output: 2
 * Example 3:
 * Input: start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
 * Output: 3
 */
public class MinMutationDNABFS {
    public int minMutation(String start, String end, String[] bank) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        char[] chars = {'A','C','G','T'};

        int steps = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size>0) {
                String check = queue.poll();
                if(check.equals(end))
                    return steps;

                char[] arr = check.toCharArray();
                for(int i=0;i<check.length();i++) {
                    for(int j=0;j<chars.length;j++) {
                        // we need to clone else it modifies the internal string
                        char[] newArr = arr.clone();
                        newArr[i] = chars[j];
                        String next = new String(newArr);
                        if(!visited.contains(next) && bankSet.contains(next)) {
                            queue.add(next);
                            visited.add(next);
                        }
                    }
                }
                size--;
            }
            steps++;
        }

        return -1;
    }

    public static void main(String[] args) {
        MinMutationDNABFS minMutationDNABFS = new MinMutationDNABFS();
        minMutationDNABFS.minMutation("AAAAACCC", "AACCCCCC", new String[]{"AAAACCCC","AAACCCCC","AACCCCCC"});
    }
}
