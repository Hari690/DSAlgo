package trees;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A binary tree has the binary search tree property (BST property) if, for every node, the keys in its left subtree are smaller than its own key,
 * and the keys in its right subtree are larger than its own key. It has the heap property if, for every node, the keys of its children are all
 * smaller than its own key. You are given a set of binary tree nodes (i, j) where each node contains an integer i and an integer j.
 * No two i values are equal and no two j values are equal. We must assemble the nodes into a single binary tree where the i values obey
 * the BST property and the j values obey the heap property. If you pay attention only to the second key in each node, the tree looks like a heap,
 * and if you pay attention only to the first key in each node, it looks like a binary search tree.
 * Input: [(1, 6), (3, 7), (2, 4)]
 * Output:
 *
 * 		(3, 7)
 * 		/
 * 	 (1, 6)
 * 		\
 * 	  (2, 4)
 *
 * 	  Input: [(1, 4), (8, 5), (3, 6), (10, -1), (4, 7)]
 * Output:
 *
 * 		(4, 7)
 * 		/    \
 * 	(3, 6)   (8, 5)
 * 	 /          \
 *  (1, 4)       (10, -1)
 */
class   Treap {
    int bstVal;
    int heapVal;
    Treap left;
    Treap right;

    public Treap(int b, int h) {
        bstVal = b;
        heapVal = h;
    }

    @Override
    public String toString() {
        return "bstVal: "+bstVal+" heapVal:"+heapVal;
    }
}

class MyComparator implements Comparator<int[]> {
    @Override
    public int compare(int[] a, int[] b) {
        return b[1] - a[1];
    }
}

public class CreateTreapBSTWithHeap {

    /*
        head of the queue will be the current Treap's root.
        in the queue, all the elements whose i values are lesser than current root's i, they belong to left Treap queue
        all the elements whose i values are greater than current root's i, they belong to right Treap queue.
        recursively repeat the bulletted points.
     */
    public Treap buildTreap(int[][] vals) {
        Arrays.sort(vals, new MyComparator());

        // simulating a heap.
        Queue<int[]> queue = new LinkedList<>(Arrays.asList(vals));
        return build(queue);
    }

    private Treap build(Queue<int[]> queue) {
        if (queue.isEmpty()) return null;
        int[] curr = queue.poll();
        Treap treap = new Treap(curr[0], curr[1]);

        // taking care of the BST property
        Queue<int[]> leftQueue = new LinkedList();
        Queue<int[]> rightQueue = new LinkedList();

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();

            if (arr[0] < curr[0])
                leftQueue.offer(arr);
            else
                rightQueue.offer(arr);
        }

        treap.left = build(leftQueue);
        treap.right = build(rightQueue);

        return treap;
    }

//    public String serialize(Treap root) {
//        if (root == null) return "null";
//
//        StringBuilder sb = new StringBuilder();
//
//        serializeHelper(root, sb);
//
//        return sb.toString();
//    }
//
//    private void serializeHelper(Treap root, StringBuilder sb) {
//        if (root == null) {
//            sb.append("null");
//            return;
//        }
//
//        sb.append("[").append(root.bstVal).append(",").append(root.heapVal).append("]");
//        sb.append(",");
//        serializeHelper(root.left, sb);
//        sb.append(",");
//        serializeHelper(root.right, sb);
//    }
// Example: Treap root = s.buildTreap(new int[][]{{1, 4}, {8, 5}, {3, 6}, {10, -1}, {4, 7}});
// System.out.println(s.serialize(root));
// serialized output: [4,7],[3,6],[1,4],null,null,null,[8,5],null,[10,-1],null,null
}