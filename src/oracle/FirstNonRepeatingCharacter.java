package oracle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FirstNonRepeatingCharacter {
    public static void main(String[] args) {

        char[] input = {'a','d','b','c','d','a'};

        String c = findFirstChar(input);
        System.out.println(c);

        char[] input2 = {'a','b','c'};
        c = findFirstChar(input2);
        System.out.println(c);

        char[] input3 = {'a','a','a'};
        c = findFirstChar(input3);
        System.out.println(c);
    }

    public static String findFirstChar(char[] chars) {

        Map<Character,ListNode> seen = new HashMap<>();
        Set<Character> seenAndRemoved = new HashSet<>();

        ListNode head = null;
        ListNode tail = null;

        for( char c : chars) {
            if(!seen.containsKey(c)) {
                ListNode node = new ListNode();
                node.val = c;

                if(head == null) {
                    head = node;
                    tail = node;
                } else {
                    tail.next = node;
                    node.prev = tail;
                    tail = tail.next;
                }
                seen.put(c, node);
            } else {
                ListNode node = seen.get(c);
                if(seenAndRemoved.contains(c)) {
                    continue;
                } else {
                    if(node.val==head.val)
                        head = head.next;
                    ListNode prev = node.prev;
                    if(prev!=null)
                        prev.next = node.next;
                    node = node.next;

                    if(node!=null)
                        node.prev = prev;

                    seenAndRemoved.add(c);
                }
            }
        }
        return (head==null)? " ":String.valueOf(head.val);
    }

    static class ListNode {
        Character val;
        ListNode next;
        ListNode prev;
    }
}
