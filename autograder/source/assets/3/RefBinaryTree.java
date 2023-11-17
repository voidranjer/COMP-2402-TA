import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import comp2402a3.BinaryTree;
import java.util.*;

/**
 * An implementation of binary trees
 * @author morin
 *
 * @param <XNode>
 */
public class RefBinaryTree extends BinaryTree<XNode> {

	/**
	 * Create a new instance of this class
	 * @param sampleNode - a sample of a node that can be used
	 * to create a new node in newNode()
	 * @param nil - a node that will be used in place of null
	 */
	public RefBinaryTree() {
		super(new XNode());
	}

	/**
	 * Demonstration of a non-recursive traversal
	 */
	public void traverse2() {
		XNode u = r, prev = nil, next;
		while (u != nil) {
			if (prev == u.parent) {
				if (u.left != nil) {
					next = u.left;
				} else if (u.right != nil) {
					next = u.right;
				}	else {
					next = u.parent;
				}
			} else if (prev == u.left) {
				if (u.right != nil) {
					next = u.right;
				} else {
					next = u.parent;
				}
			} else {
				next = u.parent;
			}
			prev = u;
			u = next;
		}
	}

	public int refLeafAndOnlyLeaf() {
		XNode u = r, prev = nil, next;
		int nleaves = 0;
		while (u != nil) {
			if (prev == u.parent) {
				if (u.left != nil) {
					next = u.left;
				} else if (u.right != nil) {
					next = u.right;
				}	else {
					next = u.parent;
					nleaves++;
				}
			} else if (prev == u.left) {
				if (u.right != nil) {
					next = u.right;
				} else {
					next = u.parent;
				}
			} else {
				next = u.parent;
			}
			prev = u;
			u = next;
		}
		return nleaves;
	}

	public int refDawnOfSpring() {
		if (r == null) {
			return -1;
		}
		XNode u = r, prev = nil, next;
		int d = 0, firstDepth = Integer.MAX_VALUE;
		while (u != nil) {
			if (prev == u.parent) {
				if (u.left != nil) {
					next = u.left;
					d++;
				} else if (u.right != nil) {
					next = u.right;
					d++;
				}	else {
					if(d < firstDepth)
						firstDepth = d;
					next = u.parent;
					d--;
				}
			} else if (prev == u.left) {
				if (u.right != nil) {
					next = u.right;
					d++;
				} else {
					next = u.parent;
					d--;
				}
			} else {
				next = u.parent;
				d--;
			}
			prev = u;
			u = next;
		}
		return firstDepth;
	}

	public int refMonkeyLand() {
		Queue<XNode> q = new LinkedList<XNode>();
		int mWidth = 0;
		if (r != nil) q.add(r);
		while (!q.isEmpty()) {
			if(q.size() > mWidth)
				mWidth = q.size();
			Queue<XNode> tempq = new LinkedList<XNode>();
			while (!q.isEmpty()){
				XNode u = q.remove();
				if (u.left != nil) tempq.add(u.left);
				if (u.right != nil) tempq.add(u.right);
			}
			q = tempq;
		}
		return mWidth;
	}

	public String refBracketSequence() {
		if (r == null) {
			return ".";
		}
		StringBuilder sb = new StringBuilder();
		XNode u = r, prev = nil, next;
		while (u != nil) {
			if (prev == u.parent) {
				sb.append("(");
				if (u.left != nil) {
					next = u.left;
				} else if (u.right != nil) {
					sb.append(".");
					next = u.right;
				}	else {
					sb.append("..");
					next = u.parent;
					sb.append(")");
				}
			} else if (prev == u.left) {
				if (u.right != nil) {
					//sb.append("(");
					next = u.right;
				} else {
					sb.append(".");
					next = u.parent;
					sb.append(")");
				}
			} else {
				next = u.parent;
				sb.append(")");
			}
			prev = u;
			u = next;
		}
		return sb.toString();
	}

	public void prettyPrint(PrintWriter w) {
		// compute every node's x-coordinate
		if(r == null)
			return;
		Map<XNode,Integer> xmap = getXCoords();

		// setup the first layer
		List<XNode> layer = new ArrayList<>();
		layer.add(r);
		while (layer.get(layer.size()-1).right != null) {
			layer.add(layer.get(layer.size()-1).right);
		}

		List<XNode> nextLayer;
		while (!layer.isEmpty()) {
			nextLayer = new ArrayList<>();
			int xcur = 0;
			for (XNode u : layer) {
				int ux = xmap.get(u);
				int deltax = ux - xcur;
				if (u == r || u.parent.left == u) {
					for (int z = 0; z < deltax; z++) w.print(" ");
				} else {
					for (int z = 0; z < deltax; z++) w.print("-");
				}
				w.print("*");
				xcur = ux+1;
				XNode z = u.left;
				while (z != null) {
					nextLayer.add(z);
					z = z.right;
				}
			}
			w.println();
			xcur = 0;
			if (!nextLayer.isEmpty()) {
				for (int i = 0; i < nextLayer.size(); i++) {
					XNode u = nextLayer.get(i);
					int ux = xmap.get(u);
					int deltax = ux - xcur;
					for (int z = 0; z < deltax; z++) w.print(" ");
					if (u.parent.left == u) {
						w.print("|");
					} else {
						w.print(" ");
					}
					xcur = ux+1;
				}
				w.println();
			}
			layer = nextLayer;
		}
	}

