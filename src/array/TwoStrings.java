package array;

// cast, cats

// similar: we can do at most swap to two characters in A (in different positions) to make it equal to B
// Constraint: A and B are anagram

// Question: Given two words, tell whether they are similar

// catsab
// castba
// they are not similar

public class TwoStrings {

    public static void main(String[] args) {
        TwoStrings solution = new TwoStrings();

        //System.out.println(solution.compareAnagrams("cast", "cats"));

        System.out.println(solution.compareAnagrams("catsab", "castba"));
    }

    public boolean compareAnagrams(String s1, String s2) {
        int sumDiff = 0;
        boolean first = true, done = false;
        for(int i=0; i<s1.toCharArray().length; i++) {
            if(s1.charAt(i)!=s2.charAt(i)) {
                if(done)
                    return false;
                if(first) {
                    sumDiff += s1.charAt(i);
                    sumDiff += s2.charAt(i);
                    first = false;
                }
                else {
                    sumDiff -= s1.charAt(i);
                    sumDiff -= s2.charAt(i);
                    if(sumDiff!=0)
                        return false;
                    done = true;
                }
            }
        }
        if(sumDiff!=0)
            return false;

        return true;
    }
}
