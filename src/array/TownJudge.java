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
        int[] votes = new int[N+1];  // Total votes each person gets. The person is represented by the index. I used N+1 to make it easier deal with array manipulation.
        int[] voted = new int[N+1]; // Total votes each person gives to others.

        for(int i=0;i<trust.length;i++){
            votes[trust[i][1]]++; // Adding all votes a person gets from others.
            voted[trust[i][0]]++; // Adding all votes a person gives to others.
        }
        for(int i = 1;i<=N;i++){
            if(votes[i]==N-1 && voted[i]==0){  // A judge gets all votes except his own i.e N - 1. A judge gives 0 votes to others.
                return i;
            }
        }
        return -1;
    }
}
