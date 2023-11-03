package comp2402a3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

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
		Node<T>[] next;

		// record the length of edge
		int[] length;

		public Node(T ix, int h) {
			x = ix;
			next = (Node<T>[]) Array.newInstance(Node.class, h + 1);

			// initialize the length
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
	protected int[] index;
	// store the false condition
	protected Node<T>[] fStack;
	protected int[] fIndex;

	@SuppressWarnings("unchecked")
	public SkippityFast(Comparator<T> c) {
		this.c = c;
		n = 0;
		sentinel = new Node<T>(null, 32);
		stack = (Node<T>[]) Array.newInstance(Node.class, sentinel.next.length);
		index = new int[sentinel.next.length];
		fStack = (Node<T>[]) Array.newInstance(Node.class, sentinel.next.length);
		fIndex = new int[sentinel.next.length];
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
		int comp = 0;

		// index of u
		int j = -1;

		// record add length times
		int count = 0;
		while (r >= 0) {
			while (u.next[r] != null && (comp = c.compare(u.next[r].x, x)) < 0) {
				// each time move to right
				j += u.length[r];
				u = u.next[r];
			}

			if (u.next[r] != null && comp == 0) {
				// come back before // THIS IS NEW
				// this is for if we tried to add an element that already exists (we revisit all
				// touched nodes and undo the count increment because we didn't actually end up
				// adding anything)
				for (int i = 0; i < count; i++) {
					fStack[i].length[fIndex[i]]--;
				}
				return false;
			}
			// account for new node
			u.length[r]++;

			fStack[count] = u; // store we add element (in case we have to revisit to undo the changes if the
													// new element ends up being a duplicate element)
			fIndex[count] = r; // technically, this should be fHeight instead of fIndex. it keeps track of the
													// height of the node<T>
			count++;

			// store the index
			index[r] = j; // store the index of the node from which we go down, indexed at the height of
										// the drop. very smart because it remembers the index of node on add()
										// execution
										// (j is not stored in individual nodes). this index is used for the
										// "w.length[i] = stack[i].length[i] - (wIndex - index[i]);" calculations later
										// (page 96 and 97
										// of ODS textbook)
			stack[r] = u; // going down, store u
			r--;
		}

		// we get the this index
		int wIndex = j + 1;

		Node<T> w = new Node<T>(x, pickHeight());
		while (h < w.height()) {
			h++;
			index[h] = -1;
			stack[h] = sentinel; // height increased
		}

		for (int i = 0; i < w.next.length; i++) {
			w.next[i] = stack[i].next[i];
			stack[i].next[i] = w;

			w.length[i] = stack[i].length[i] - (wIndex - index[i]);

			stack[i].length[i] = wIndex - index[i];

		}

		n++;
		return true;
	}

	public boolean remove(T x) {
		boolean removed = false;
		Node<T> u = sentinel;
		int r = h;
		int comp = 0;

		// record minus length times
		int count = 0;
		while (r >= 0) {
			while (u.next[r] != null && (comp = c.compare(u.next[r].x, x)) < 0) {

				u = u.next[r];

			}
			// account for removing
			u.length[r]--;
			// store we add element
			fStack[count] = u;
			fIndex[count] = r;
			count++;

			// find the position
			if (u.next[r] != null && comp == 0) {
				// change length
				u.length[r] += u.next[r].length[r];
				removed = true;
				u.next[r] = u.next[r].next[r];
				if (u == sentinel && u.next[r] == null) {
					h--;
				}
			}
			r--;
		}

		if (removed) {
			n--;
		} else {
			// come back before
			for (int i = 0; i < count; i++) {
				fStack[i].length[fIndex[i]]++;
			}
		}
		return removed;
	}

	public T get(int i) {
		// This is just a copy of the slow version
		// TODO: You need to rewrite this method so that it is faster
		if (i < 0 || i > n - 1)
			return null;

		Node<T> u = sentinel;
		int r = h;
		int j = -1;
		while (r >= 0) {
			while (u.next[r] != null && j + u.length[r] < i) {
				j += u.length[r];
				u = u.next[r];
			}
			r--;
		}
		return u.next[0].x;
	}

	public int rangecount(T x, T y) {
		// This is just a copy of the slow version
		// TODO: You need to rewrite this method so that it is faster
		if (c.compare(x, y) > 0) {
			T temp = x;
			x = y;
			y = temp;
		}
		// record two value index
		int xIndex = -1;
		int yIndex = -1;

		xIndex = getXIndex(x);
		yIndex = getYIndex(y);

		// return the count
		return yIndex - xIndex;
	}

	// get the index of smallest value that bigger than x
	public int getXIndex(T x) {
		// finde x index
		Node<T> u = sentinel;
		int r = h;
		int j = -1;
		while (r >= 0) {
			while (u.next[r] != null && (c.compare(u.next[r].x, x)) < 0) {
				j += u.length[r];
				u = u.next[r];
			}
			r--;
		}
		return j + 1;
	}

	public int getYIndex(T x) {
		// finde x index
		Node<T> u = sentinel;
		int r = h;
		int j = -1;
		while (r >= 0) {
			while (u.next[r] != null && (c.compare(u.next[r].x, x)) <= 0) {
				j += u.length[r];
				u = u.next[r];
			}
			r--;
		}
		return j + 1;
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
				System.out.println("*********" + u.x + "***********");
				for (int i = 0; i <= u.height(); i++) {
					System.out.println("height " + i + ": " + "length: " + u.length[i]);

				}

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
