package comp2402a3;

import java.lang.reflect.Array;
import java.util.*;

/**
 * An implementation of skiplists for searching
 *
 * @param <T>
 */
public class SkippityFast<T extends Comparable<T>> implements IndexedSSet<T> {
    protected Comparator<T> c;

    protected static class Node<T> {
        // FIXME: Copy-paste from Pat Morin ODS SkiplistList
        T x;
        Node<T>[] next;
        int[] length;

        @SuppressWarnings("unchecked")
        public Node(T ix, int h) {
            x = ix;
            next = (Node[]) Array.newInstance(Node.class, h + 1);
            length = new int[h + 1];
        }

        public int height() {
            return next.length - 1;
        }
    }

    /**
     * This node<T> sits on the left side of the skiplist
     */
    protected Node<T> sentinel;

    /**
     * The maximum height of any element
     */
    public int height;

    /**
     * The number of elements stored in the skiplist
     */
    int elements;

    /**
     * A source of random numbers
     */
    Random rand;

    Node[] stack = new Node[64];
    int[] predIndex = new int[64];

    /**
     * Used by add(x) method
     */
    /// protected Node<T>[] stack; causing probolems

    @SuppressWarnings("unchecked")
    public SkippityFast(Comparator<T> c) {
        this.c = new CustomComparator<T>();
        elements = 0;
        sentinel = new Node<T>(null, 32);
        height = 0;
        rand = new Random();
    }

    public class CustomComparator<T extends Comparable<T>> implements Comparator<T> {
        public int compare(T a, T b) {
            return a.compareTo(b);
        }
    }

    public int compare(T a, T b) {
        return ((Comparable<T>) a).compareTo(b);
    }

    /**
     * The following methods are copy-pasted from Pat Morin ODS SkiplistList
     * I changed the variable names because his original ones are hard to keep track
     * of
     */

    /**
     * Find the node that precedes list index i in the skiplist.
     *
     * @param i - the value to search for
     * @return the predecessor of the node at index i or the final
     *         node if i exceeds size() - 1.
     */
    protected Node findPred(int i) {
        Node u = sentinel;
        int r = height;
        int j = -1; // index of the current node in list 0
        while (r >= 0) {
            while (u.next[r] != null && j + u.length[r] < i) {
                j += u.length[r];
                u = u.next[r];
            }
            r--;
        }
        return u;
    }

    public T get(int i) {
        if (i < 0 || i > elements - 1)
            throw new IndexOutOfBoundsException();
        return (T) findPred(i).next[0].x;
    }

    protected Node add(int i, Node w) {
        Node u = sentinel;

        int k = w.height();
        int r = height;
        int j = -1; // index of u
        while (r >= 0) {
            while (u.next[r] != null && j + u.length[r] < i) {
                j += u.length[r];
                u = u.next[r];
            }
            u.length[r]++; // accounts for new node in list 0
            if (r <= k) {
                w.next[r] = u.next[r];
                u.next[r] = w;
                w.length[r] = u.length[r] - (i - j);
                u.length[r] = i - j;
            }
            r--;
        }
        elements++;
        return u;
    }

    T remove(int i) {
        if (i < 0 || i > elements - 1)
            throw new IndexOutOfBoundsException();
        T x = null;
        Node u = sentinel;
        int r = height;
        int j = -1; // index of node u
        while (r >= 0) {
            while (u.next[r] != null && j + u.length[r] < i) {
                j += u.length[r];
                u = u.next[r];
            }
            u.length[r]--; // for the node we are removing
            if (j + u.length[r] + 1 == i && u.next[r] != null) {
                x = (T) u.next[r].x;
                u.length[r] += u.next[r].length[r];
                u.next[r] = u.next[r].next[r];
                if (u == sentinel && u.next[r] == null)
                    height--;
            }
            r--;
        }
        elements--;
        return x;
    }

    /**
     * End of copy-paste
     */

    public SkippityFast() {
        this(new DefaultComparator<T>());
    }

    /**
     * Simulate repeatedly tossing a coin until it comes up tails.
     * Note, this code will never generate a height greater than 32
     *
     * @return the number of coin tosses - 1
     */
    protected int pickHeight() {
        int z = rand.nextInt();
        int k = 0;
        int m = 1;
        while ((z & m) != 0) {
            k++;
            m <<= 1;
        }
        return k;
    }

    /**
     * Find the node<T> u that precedes the value x in the skiplist.
     *
     * @param x - the value to search for
     * @return a node<T> u that maximizes u.x subject to
     *         the constraint that u.x < x --- or sentinel if u.x >= x for
     *         all node<T>s x
     */
    protected Node<T> findPredNode(T x) {
        Node<T> u = sentinel;
        int r = height;
        while (r >= 0) {
            while (u.next[r] != null && compare(u.next[r].x, x) < 0) {
                u = u.next[r]; // go right in list r
            }
            r--; // go down into list r-1
        }
        return u;
    }

    public T find(T x) {
        Node<T> u = findPredNode(x);
        return u.next[0] == null ? null : u.next[0].x;
    }

