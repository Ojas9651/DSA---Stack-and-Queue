import java.util.*;
    class Node {
        int key;
        int value;
        Node next;
        Node prev;

        Node(){
            key = -1;
            value = -1;
            next = null;
            prev = null;
        }

        Node(int key, int value){
            this.key = key;
            this.value = value;
            next = null;
            prev = null;
        }
    }

    class LRUCache {

        HashMap<Integer, Node> hm = new HashMap<>();
        int capacity;
        Node head = new Node();
        Node tail =  new Node();
        int count;

        public LRUCache(int capacity) {
            count = 0;
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
            head.key = -1;
            head.value = -1;
            tail.key = -1;
            tail.value = -1;
        }

        public int get(int key) {
            if(hm.containsKey(key)){
                Node n = hm.get(key);
                Node nNext = n.next;
                Node nPrev = n.prev;
                nPrev.next = nNext;
                nNext.prev = nPrev;
                Node temp = head.next;
                head.next = n;
                n.next = temp;
                temp.prev = n;
                n.prev = head;
                return n.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            if(hm.containsKey(key)){
                Node n = hm.get(key);
                n.value = value;
                Node nNext = n.next;
                Node nPrev = n.prev;
                nPrev.next = nNext;
                nNext.prev = nPrev;
                Node temp = head.next;
                head.next = n;
                n.next = temp;
                temp.prev = n;
                n.prev = head;
            }
            else{
                if(count < capacity){
                    Node n = new Node(key, value);
                    hm.put(key, n);
                    Node tempNext = head.next;
                    head.next = n;
                    n.next = tempNext;
                    tempNext.prev = n;
                    n.prev = head;
                    count++;
                }else{
                    Node del = tail.prev;
                    hm.remove(del.key);
                    Node temp = tail.prev.prev;
                    temp.next = tail;
                    tail.prev = temp;
                    temp = head.next;
                    head.next = del;
                    del.next = temp;
                    temp.prev = del;
                    del.prev = head;
                    del.key = key;
                    del.value = value;
                    hm.put(key, del);
                }
            }
        }

        public static void main(String[] args) {
            LRUCache lr = new LRUCache(2);
            lr.put(1,1);
            lr.put(2,2);
            System.out.println(lr.get(1));
            lr.put(3,3);
            System.out.println(lr.get(2));
            lr.put(4,4);
            System.out.println(lr.get(1));
            System.out.println(lr.get(3));
            System.out.println(lr.get(4));
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

