package backtracking;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Design the CombinationIterator class:
 *
 * CombinationIterator(string characters, int combinationLength) Initializes the object with a string characters of sorted distinct
 * lowercase English letters and a number combinationLength as arguments.
 * next() Returns the next combination of length combinationLength in lexicographical order.
 * hasNext() Returns true if and only if there exists a next combination.
 */
class CombinationIterator {
    private String s;
    Queue<String> q;
    public CombinationIterator(String characters, int combinationLength) {
        s = characters;
        q = new LinkedList();

        //getCombination(0, combinationLength, new StringBuilder());
        helper(characters,combinationLength,"",0);

        System.out.println(q);
    }

    public String next() {
        return q.poll();
    }

    public boolean hasNext() {
        return !q.isEmpty();
    }

    private void helper(String characters, int combinationLength, String output, int k) {
        if(output.length()==combinationLength) {
            q.add(output);
        }

        for(int i=k;i<characters.length();i++) {
            String next = output+characters.charAt(i);
            if( next.length()<=combinationLength )
                helper(characters, combinationLength, next, i+1);
        }
    }

    private void getCombination(int start, int length, StringBuilder txt){
        if(length == 0){
            q.add(txt.toString());
            return;
        }

        for(int i = start; i <= s.length() - length; ++i){
            txt.append(s.charAt(i));
            getCombination(i+1, length-1, txt);
            txt.deleteCharAt(txt.length() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationIterator combinationIterator = new CombinationIterator("abc", 2);
    }
}
