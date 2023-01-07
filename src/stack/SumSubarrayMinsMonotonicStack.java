package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.
 * Example 1:
 *
 * Input: arr = [3,1,2,4]
 * Output: 17
 * Explanation:
 * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
 * Sum is 17.
 *
 * Example 2:
 * Input: arr = [11,81,94,43,3]
 * Output: 444
 */
public class SumSubarrayMinsMonotonicStack {

    /*
    The intuition of the algorithm I have posted below is derived from the other dicussion threads that I have read and tried to comprehend. However, personally,
    I felt that those explanations in the threads were slightly confusing with the type of notations used in them. Hence I am writing this detailed
    explanation with easier notations so that it would be easier for those like me who are probably having a hard time understanding the intuition
    for this algorithm. So, here it is -

        The idea here is to use the intuition that we calculate the contribution of each element in the array towards the overall ans.
        So consider the array is- [3,4,1]. The subarrays are - [3], [3,4], [3,4,1], [4], [4,1], [1] (total - 13)
        We shall calculate the contribution of each element towards the answer i.e 3, 4, 1.
        The contribution by each of the element is -
        3 - [3], [3,4] = 3 + 3 = 6. i.e if you see the subarrays of [3,4,1],
        3 appears to have contributed in the sub-arrays [3] & [3,4] leading to its contribution to be '6' in the total sum.

        Similarly,
        4 - [4] = 4
        1 - [3,4,1], [4,1], [1] = 1 + 1 + 1 = 3.
        Now, from the above observation, for every element if we know of the left next smallest element and right next smallest index (name them as leftNextSmallestElem,
        rightNextSmallestElem), we can calculate its contribution using -
        currElement * (currElemIndex - leftNextSmallestElemIndex) * (rightNextSmallestElemIndex - currIndex);
        for every element, the sum of above expression gives the answer.
        Note that the above expression itself, it not that intuitive itself, you'll have to workout a few examples to figure this equation out.
        In order to put boundary for border elements, we shall assign a left boundary of -1 (consider it as index -1 which is just a dummy boundary)
        and right boundary of arr.length.
        We create a monotonic stack of increasing order for that purpose -
        Initialize the stack with left boundary. i.e (-1). Stack - [-1

        [3,4,1]

        We first see 3, we put index of 3 i.e 0 to the stack as the stack is currently empty. Stack - [-1,0
        Next element, 4, we put index of 4 i.e 1 to the stack as 3 < 4. Stack - [-1, 0, 1
        Now, we see 1, we see that the current top element is 4 viz. > 1 so pop it out and calculate its contribution -
        Now for stack top element 1 (i.e 4),
        The next smallest right element index is '2' (i.e index of 1) (this value is obtained by the current array pointer - i)
        The next smallest left element index is '0' (i.e index of 3) (this value is the current stack top, after popping out 4's index)
        Our logic ensures that for element under considertion, 'i' will be the right next smallest element index and stack.top after
        stack.pop of element under consideration would be left next smallest element index.
        So the contribution is -
        currElement (i.e 4) * (currElemIndex (i.e 1) - leftNextSmallestElemIndex (i.e 0)) * (rightNextSmallesElemIndex (i.e 2) - currElemIndex (i.e 1))
        = 4 * (1 - 0) * (2 - 1) = 4
        Now, the current top element is 0 (i.e 3), we calculate its contribution -
        next smallest right elem index - 2 (i.e 'i')
        next smallest left elem index - '-1' (stack.peek() value after stack.pop()). As you can see from the original array, 3 has no element to the left of it,
        so we consider the dummy left boundary of -1).
        currElemIndex = 0
        Therefore contribution -
        3 * (0 - (-1)) * (2 - 0) = 6
        Now, after popping and calculating contribution of above two elements, we add the incoming element i.e 1. Stack now becomes - [-1, 2
        We have reached at the end of the array so now we have to calculate the contribution of remaining elements in the array.
        Note that for all such elements, the right boundary is reached so the right boundary viz. arr.length would be 3 for this case.
        We go through such elements, in our case, the stack top is '2' (i.e element - 1). It contribution can be calculated as -
        left next smallest element index = -1 (left boundary in this case since there is no smaller element than 1 on the left).
        right next smallest element index = 3 (right boundary, there's no element to the right of 1 either).
        currElemIndex = 2
        Therefore contribution -
        1 * (2 - (-1)) * (3 - 2) = 3
        Hence from above our total sum becomes -

        Contribution of 4 (i.e 4) + Contribution of 3 (i.e 6) + Contribution of 1 (i. e 3) = 13.
     */
    public int sumSubarrayMins(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();

        // left bound we set of -1
        stack.push(-1);
        long res = 0;
        for(int i = 0; i < arr.length; i++) {
            while(stack.size() > 1 && arr[stack.peek()] > arr[i]) {
                int currElemIndex = stack.pop();
                /* Note that after the above line, stack.peek() will give us the index of left next smallest element.
                   We monotonically order the stack always in an increasing order at all the times, because of that this
                   argument holds true.
                   'i' will be the next right smallest element index. (The while loop condition is designed such way).
                */
                res += ((long) arr[currElemIndex] * (currElemIndex - stack.peek()) * ( i - currElemIndex)) % 1000000007;
                res = res % 1000000007;
            }
            stack.push(i);
        }

        // right bound we set to array length and do for remaining elements in stack;
        int rightBoundary = arr.length;

        while(stack.size() > 1) {
            int currElemIndex = stack.pop();
            res += ((long) arr[currElemIndex] * (currElemIndex - stack.peek()) * (rightBoundary - currElemIndex)) % 1000000007;
            res = res % 1000000007;
        }

        return (int) res;

    }
}
