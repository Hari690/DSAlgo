package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NosWithSameConsecutiveDiff {
    private void dfs(int num, int N, int K, List<Integer> result){
        if(N == 0){
            result.add(num);
            return;
        }
        int last_digit = num%10;
        if(last_digit >= K) dfs(num*10 + last_digit - K, N-1, K, result);
        if(K > 0 && last_digit + K < 10) dfs(num*10 + last_digit + K, N-1, K, result);
    }

    public int[] numsSameConsecDiff(int N, int K) {
        List<Integer> result = new ArrayList();
        if(N == 1) result.add(0);
        for(int d = 1; d < 10; ++d)
            dfs(d, N-1, K, result);

        return result.stream().mapToInt(i->i).toArray();
    }

    public static void main(String[] args) {
        NosWithSameConsecutiveDiff obj = new NosWithSameConsecutiveDiff();
        int[] nos = obj.numsSameConsecDiff(3,6);
        Arrays.stream(nos).forEach(System.out::println);
    }
}
