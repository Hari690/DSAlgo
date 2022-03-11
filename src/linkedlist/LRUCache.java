package linkedlist;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    private class Node{
        int key, value;
        Node prev, next;
        Node(int k, int v){
            this.key = k;
            this.value = v;
        }
        Node(){
            this(0, 0);
        }
    }

    private int capacity;
    private Map<Integer, Node> map;

    // remove from tail, insert at head.
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node n = map.get(key);
        if(null==n){
            return -1;
        }
        update(n);
        return n.value;
    }

    public void set(int key, int value) {
        Node n = map.get(key);
        if(null==n){
            n = new Node(key, value);
            map.put(key, n);
            add(n);
        }
        else{
            n.value = value;
            update(n);
        }
        if(map.size()>capacity){
            Node toDel = tail.prev;
            remove(toDel);
            map.remove(toDel.key);
        }
    }

    private void update(Node node){
        remove(node);
        add(node);
    }
    private void add(Node node){
        // head will always be at dummy node.
        Node after = head.next;
        head.next = node;
        node.prev = head;
        node.next = after;
        after.prev = node;
    }

    private void remove(Node node){
        Node before = node.prev, after = node.next;
        before.next = after;
        after.prev = before;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.set(1,2);
        cache.get(1);
    }
}
