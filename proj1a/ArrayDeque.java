/**
 * Performs some basic linked list tests.
 */
public class ArrayDeque<T> {
    private T[] items;
    private int size = 0;

    public T removeFirst() {
        T x = items[0];
        size = size - 1;
        int len = items.length;
        if ((size <= (items.length * 0.25)) && (items.length >= 16)) {
            len = size * 2;
        }
        resize(len, 1, 0);
        return x;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /* Creates a empty ArrayDeque */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    /* Resize the ArrayDeque */
    private void resize(int capacity, int pos, int pos2) {
        if (capacity <= 16) {
            capacity = 16;
        }
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, pos, a, pos2, size);
        items = a;
    }

    /* -------- Function: Add Last ------------*/
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2, 0, 0);
        }
        items[size] = x;
        size += 1;
    }

    /* -------- Function: Get Last -------------*/
    private T getLast() {
        return items[size - 1];
    }

    /* -------- Function: Get ith element --------*/
    public T get(int i) {
        if ((i + 1) > size) {
            return null;
        }
        return items[i];
    }

    /**
     * Returns the number of items in the list.
     */
    public int size() {
        return size;
    }

    /**
     * Deletes item from back of the list and returns deleted item.
     */
    public T removeLast() {
        T x = getLast();
        items[size - 1] = null;
        size = size - 1;
        if ((size <= (items.length * 0.25)) && (items.length >= 16)) {
            resize(size * 2, 0, 0);
        }
        return x;
    }

    /* ---- Fuction: Add first -------*/
    public void addFirst(T x) {
        int len = items.length;
        if (size == items.length) {
            len = size * 2;
        }
        resize(len, 0, 1);
        items[0] = x;
        size += 1;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.println(items[i]);
        }
    }
}
