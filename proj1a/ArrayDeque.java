public class ArrayDeque<T> {
	private T[] items;
	private int size;
	/* Creates a empty ArrayDeque */
	public ArrayDeque() {
		items = (T[]) new Object[8];
		size = 0;
	}
	/* Resize the ArrayDeque */
	private void resize(int capacity) {
		T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
	}
    /* -------- Function: Add Last ------------*/
	public void addLast(T x) {
		if (size == items.length) {
			resize(size*2);
		}
		items[size] = x;
		size += 1;
	}
	/* -------- Function: Get Last -------------*/
	public T getLast() {
        return items[size - 1];
    }
    /* -------- Function: Get ith element --------*/
    public T get(int i) {
    	return items[i];
    }
    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and returns deleted item. */
    public T removeLast() {
        T x = getLast();
        items[size - 1] = null;
        size = size - 1;
        return x;
    }
    /* ---- Fuction: Add first -------*/
    public void addFirst(T x) {
    	T[] a = (T[]) new Object[size + 1];
        System.arraycopy(items, 0, a, 1, size);
        a[0] = x;
        items = a;
    }
}