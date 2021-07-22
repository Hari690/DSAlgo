package backtracking;

/**
 * Suppose you have n integers labeled 1 through n. A permutation of those n integers perm (1-indexed) is considered a beautiful
 * arrangement if for every i (1 <= i <= n), either of the following is true:
 *
 * perm[i] is divisible by i.
 * i is divisible by perm[i].
 * Given an integer n, return the number of the beautiful arrangements that you can construct.
 *
 * Input: n = 2
 * Output: 2
 * Explanation:
 * The first beautiful arrangement is [1,2]:
 *     - perm[1] = 1 is divisible by i = 1
 *     - perm[2] = 2 is divisible by i = 2
 * The second beautiful arrangement is [2,1]:
 *     - perm[1] = 2 is divisible by i = 1
 *     - i = 2 is divisible by perm[2] = 1
 *
 * Backtracking template
 * helper (parameters of given data and current recursive level) {
 *         // Handle base cases, i.e. the last level of recursive call
 *         if (level == lastLevel) {
 *             record result;
 *             return sth;
 *         }
 *         // Otherwise permute every possible value for this level.
 *         for (every possible value for this level) {
 *             helper(parameters of given data and next recursive level);
 *         }
 *         return sth;
 *     }
 */
class BeautifulArrangement {
    private int count = 0;
    private void calculate(int n, int pos, boolean[] visited){
        // number upto n has satisfied
        if(pos > n) count++;
        //get all permutations
        for(int i = 1; i <= n; ++i){
            // boolean array simulates placing a number at a position.
            // condition prunes the search space so we only recur when required.
            if(!visited[i] && (pos % i == 0 || i%pos == 0)){
                visited[i] = true;
                calculate(n, pos+1, visited);
                visited[i] = false;
            }
        }
    }

    public int countArrangement(int n) {
        boolean[] visited = new boolean[n+1];
        calculate(n, 1, visited);
        return count;
    }

    private void helper(int[] nums, int start) {
        if (start == 0) {
            count++;
            return;
        }
        for (int i = start; i > 0; i--) {
            swap(nums, start, i);
            if (nums[start] % start == 0 || start % nums[start] == 0) helper(nums, start-1);
            swap(nums,i, start);
        }
    }

    public int countArrangement2(int N) {
        if (N == 0) return 0;
        int[] nums = new int[N+1];
        for (int i = 0; i <= N; i++) nums[i] = i;
        helper(nums, N);
        return count;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
