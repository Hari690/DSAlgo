package general;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 **/
public class RandomizedSetGetRandom1 {
    private ArrayList<Integer> nums;
    // since nos can be unique we can hold a mapping to indexes.
    // By replacing with a HashMap<Integer, Set<Integer>> we can handle duplicate as well.
    private HashMap<Integer, Integer> locs;
    private java.util.Random rand = new java.util.Random();

    /** Initialize your data structure here. */
    public RandomizedSetGetRandom1() {
        nums = new ArrayList<>();
        locs = new HashMap<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contain = locs.containsKey(val);
        if (contain) {
            return false;
        }
        locs.put(val, nums.size());
        nums.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        boolean contain = locs.containsKey(val);
        if (!contain) {
            return false;
        }
        int loc = locs.get(val);
        if (loc < nums.size() - 1) { // not the last one than swap the last one with this val
            int lastone = nums.get(nums.size() - 1);
            nums.set(loc, lastone);
            locs.put(lastone, loc);
        }
        locs.remove(val);
        nums.remove(nums.size() - 1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}
