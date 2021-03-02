import java.util.Formatter;

/**
 * A naked recursive list of integers, similar to what we saw in lecture 3, but
 * with a large number of additional methods.
 *
 * @author P. N. Hilfinger, with some modifications by Josh Hug and melaniecebula
 * [Do not modify this file.]
 */
public class IntList {
    /**
     * First element of list.
     */
    public int first;
    /**
     * Remaining elements of list.
     */
    public IntList rest;

    /**
     * A List with first FIRST0 and rest REST0.
     */
    public IntList(int first0, IntList rest0) {
        first = first0;
        rest = rest0;
    }

    /**
     * A List with null rest, and first = 0.
     */
    public IntList() {
        /* NOTE: public IntList () { }  would also work. */
        this(0, null);
    }

/*    public void addlast(int i) {
        this.rest= new IntList(i, null);
    }
    public IntList of(int i0, int i1, int i2, int i3) {
        IntList x = new IntList(i0, null);
        this.addlast(i1);
        this.addlast(i2);
        this.addlast(i3);
        return x;
    }*/

    /**
     * Returns a list equal to L with all elements squared. Destructive.
     */
    public static void dSquareList(IntList L) {
        IntList p = L;
        while (p != null) {
            p.first = p.first * p.first;
            p = p.rest;
        }
    }

    /**
     * @return a version of the list with all elements squared, using iteration.
     * The list is not modified.
     */
    public static IntList squareListIterative(IntList L) {
        IntList Q = new IntList(L.first * L.first, null);
        IntList p = L.rest;
        IntList q = Q;
        while (p != null) {
            q.rest = new IntList(p.first * p.first, null);
            p = p.rest;
            q = q.rest;
        }
        return Q;
    }


    /**
     * @return returns a version of the list with all elements squared, using recursion.
     * The list is not modified.
     */
    public static IntList squareListRecursive(IntList L) {
        while (L != null) {
            return new IntList(L.first * L.first, squareListRecursive(L.rest));
        }
        return null;
    }

    public static IntList reverse(IntList x) {
        if (x == null) {
            return null;
        }
        IntList tail = x;
        IntList temp = x;
        int size = 1;
        while (tail.rest != null) {
            tail = tail.rest;
            size += 1;
        }
        IntList[] p = new IntList[size];
        for (int i = 0; i < size; i++) {
            p[i] = temp;
            temp = temp.rest;
        }
        for (int j = 0; j < size / 2; j++) {
            int t = p[j].first;
            p[j].first = p[size - j - 1].first;
            p[size - j - 1].first = t;
        }
        return x;
    }

    /**
     * @return Returns a list consisting of the elements of A followed by the
     * *  elements of B.  May modify items of A. Don't use 'new'.
     */

    public static IntList dcatenate(IntList A, IntList B) {
        IntList p = A;
        if (p == null) {
            return B;
        }
        if (B == null) {
            return A;
        }
        while (p.rest != null) {
            p = p.rest;
        }
        p.rest = B;
        return A;
    }

    /**
     * @return Returns a list consisting of the elements of A followed by the
     * * elements of B.  May NOT modify items of A.  Use 'new'.
     */
    public static IntList catenate(IntList A, IntList B) {
        if (A == null) {
            return B;
        }
        if (B == null) {
            return A;
        }
        IntList Q = new IntList(A.first, null);
        IntList p = A.rest;
        IntList q = Q;

        while (p != null) {
            q.rest = new IntList(p.first, null);
            p = p.rest;
            q = q.rest;
        }
        IntList p2 = Q;
        while (p2.rest != null) {
            p2 = p2.rest;
        }
        p2.rest = B;
        return Q;
    }


    /**
     * DO NOT MODIFY ANYTHING BELOW THIS LINE! Many of the concepts below here
     * will be introduced later in the course or feature some form of advanced
     * trickery which we implemented to help make your life a little easier for
     * the lab.
     */

    @Override
    public int hashCode() {
        return first;
    }

    /**
     * Returns a new IntList containing the ints in ARGS. You are not
     * expected to read or understand this method.
     */
    public static IntList of(Integer... args) {
        IntList result, p;

        if (args.length > 0) {
            result = new IntList(args[0], null);
        } else {
            return null;
        }

        int k;
        for (k = 1, p = result; k < args.length; k += 1, p = p.rest) {
            p.rest = new IntList(args[k], null);
        }
        return result;
    }

    /**
     * Returns true iff X is an IntList containing the same sequence of ints
     * as THIS. Cannot handle IntLists with cycles. You are not expected to
     * read or understand this method.
     */
    public boolean equals(Object x) {
        if (!(x instanceof IntList)) {
            return false;
        }
        IntList L = (IntList) x;
        IntList p;

        for (p = this; p != null && L != null; p = p.rest, L = L.rest) {
            if (p.first != L.first) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }

    /**
     * If a cycle exists in the IntList, this method
     * returns an integer equal to the item number of the location where the
     * cycle is detected.
     * <p>
     * If there is no cycle, the number 0 is returned instead. This is a
     * utility method for lab2. You are not expected to read, understand, or
     * even use this method. The point of this method is so that if you convert
     * an IntList into a String and that IntList has a loop, your computer
     * doesn't get stuck in an infinite loop.
     */

    private int detectCycles(IntList A) {
        IntList tortoise = A;
        IntList hare = A;

        if (A == null) {
            return 0;
        }

        int cnt = 0;


        while (true) {
            cnt++;
            if (hare.rest != null) {
                hare = hare.rest.rest;
            } else {
                return 0;
            }

            tortoise = tortoise.rest;

            if (tortoise == null || hare == null) {
                return 0;
            }

            if (hare == tortoise) {
                return cnt;
            }
        }
    }

    @Override
    /** Outputs the IntList as a String. You are not expected to read
     * or understand this method. */
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;

        for (IntList p = this; p != null; p = p.rest) {
            out.format("%s%d", sep, p.first);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }
}

