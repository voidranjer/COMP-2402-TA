package comp2402a3;

import java.util.LinkedList;
import java.util.Queue;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * An implementation of binary trees
 * @author morin
 *
 * @param <Node>
 */
public class BinaryTree<Node extends BinaryTree.BTNode<Node>> {

	public static class BTNode<Node extends BTNode<Node>> {
		public Node left;
		public Node right;
		public Node parent;
	}

	/**
	 * An extension of BTNode that you can actually instantiate.
	 */
	protected static class EndNode extends BTNode<EndNode> {
			public EndNode() {
				this.parent = this.left = this.right = null;
			}
	}

	/**
	 * Used to make a mini-factory
	 */
	protected Node sampleNode;

	/**
	 * The root of this tree
	 */
	protected Node r;

	/**
	 * This tree's "null" node
	 */
	protected Node nil;

	/**
	 * Create a new instance of this class
	 * @param sampleNode - a sample of a node that can be used
	 * to create a new node in newNode()
	 * @param nil - a node that will be used in place of null
	 */
	public BinaryTree(Node sampleNode, Node nil) {
		this.sampleNode = sampleNode;
		this.nil = nil;
		r = nil;
	}

	/**
	 * Create a new instance of this class
	 * @param sampleNode - a sample of a node that can be used
	 * to create a new node in newNode()
	 */
	public BinaryTree(Node sampleNode) {
		this.sampleNode = sampleNode;
	}

