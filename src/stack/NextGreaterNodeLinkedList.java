package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import linkedlist.ListNode;

/**
 * You are given the head of a linked list with n nodes.
 *
 * For each node in the list, find the value of the next greater node. That is, for each node, find the value of the first node that is next to it and has a strictly larger value than it.
 *
 * Return an integer array answer where answer[i] is the value of the next greater node of the ith node (1-indexed). If the ith node does not have a next greater node, set answer[i] = 0.
 *
 * Input: head = [2,1,5]
 * Output: [5,5,0]
 *
 * Input: head = [2,7,4,3,5]
 * Output: [7,0,5,5,0]
 */
public class NextGreaterNodeLinkedList {
    class Solution {
        class Pair {
            int key;
            int value;
            Pair(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
        public int[] nextLargerNodes(ListNode head) {
            Stack<Pair> stack = new Stack<>();

            ListNode temp = head;
            List<Pair> output = new ArrayList<>();

            int length = 0;
            while (temp!=null) {
                while(!stack.isEmpty() && stack.peek().value<temp.val) {
                    output.add(new Pair(stack.peek().key,temp.val));
                    stack.pop();
                }
                stack.push(new Pair(length,temp.val));
                temp = temp.next;
                length++;
            }

            int[] result = new int[length];
            int i=0;
            for(;i<output.size();i++)
                result[output.get(i).key] = output.get(i).value;
            return result;
        }
    }
}
