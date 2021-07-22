package array;

/**
 * In a town, there are n people labelled from 1 to n.  There is a rumor that one of these people is secretly the town judge.
 * If the town judge exists, then:
 * The town judge trusts nobody.
 * Everybody (except for the town judge) trusts the town judge.
 * There is exactly one person that satisfies properties 1 and 2.
 * You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.
 *
 * If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.
 */
public class TownJudge {
    public int findJudge(int N, int[][] trust) {
        if(trust.length == 0) return N == 1 ? 1 : -1;
        int[] trustCount = new int[N+1];
        for(int[] t : trust){
            trustCount[t[1]]++;
            trustCount[t[0]]--;
        }
        for(int i = 1; i < trustCount.length;i++){
            if(trustCount[i] == N-1)
                return i;
        }
        return -1;
    }
}