	/**
	 * Allocate a new node for use in this tree
	 * @return
	 */
	@SuppressWarnings({"unchecked"})
	protected Node newNode() {
		try {
			Node u = (Node)sampleNode.getClass().getDeclaredConstructor().newInstance();
			u.parent = u.left = u.right = nil;
			return u;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Compute the depth (distance to the root) of u
	 * @param u
	 * @return the distanct between u and the root, r
	 */
	public int depth(Node u) {
		int d = 0;
		while (u != r) {
			u = u.parent;
			d++;
		}
		return d;
	}

	/**
	 * Compute the size (number of nodes) of this tree
	 * @warning uses recursion so could cause a stack overflow
	 * @return the number of nodes in this tree
	 */
	public int size() {
		return size(r);
	}

	/**
	 * @return the size of the subtree rooted at u
	 */
	protected int size(Node u) {
		if (u == nil) return 0;
		return 1 + size(u.left) + size(u.right);
	}

	/**
	 * Compute the number of nodes in this tree without recursion
	 * @return
	 */
	public int size2() {
		Node u = r, prev = nil, next;
		int n = 0;
		while (u != nil) {
			if (prev == u.parent) {
				n++;
				if (u.left != nil) next = u.left;
				else if (u.right != nil) next = u.right;
				else next = u.parent;
			} else if (prev == u.left) {
				if (u.right != nil) next = u.right;
				else next = u.parent;
			} else {
				next = u.parent;
			}
			prev = u;
			u = next;
		}
		return n;
	}

	/**
	 * Compute the maximum depth of any node in this tree
	 * @return the maximum depth of any node in this tree
	 */
	public int height() {
		return height(r);
	}

	/**
	 * @return the height of the subtree rooted at u
	 */
	protected int height(Node u) {
		if (u == nil) return -1;
		return 1 + Math.max(height(u.left), height(u.right));
	}


	public String toString() {
		StringBuilder sb = new StringBuilder();
		toStringHelper(sb, r);
		return sb.toString();
	}

	protected void toStringHelper(StringBuilder sb, Node u) {
			if (u == null) {
				return;
			}
			sb.append('(');
			toStringHelper(sb, u.left);
			toStringHelper(sb, u.right);
			sb.append(')');
	}


	/**
	 * @ return an n-node BinaryTree that has the shape of a random
	 * binary search tree.
	 */
	public static BinaryTree<EndNode> randomBST(int n) {
		Random rand = new Random();
		EndNode sample = new EndNode();
		BinaryTree<EndNode> t = new BinaryTree<EndNode>(sample);
		t.r = randomBSTHelper(n, rand);
		return t;
	}

	protected static EndNode randomBSTHelper(int n, Random rand) {
		if (n == 0) {
			return null;
		}
		EndNode r = new EndNode();
		int ml = rand.nextInt(n);
		int mr = n - ml - 1;
		if (ml > 0) {
			r.left = randomBSTHelper(ml, rand);
			r.left.parent = r;
		}
		if (mr > 0) {
			r.right = randomBSTHelper(mr, rand);
			r.right.parent = r;
		}
		return r;
	}

	/**
	 * @return
	 */
	public boolean isEmpty() {
		return r == nil;
	}

	/**
	 * Make this tree into the empty tree
	 */
	public void clear() {
		r = nil;
	}

	/**
	 * Demonstration of a recursive traversal
	 * @param u
	 */
	public void traverse(Node u) {
		if (u == nil) return;
		traverse(u.left);
		traverse(u.right);
	}

	/**
	 * Demonstration of a non-recursive traversal
	 */
	public void traverse2() {
		Node u = r, prev = nil, next;
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

	/**
	 * Demonstration of a breadth-first traversal
	 */
	public void bfTraverse() {
		Queue<Node> q = new LinkedList<Node>();
		if (r != nil) q.add(r);
		while (!q.isEmpty()) {
			Node u = q.remove();
			if (u.left != nil) q.add(u.left);
			if (u.right != nil) q.add(u.right);
		}
	}

	/**
	 * Find the first node in an in-order traversal
	 * @return the first node reported in an in-order traversal
	 */
	public Node firstNode() {
		Node w = r;
		if (w == nil) return nil;
		while (w.left != nil)
			w = w.left;
		return w;
	}

	/**
	 * Find the node that follows w in an in-order traversal
	 * @param w
	 * @return the node that follows w in an in-order traversal
	 */
	public Node nextNode(Node w) {
		if (w.right != nil) {
			w = w.right;
			while (w.left != nil)
				w = w.left;
		} else {
			while (w.parent != nil && w.parent.left != w)
				w = w.parent;
			w = w.parent;
		}
		return w;
	}

	public int totalDepth() {
		if (r == null) {
			return -1;
		}
		Node u = r, prev = nil, next;
		int d = 0, totalDepth = 0;
		while (u != nil) {
			if (prev == u.parent) {
				totalDepth += d;
				if (u.left != nil) {
					next = u.left;
					d++;
				} else if (u.right != nil) {
					next = u.right;
					d++;
				}	else {
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
		return totalDepth;
	}

	public int totalLeafDepth() {
		if (r == null) {
			return -1;
		}
		Node u = r, prev = nil, next;
		int d = 0, totalDepth = 0;
		while (u != nil) {
			if (prev == u.parent) {
				if (u.left != nil) {
					next = u.left;
					d++;
				} else if (u.right != nil) {
					next = u.right;
					d++;
				}	else {
					totalDepth += d;
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
		return totalDepth;
	}

	public String bracketSequence() {
		if (r == null) {
			return ".";
		}
		StringBuilder sb = new StringBuilder();
		Node u = r, prev = nil, next;
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
		Map<Node,Integer> xmap = getXCoords();

		// setup the first layer
		List<Node> layer = new ArrayList<>();
		layer.add(r);
		while (layer.get(layer.size()-1).right != null) {
			layer.add(layer.get(layer.size()-1).right);
		}

		List<Node> nextLayer;
		while (!layer.isEmpty()) {
			nextLayer = new ArrayList<>();
			int xcur = 0;
			for (Node u : layer) {
				int ux = xmap.get(u);
				int deltax = ux - xcur;
				if (u == r || u.parent.left == u) {
					for (int z = 0; z < deltax; z++) w.print(" ");
				} else {
					for (int z = 0; z < deltax; z++) w.print("-");
				}
				w.print("*");
				xcur = ux+1;
				Node z = u.left;
				while (z != null) {
					nextLayer.add(z);
					z = z.right;
				}
			}
			w.println();
			xcur = 0;
			if (!nextLayer.isEmpty()) {
				for (int i = 0; i < nextLayer.size(); i++) {
					Node u = nextLayer.get(i);
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

	protected Map<Node,Integer> getXCoords() {
		Map<Node,Integer> widthMap = getWidths();
		Map<Node,Integer> xmap = new HashMap<>();
		Node u = r, prev = nil, next;
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

	protected Map<Node,Integer> getWidths() {
		Map<Node,Integer> widthMap = new HashMap<>();
		widthMap.put(null, 1);
		Node u = r, prev = nil, next;
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

	public static void main(String[] args) {
		System.out.println(randomBST(30));
	}

}
