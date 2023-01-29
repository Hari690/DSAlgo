package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given two strings of the same length s1 and s2 and a string baseStr.
 * We say s1[i] and s2[i] are equivalent characters.
 *
 * For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'.
 * Equivalent characters follow the usual rules of any equivalence relation:
 *
 * Reflexivity: 'a' == 'a'.
 * Symmetry: 'a' == 'b' implies 'b' == 'a'.
 * Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.
 * For example, given the equivalency information from s1 = "abc" and s2 = "cde", "acd" and "aab" are equivalent strings of baseStr = "eed", and "aab" is the lexicographically smallest equivalent string of baseStr.
 *
 * Return the lexicographically smallest equivalent string of baseStr by using the equivalency information from s1 and s2.
 */
public class LexicographicallySmallestEquivalentStringDFSUsingCharacterSpace {
    int min;
    void dfs(int src, Integer[][] adjMatrix, boolean visited[], List<Integer> component) {
        // Mark the character as visited.
        visited[src] = true;
        // Add it to the list.
        component.add(src);
        // Update the minimum character in the component.
        min = Math.min(min, src);

        for(int i=0;i<adjMatrix[src].length;i++) {
            // Perform DFS if the edge exists and the node isn't visited yet.
            if (adjMatrix[src][i] != null && visited[i]==false) {
                dfs(i, adjMatrix, visited, component);
            }
        }
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        // Adjacency matrix to store edges.
        Integer adjMatrix[][] = new Integer[26][26];
        for (int i = 0; i < s1.length(); i++) {
            adjMatrix[s1.charAt(i) - 'a'][s2.charAt(i) - 'a'] = 1;
            adjMatrix[s2.charAt(i) - 'a'][s1.charAt(i) - 'a'] = 1;
        }

        // Array to store the final character mappings.
        int mappingChar[] = new int[26];
        for (int i = 0; i < 26; i++) {
            mappingChar[i] = i;
        }

        // Array to keep visited nodes during DFS.
        boolean visited[] = new boolean[26];
        //for (int c = 0; c < 26; c++) {
        for(char ch : baseStr.toCharArray()) {
            int c = ch - 'a';
            if (visited[c] == false) {
                // Store the characters in the current component.
                List<Integer> component = new ArrayList<>();
                // Variable to store the minimum character in the component.
                min = 27;
                dfs(c, adjMatrix, visited, component);
                // Map the characters in the component to the minimum character.
                for (int vertex : component) {
                    mappingChar[vertex] = min;
                }
            }
        }

        String ans = "";
        // Create the answer string.
        for (char c : baseStr.toCharArray()) {
            ans += (char)(mappingChar[c - 'a'] + 'a');
        }

        return ans;
    }

    public static void main(String[] args) {
        LexicographicallySmallestEquivalentStringDFSUsingCharacterSpace lexicographicallySmallestEquivalentString = new LexicographicallySmallestEquivalentStringDFSUsingCharacterSpace();

        System.out.println(lexicographicallySmallestEquivalentString.smallestEquivalentString("parker", "morris", "parser"));
    }
}
