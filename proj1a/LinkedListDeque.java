/**
 * @author Hanshi Sun
 * @create 2021-2-28
 */
public class LinkedListDeque<T> {
    /* the private static class IntNode */
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        /* constructor */
        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    /* add two sentinel */
    private Node sentF;
    private Node sentB;
    private int size;
    private Node setfront = sentF;

    /**
     * Creates an empty LinkedListDequeList.
     */
    public LinkedListDeque() {
        sentF = new Node(null, null, null);
        sentB = new Node(null, null, null);
        sentF.next = sentB;
        sentB.prev = sentF;
        size = 0;
    }

    /* Creates an LinkedListDequeList by item */
/*	public LinkedListDeque(T item) {
        sentF = new Node(null, null, null);
        sentB = new Node(null, null, null);
        sentF.next = new Node(item, sentF, sentB);
        sentB.prev = sentF.next;
        size = 1;
    }*/
    //Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        Node temp = new Node(item, sentF, sentF.next);
        sentF.next = temp;
        sentF.next.prev = temp;
        size += 1;
    }

    //Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        Node temp = new Node(item, sentB.prev, sentB);
        sentB.prev = temp;
        sentB.prev.next = temp;
        size += 1;
    }


    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        if (sentF.next == sentB) {
            return true;
        }
        return false;
    }

    //Returns the number of items in the deque.
    public int size() {
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        Node p = sentF;
        while (p.next != sentB) {
            System.out.print(p.next.item);
            System.out.print(" ");
            p.next = p.next.next;
        }
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node temp = sentF.next;
        sentF.next = temp.next;
        temp.next.prev = temp.prev;
        return temp.item;
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node temp = sentB.prev;
        sentB.prev = temp.prev;
        temp.prev.next = temp.next;
        return temp.item;
    }

    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
//If no such item exists, returns null. Must not alter the deque!
    public T get(int index) {
        if ((index + 1) > size) {
            return null;
        }
        int i = 0;
        Node p = sentF;
        while (i <= index) {
            p = p.next;
            i = i + 1;
        }
        return p.item;
    }

    private T getfirst() {
        if (isEmpty()) {
            return null;
        }
        return sentF.next.item;
    }

    // Use Recursion
    public T getRecursive(int index) {
        if ((index + 1) > size) {
            return null;
        }
        if (isEmpty()) {
            return null;
        }
        if (index == 0) {
            sentF = setfront;
            return getfirst();

        }
        sentF = sentF.next;
        return getRecursive(index - 1);
    }
}
