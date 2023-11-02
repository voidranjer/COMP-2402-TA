package comp2402a3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.ArrayList;

/**
 * An implementation of skiplists for searching
 *
 *
 * @param <T>
 */
public class SkippityFast<T> implements IndexedSSet<T> {
	protected Comparator<T> c;

	@SuppressWarnings("unchecked")
	protected static class Node<T> {
		T x;
		// ArrayList<Node<T>> next;
		Node<T>[] next;
		int[] length; // [LIST FUNCTIONALITY]: length of jumps at each level
		int index; // [LIST FUNCTIONALITY]: index of Node<T> in list

		public Node(T ix, int h, int index) {
			x = ix;
			next = (Node<T>[]) Array.newInstance(Node.class, h + 1);
			length = new int[h + 1];
			this.index = index;
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
	int h;

	/**
	 * The number of elements stored in the skiplist
	 */
	int n;

	/**
	 * A source of random numbers
	 */
	Random rand;

	/**
	 * Used by add(x) method
	 */
	protected Node<T>[] stack;

	@SuppressWarnings("unchecked")
	public SkippityFast(Comparator<T> c) {
		this.c = c;
		n = 0;
		sentinel = new Node<T>(null, 32, -1);
		stack = (Node<T>[]) Array.newInstance(Node.class, sentinel.next.length);
		h = 0;
		rand = new Random();
	}

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
		int r = h;
		while (r >= 0) {
			while (u.next[r] != null && c.compare(u.next[r].x, x) < 0)
				u = u.next[r]; // go right in list r
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
			int r = h;
			while (r >= 0) {
				while (u.next[r] != null)
					u = u.next[r];
				r--;
			}
			return u.x;
		}
		return findPredNode(x).x;
	}

	public boolean add(T x) {
		Node<T> u = sentinel;

		int r = h;
		int j = -1; // [LIST FUNCTIONALITY]: index of nodes in list
		int comp = 0;

		while (r >= 0) {
			// move to the furthest right possible before going down
			while (u.next[r] != null && (comp = c.compare(u.next[r].x, x)) < 0) {
				j += u.length[r]; // [LIST FUNCTIONALITY]: increment list index (by the distance of the jump)
				u = u.next[r];
			}

			u.length[r]++; // [LIST FUNCTIONALITY]: increment the length of jump for every path that we go
											// one level down from

			// element already exists in the set (because comp == 0)
			if (u.next[r] != null && comp == 0)
				return false;

			// already at the furthest right edge, going down now, and storing u
			stack[r] = u;

			r--;
		}

		// int wIndex = j; // [LIST FUNCTIONALITY]: index of w in list
		Node<T> w = new Node<T>(x, pickHeight(), j);

		// height of new node exceeds current height (of sentinel)
		while (h < w.height())
			stack[++h] = sentinel; // add sentinel to stack

		// Inserting w: stack[i] -> w -> old stack[i].next[i]
		for (int i = 0; i < w.next.length; i++) {
			w.next[i] = stack[i].next[i]; // points w to nodes that are supposed to come after w
			stack[i].next[i] = w; // points nodes before w to w

			int i_j = w.index - u.index; // [LIST FUNCTIONALITY]
			w.length[i] = u.length[i] - (i_j); // [LIST FUNCTIONALITY]
			u.length[i] = i_j; // [LIST FUNCTIONALITY]
		}

		n++;

		return true;
	}

	public boolean remove(T x) {
		boolean removed = false;
		Node<T> u = sentinel;
		int r = h;
		int comp = 0;
		while (r >= 0) {
			while (u.next[r] != null
					&& (comp = c.compare(u.next[r].x, x)) < 0) {
				u = u.next[r];
			}
			if (u.next[r] != null && comp == 0) {
				removed = true;
				u.next[r] = u.next[r].next[r];
				if (u == sentinel && u.next[r] == null)
					h--; // height has gone down
			}
			r--;
		}
		if (removed)
			n--;
		return removed;
	}

	public T get(int i) {
		// This is just a copy of the slow version
		// TODO: You need to rewrite this method so that it is faster
		Iterator<T> it = this.iterator();
		for (int j = 0; j < i; j++) {
			it.next();
		}
		return it.next();
	}

	public int rangecount(T x, T y) {
		// This is just a copy of the slow version
		// TODO: You need to rewrite this method so that it is faster
		if (c.compare(x, y) > 0) {
			T temp = x;
			x = y;
			y = temp;
		}
		int count = 0;
		Iterator<T> it = this.iterator();
		for (int i = 0; i < n; i++) {
			T item = it.next();
			if (c.compare(item, x) >= 0 && c.compare(item, y) <= 0)
				count++;
		}
		return count;
	}

	public void clear() {
		n = 0;
		h = 0;
		Arrays.fill(sentinel.next, null);
	}

	public int size() {
		return n;
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
