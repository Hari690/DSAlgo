package intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 */
public class MergeIntervals {

    final static int MAX_CHAR = 256;

    public static void main(String[] args) {
        int[][] intervals= {{1,4},{0,2},{3,5},{6,9},{7,12},{15,20}};
        new MergeIntervals().merge(intervals);
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;

        // Sort by ascending starting point
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        // once we add this to result we keep modifying the boundaries.
        result.add(newInterval);
        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) // Overlapping intervals, move the end if needed
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            else {                             // Disjoint intervals, add the new interval to the list
                newInterval = interval;
                result.add(newInterval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    static void findFirstNonRepeating()
    {
        // inDLL[x] contains pointer to a DLL node if x is
        // present in DLL. If x is not present, then
        // inDLL[x] is NULL
        List<Character> inDLL = new ArrayList<>();

        // repeated[x] is true if x is repeated two or more
        // times. If x is not seen so far or x is seen only
        // once. then repeated[x] is false
        boolean[] repeated = new boolean[MAX_CHAR];

        // Let us consider following stream and see the
        // process
        String stream = "geeksforgeeksandgeeksquizfor";
        for (int i = 0; i < stream.length(); i++) {
            char x = stream.charAt(i);
            System.out.println("Reading " + x
                    + " from stream \n");

            // We process this character only if it has not
            // occurred or occurred only once. repeated[x]
            // is true if x is repeated twice or more.s
            if (!repeated[x]) {
                // If the character is not in DLL, then add
                // this at the end of DLL.
                if (!(inDLL.contains(x))) {
                    inDLL.add(x);
                }
                else // Otherwise remove this character from
                // DLL
                {
                    inDLL.remove((Character)x);
                    repeated[x]
                            = true; // Also mark it as repeated
                }
            }

            // Print the current first non-repeating
            // character from stream
            if (inDLL.size() != 0) {
                System.out.print(
                        "First non-repeating character so far is ");
                System.out.println(inDLL.get(0));
            }
        }
    }
}
