package comp2402a3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

import comp2402a3.RankedSSet;

/**
 * An implementation of skiplists for searching
 *
 * @author morin
 *
 * @param <T>
 */
public class FastSkiplistRankedSSet<T> implements RankedSSet<T> {
	protected Comparator<T> c;

	@SuppressWarnings("unchecked")
	protected static class Node<T> {
		T x;
		Node<T>[] next;
		int[] length;
		public Node(T ix, int h) {
			x = ix;
			next = (Node<T>[])Array.newInstance(Node.class, h+1);
			length = new int[h+1];
		}
		public int height() {
			return next.length - 1;
		}
		// public String toString() {
		// 	return x.toString();
		// }
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
	protected int[] stacki;

	@SuppressWarnings("unchecked")
	public FastSkiplistRankedSSet(Comparator<T> c) {
		this.c = c;
		n = 0;
		sentinel = new Node<T>(null, 32);
		stack = (Node<T>[])Array.newInstance(Node.class, sentinel.next.length);
		stacki = new int[sentinel.next.length];
		h = 0;
		rand = new Random();
	}

	public FastSkiplistRankedSSet() {
		this(new comp2402a3.DefaultComparator<T>());
	}

	/**
	 * Find the node<T> u that precedes the value x in the skiplist.
	 *
	 * @param x - the value to search for
	 * @return a node<T> u that maximizes u.x subject to
	 * the constraint that u.x < x --- or sentinel if u.x >= x for
	 * all node<T>s x
	 */
	protected Node<T> findPredNode(T x) {
		Node<T> u = sentinel;
		int r = h;
		while (r >= 0) {
			while (u.next[r] != null && c.compare(u.next[r].x,x) < 0)
				u = u.next[r];   // go right in list r
			r--;               // go down into list r-1
		}
		return u;
	}

	protected class Pair {
		public int i;
		public Node<T> u;

		Pair(int i, Node<T> u) {
			this.i = i;
			this.u = u;
		}
	}

	protected Pair findPredNode2(T x) {
		Node<T> u = sentinel;
		int r = h;
		int i = -1;
		while (r >= 0) {
			while (u.next[r] != null && c.compare(u.next[r].x,x) < 0) {
				i += u.length[r];
				u = u.next[r];   // go right in list r
			}
			r--;               // go down into list r-1
		}
		return new Pair(i, u);
	}

	public T find(T x) {
		Node<T> u = findPredNode(x);
		return u.next[0] == null ? null : u.next[0].x;
	}

	public T findGE(T x) {
		if (x == null) {   // return first node<T>
			return sentinel.next[0] == null ? null : sentinel.next[0].x;
		}
		return find(x);
	}

	public T findLT(T x) {
		if (x == null) {  // return last node<T>
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

	protected Node<T> getPred(int i) {
			Node<T> u = sentinel;
			int r = h;
			int j = -1;   // index of the current node in list 0
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
		if (i < 0 || i > n-1) throw new IndexOutOfBoundsException();
		Node<T> p = getPred(i);
		return p.next[0].x;
	}

	public int rank(T x) {
		Pair pair = findPredNode2(x);
		return pair.i+1;
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
			stack[r] = u;
			r--;
		}
		if (c.compare(x, u.next[0].x) == 0) {
			// we're really removing something
			for (r = h; r >= 0; r--) {
				u = stack[r];
				u.length[r]--;
				if (u.next[r] != null && c.compare(x, u.next[r].x) == 0) {
					u.length[r] += u.next[r].length[r];
					u.next[r] = u.next[r].next[r];
					if (u == sentinel && u.next[r] == null)
						h--;  // height has gone down
				}
			}
			n--;
			return true;
		}
		return false;
	}


	/**
	 * Simulate repeatedly tossing a coin until it comes up tails.
	 * Note, this code will never generate a height greater than 32
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
	 * TODO: Constant time removal requires the use of a skiplist finger (a stack)
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
				FastSkiplistRankedSSet.this.remove(prev.x);
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

	public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int r = h; r >= 0; r--) {
				int rank = -1;
				Node<T> u = sentinel;
				while (u != null) {
					sb.append('*');
					if (u.next[r] != null) {
						for (int i = 0; i < 2*u.length[r]-1; i++) {
							sb.append('-');
						}
					}
					u = u.next[r];
				}
				sb.append('\n');
			}
			return sb.toString();
	}

	public boolean add(T x) {
		Node<T> u = sentinel;
		int r = h;
		int comp = 0;
		int rank = -1;
		while (r >= 0) {
			while (u.next[r] != null
			       && (comp = c.compare(u.next[r].x,x)) < 0) {
	 			 	 rank += u.length[r];
					 u = u.next[r];
			}
			if (u.next[r] != null && comp == 0) return false;
			stacki[r] = rank;
			stack[r--] = u;          // going down, store u
		}
		// We made it this far without finding x, so we insert it

		Node<T> w = new Node<T>(x, pickHeight());
		while (h < w.height()) {
			stack[++h] = sentinel;   // height increased
			stacki[h] = -1;
		}

		// length of each edge "over" x increases by 1
		for (int i = 0; i <= h; i++) {
			stack[i].length[i]++;
		}

		rank++;  // now rank = rank(x)
		for (int i = 0; i < w.next.length; i++) {
			// these two lines split w into the L_i
			w.next[i] = stack[i].next[i];
			stack[i].next[i] = w;
			// now we split the length of an edge into two
			w.length[i] = stack[i].length[i] + stacki[i] - rank;
			stack[i].length[i] = rank-stacki[i];
		}
		n++;
		return true;
	}

	public static void main(String[] args) {
		int n = 20;

		Random rand = new java.util.Random(0);
		RankedSSet<Integer> rss = new FastSkiplistRankedSSet<>();
		for (int i = 0; i < n; i++) {
			rss.add(rand.nextInt(3*n));
		}
		System.out.print("Contents: ");
		for (Integer x : rss) {
			System.out.print(x + ",");
		}
		System.out.println();

		System.out.println("size()=" + rss.size());

		for (int i = 0; i < rss.size(); i++) {
			Integer x = rss.get(i);
			System.out.println("get(" + i + ")=" + x);
		}

		for (Integer x = 0; x < 3*n+1; x++) {
			int i = rss.rank(x);
			System.out.println("rank(" + x + ")=" + i);
		}
	}
}
