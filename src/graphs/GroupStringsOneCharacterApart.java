package graphs;

/**
 * Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.
 * For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".
 * Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.
 * We are given a list strs of strings where every string in strs is an anagram of every other string in strs. How many groups are there?
 *
 * Example 1:
 * Input: strs = ["tars","rats","arts","star"]
 * Output: 2
 *
 * Example 2:
 * Input: strs = ["omv","ovm"]
 * Output: 1
 */
public class GroupStringsOneCharacterApart {

    /*
        use input array itself as a visited array. Now start from one string and start grouping.
        If there's a match look for another string which matches by dfs.
        Keep doing for all strings.
     */
    public int numSimilarGroups(String[] strs) {
        int count = 0;
        for(int i=0;i<strs.length;i++) {
            if(strs[i]!=null) {
                count++;
                dfs(i, strs);
            }
        }
        return count;
    }

    private void dfs(int index, String[] strs) {
        String curr = strs[index];
        strs[index]=null;
        for(int i=0;i<strs.length;i++) {
            if(strs[i]!=null && same(curr, strs[i])) {
                dfs(i, strs);
            }
        }
    }

    private boolean same(String a, String b) {
        if(a.length()!=b.length())
            return false;

        int diff = 0;
        for(int k=0;k<a.length();k++) {
            if(a.charAt(k)!=b.charAt(k))
                diff++;
        }

        return (diff<=2);
    }

    public static void main(String[] args) {
        GroupStringsOneCharacterApart groupStringsOneCharacterApart = new GroupStringsOneCharacterApart();

        String[] strings = {"tars","rats","arts","star"};
        System.out.println(groupStringsOneCharacterApart.numSimilarGroups(strings));
    }
}
