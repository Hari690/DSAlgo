package treemap;

import java.util.Map;
import java.util.TreeMap;

/**
 * Invariant: At any point all ranges in the collection will always be non-overlaping or we'd have merged them while adding.
 * This is very important and drives the algorithm.
 *
 * Add-
 * The solution involves searching for potential points of intersection followed by merges if the search is successful.
 * Binary search is the most efficient way of performing this search.
 *
 * Query-
 * This will return true if and only if the input range is contained within an existing range.
 * If the input range overlaps two or more ranges then this will be false because the individual ranges will have
 * gaps between them so the input range will have numbers not in any range. We can use binary search to identify the container range.
 *
 * Remove-
 * All ranges that lie completely inside the input range will have to be removed. Ranges that overlap will need to be split.
 * By adding the input range we'll merge all ranges that have an overlap/ or are completely contained. We then need to check the
 * left and right of the merged range and split if needed.
 */
public class RangeModule {
    // Java's red black tree implementation for fast search.
    TreeMap<Integer,Integer> tree;

    public RangeModule() {
        tree = new TreeMap<>(); // Key = left, val = right
    }
    /**
     IMPORTANT INVARIANT: All ranges in the tree will always be non-overlaping or we'd have merged them while adding.
     So we try to merge ranges from right to left when adding a new range
     We get the right most range that can be merged into input range
     We remove the above range from the tree before merging.
     Repeat.
     **/
    public void addRange(int left, int right) {
        while (true){
            // Left most range that can potentially merge with input range
            Map.Entry<Integer, Integer> lastPossibleOverlap = tree.floorEntry(right);
            // Check if the above search was successful
            // Also check that the range doesn't lie entirely to the left of input range
            if (lastPossibleOverlap == null || lastPossibleOverlap.getValue() < left){
                break;
            }
            left = Math.min(left, lastPossibleOverlap.getKey());
            right = Math.max(right, lastPossibleOverlap.getValue());
            tree.remove(lastPossibleOverlap.getKey());
        }
        tree.put(left, right);
    }
    /**
     In order to be able to successfully query a range it MUST lie completely within an existing range
     Search for the above range and if successfully found return true.
     NOTE: If the input range is spread across multiple ranges in the tree then we will NOT find parts of the
     range as the trees ranges will always be non-overlaping.
     **/
    public boolean queryRange(int left, int right) {
        // floor includes the value
        Map.Entry<Integer, Integer> possibleOverlap = tree.floorEntry(left);
        // Only check if potential overlap's value is greater than right
        // The check for overlap.key <= left will be redundant
        return possibleOverlap != null && possibleOverlap.getValue() >= right;
    }

    /**
     In order to remove a range we first add it.
     Doing so this will merge all ranges that lie fully or partially within the input range
     We can get the merged range by doing a binary search
     So we check if merged range's left is less than input left and right is greater than input right.
     If so then we split
     **/
    public void removeRange(int left, int right) {
        addRange(left, right);
        // Returns the range added above
        Map.Entry<Integer, Integer> firstOverlap = tree.floorEntry(left);
        // Remove it from the tree
        tree.remove(firstOverlap.getKey());
        //Split left overlap
        if (firstOverlap.getKey() < left) tree.put(firstOverlap.getKey(), left);
        //Split right overlap
        if (firstOverlap.getValue() > right) tree.put(right, firstOverlap.getValue());

    }

    public static void main(String[] args) {
        RangeModule rangeModule = new RangeModule();

        rangeModule.addRange(10, 20);
        rangeModule.removeRange(16, 24);
        System.out.println(rangeModule.queryRange(10, 14));
        System.out.println(rangeModule.queryRange(13, 15));
        System.out.println(rangeModule.queryRange(16, 17));
    }
}