	protected Map<XNode,Integer> getXCoords() {
		Map<XNode,Integer> widthMap = getWidths();
		Map<XNode,Integer> xmap = new HashMap<>();
		XNode u = r, prev = nil, next;
		while (u != nil) {
			if (prev == u.parent) {
				if (u.parent == null) {
					xmap.put(u, 0);
				} else if (u.parent.left == u) {
					xmap.put(u, xmap.get(u.parent));
				} else if (u.parent.right == u) {
					xmap.put(u, xmap.get(u.parent) + widthMap.get(u.parent.left) + 1);
				}
				if (u.left != nil) {
					next = u.left;
				} else if (u.right != nil) {
					next = u.right;
				}	else {
					next = u.parent;
				}
			} else if (prev == u.left) {
				if (u.right != nil) {
					next = u.right;
				} else {
					next = u.parent;
				}
			} else {
				next = u.parent;
			}
			prev = u;
			u = next;
		}
		return xmap;
	}

	protected Map<XNode,Integer> getWidths() {
		Map<XNode,Integer> widthMap = new HashMap<>();
		widthMap.put(null, 1);
		XNode u = r, prev = nil, next;
		while (u != nil) {
			if (prev == u.parent) {
				if (u.left != nil) {
					next = u.left;
				} else if (u.right != nil) {
					next = u.right;
				}	else {
					next = u.parent;
					widthMap.put(u, 1);
				}
			} else if (prev == u.left) {
				if (u.right != nil) {
					next = u.right;
				} else {
					next = u.parent;
					widthMap.put(u, widthMap.get(u.left));
				}
			} else {
				next = u.parent;
				widthMap.put(u, widthMap.get(u.left) + 1 + widthMap.get(u.right));
			}
			prev = u;
			u = next;
		}
		return widthMap;
	}

	public String treeToString() {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		PrintWriter pw = new PrintWriter(ps);
		prettyPrint(pw);
		pw.flush();
		return os.toString();
	}

	public static RefBinaryTree randomMBST(int n) {
		Random rand = new Random();

		RefBinaryTree t = new RefBinaryTree();
		t.r = randomMBSTHelper(n, rand);
		return t;
	}

	protected static XNode randomMBSTHelper(int n, Random rand) {
		if (n == 0) {
			return null;
		}
		XNode r = new XNode();
		int ml = rand.nextInt(n);
		int mr = n - ml - 1;
		if (ml > 0) {
			r.left = randomMBSTHelper(ml, rand);
			r.left.parent = r;
		}
		if (mr > 0) {
			r.right = randomMBSTHelper(mr, rand);
			r.right.parent = r;
		}
		return r;
	}

	protected static RefBinaryTree leftLeaning(int n) {
		RefBinaryTree t = new RefBinaryTree();
		t.r = new XNode();
		XNode u = t.r;
		for (int i = 1; i < n; i++) {
			u.left = new XNode();
			u.left.parent = u;
			u = u.left;
		}
		return t;
	}

	protected static RefBinaryTree rightLeaning(int n) {
		RefBinaryTree t = new RefBinaryTree();
		t.r = new XNode();
		XNode u = t.r;
		for (int i = 1; i < n; i++) {
			u.right = new XNode();
			u.right.parent = u;
			u = u.right;
		}
		return t;
	}

	public void addLongPath(int n) {
		if(r == null)
			r = newNode();
		XNode u = r;
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
				if (rand.nextBoolean()) {
					if (u.left == null) {
						u.left = newNode();
						u.left.parent = u;
						u = u.left;
					}
				} else {
					u.right = newNode();
					u.right.parent = u;
					u = u.right;
				}
		}
	}

	protected static RefBinaryTree longPath(int n) {
		RefBinaryTree t = new RefBinaryTree();
		t.r = new XNode();
		XNode u = t.r;
		for (int i = 1; i < n; i++) {
			System.out.println(u);
			u.right = new XNode();
			u.right.parent = u;
			u = u.right;
		}
		return t;
	}
}
