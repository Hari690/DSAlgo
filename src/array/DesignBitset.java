package array;

import java.util.HashSet;
import java.util.Set;
/**
 * A Bitset is a data structure that compactly stores bits.
 * Implement the Bitset class:
 *
 * Bitset(int size) Initializes the Bitset with size bits, all of which are 0.
 * void fix(int idx) Updates the value of the bit at the index idx to 1. If the value was already 1, no change occurs.
 * void unfix(int idx) Updates the value of the bit at the index idx to 0. If the value was already 0, no change occurs.
 * void flip() Flips the values of each bit in the Bitset. In other words, all bits with value 0 will now have value 1 and vice versa.
 * boolean all() Checks if the value of each bit in the Bitset is 1. Returns true if it satisfies the condition, false otherwise.
 * boolean one() Checks if there is at least one bit in the Bitset with value 1. Returns true if it satisfies the condition, false otherwise.
 * int count() Returns the total number of bits in the Bitset which have value 1.
 * String toString() Returns the current composition of the Bitset. Note that in the resultant string, the character at the ith index should coincide with the value at the ith bit of the Bitset.
 *
 */
public class DesignBitset {
    int size;
    Set<Integer> one = new HashSet<>();
    Set<Integer> zero = new HashSet<>();
    public DesignBitset(int size) {
        this.size = size;
        for(int i=0;i<size;i++) zero.add(i);
    }

    public void fix(int idx) {
        one.add(idx);
        zero.remove(idx);
    }

    public void unfix(int idx) {
        one.remove(idx);
        zero.add(idx);
    }

    //swapping object's referrence is O(1)
    public void flip() {
        Set<Integer> s = one;
        one = zero;
        zero = s;
    }

    public boolean all() {
        return one.size() == size;
    }

    public boolean one() {
        return one.size()>=1;
    }

    public int count() {
        return one.size();
    }

    public String toString() {
        StringBuilder sb=  new StringBuilder();
        for(int i=0;i<size;i++) {
            if(one.contains(i)) sb.append("1");
            else if(zero.contains(i)) sb.append("0");
        }
        return sb.toString();
    }
}