    public T findGE(T x) {
        if (x == null) { // return first node<T>
            return sentinel.next[0] == null ? null : sentinel.next[0].x;
        }
        return find(x);
    }

    public T findLT(T x) {
        if (x == null) { // return last node<T>
            Node<T> u = sentinel;
            int r = height;
            while (r >= 0) {
                while (u.next[r] != null) {
                    u = u.next[r];
                }
                r--;
            }
            return u.x;
        }
        return findPredNode(x).x;
    }

    /**
     * Modification based on ODS SkiplistList
     */
    public boolean add(T x) {
        Node<T> u = sentinel;

        int level = this.height;
        int index = -1; // index of u
        int comp = 0;

        while (level >= 0) {
            while (u.next[level] != null && (comp = compare(u.next[level].x, x)) < 0) {
                index += u.length[level];
                u = u.next[level];
            }

            if (u.next[level] != null && comp == 0) {
                return false;
            }

            predIndex[level] = index;
            stack[level] = u; // going down, store u
            level--;
        }

        index++;
        int newHeight = pickHeight();
        Node<T> newNode = new Node<>(x, newHeight);
        if (newHeight > this.height) {
            for (int i = this.height + 1; i < newHeight + 1; i++) {
                stack[i] = sentinel;
                predIndex[i] = -1; // index of sentinel is always -1. for example, length of SENTINEL -> NODES[2] =
                                   // 2-(-1) = 3.
            }
            this.height = newHeight;
        }
        for (int i = 0; i <= this.height; i++)
            stack[i].length[i]++;
        for (int i = 0; i < newNode.next.length; i++) {
            comp = index - predIndex[i];
            newNode.next[i] = stack[i].next[i];
            stack[i].next[i] = newNode;
            newNode.length[i] = stack[i].length[i] - comp;
            stack[i].length[i] = comp;
        }
        elements++;
        return true;
    }

    public boolean remove(T x) {
        boolean removed = false;
        Node<T> u = sentinel;
        int level = height;
        while (level >= 0) {
            while (u.next[level] != null && compare(u.next[level].x, x) < 0) {
                u = u.next[level];
            }
            stack[level] = u;
            level--;
        }
        if (u.next[0] != null && compare(x, u.next[0].x) == 0) {
            removed = true;
            for (level = height; level >= 0; level--) {
                u = stack[level];
                u.length[level]--;
                if (u.next[level] != null && compare(x, u.next[level].x) == 0) {
                    u.length[level] += u.next[level].length[level];
                    u.next[level] = u.next[level].next[level];
                }
            }
            for (level = height; level >= 0; level--) {
                if (sentinel.next[level] != null) {
                    height = level;
                    break;
                }
            }
        }
        if (removed)
            elements--;
        return removed;
    }

    public int rangecount(T x, T y) {
        if (compare(x, y) > 0) {
            return findIndexEnd(x) - findIndex(y);
        }
        return findIndexEnd(y) - findIndex(x);
    }

    protected int findIndexExact(T x) {
        Node<T> u = sentinel;
        int level = height;
        int index = -1;
        while (level >= 0) {
            while (u.next[level] != null && compare(u.next[level].x, x) <= 0) {
                index += u.length[level];
                u = u.next[level];
            }
            level--;
        }
        if (u.x == null || compare(x, u.x) != 0)
            return -1;
        return index;
    }

    protected int findIndex(T x) {
        Node<T> u = sentinel;
        int level = height;
        int index = 0;
        while (level >= 0) {
            while (u.next[level] != null && compare(u.next[level].x, x) < 0) {
                index += u.length[level];
                u = u.next[level];
            }
            level--;
        }
        return index;
    }

    protected int findIndexEnd(T x) {
        Node<T> u = sentinel;
        int level = height;
        int index = 0;
        while (level >= 0) {
            while (u.next[level] != null && compare(u.next[level].x, x) <= 0) {
                index += u.length[level];
                u = u.next[level];
            }
            level--;
        }
        return index;
    }

    public void clear() {
        elements = 0;
        height = 0;
        Arrays.fill(sentinel.next, null);
    }

    public int size() {
        return elements;
    }

    public Comparator<T> comparator() {
        return c;
    }

    /**
     * Create a new iterator in which the next value in the iteration is u.next.x
     *
     * @param u
     * @return
     */
    protected Iterator<T> iterator(Node<T> u) {
        class SkiplistIterator implements Iterator<T> {
            Node<T> u, prev;

            public SkiplistIterator(Node<T> u) {
                this.u = u;
                prev = null;
            }

            public boolean hasNext() {
                return u.next[0] != null;
            }

            public T next() {
                prev = u;
                u = u.next[0];
                return u.x;
            }

            public void remove() {
                // Not constant time
                SkippityFast.this.remove(prev.x);
            }
        }
        return new SkiplistIterator(u);
    }

    public Iterator<T> iterator() {
        return iterator(sentinel);
    }

    public Iterator<T> iterator(T x) {
        return iterator(findPredNode(x));
    }
}
