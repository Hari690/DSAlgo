package array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an integer n, return a list of all simplified fractions between 0 and 1 (exclusive) such that the denominator is
 * less-than-or-equal-to n. The fractions can be in any order.
 */
public class SimplifyFractions {
    public List<String> simplifiedFractions(int n) {
        if(n==1)
            return new ArrayList<>();

        Set<String> set = new HashSet<>();
        for(int j=2;j<=n;j++) {
            for(int i=1;i<j;i++) {
                simplify(i,j,set);
            }
        }

        return new ArrayList<>(set);
    }

    private void simplify(int num, int den, Set<String> set) {
            int div = num;
            while(div>=1) {
                if(num%div==0 && den%div==0) {
                    String e = num / div + "/" + den / div;
                    if(!set.contains(e))
                        set.add(e);
                    // this was already added before so break
                    break;
                }
                div--;
            }
    }

    public List<String> simplifiedFractionsUsingSet(int n) {
        if(n==1)
            return new ArrayList<>();

        Set<Float> set = new HashSet<>();
        List<String> result = new ArrayList<>();
        for(int j=2;j<=n;j++) {
            for(int i=1;i<j;i++) {
                if(!set.contains((float)i/j)) {
                    set.add((float)i/j);
                    result.add(i+"/"+j);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SimplifyFractions simplifyFractions = new SimplifyFractions();
        simplifyFractions.simplifiedFractions(4);
    }
}
