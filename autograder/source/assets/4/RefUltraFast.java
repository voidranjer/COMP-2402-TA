import comp2402a4.UltraStack;
import java.util.Iterator;

public class RefUltraFast implements UltraStack {
  int n;  // number of items stored in the structure
  int h;  // height of the complete binary trees
  long[] sum; // the stack items are at the leaf nodes, the higher level nodes have the partial sums
  int[] max; // the stack items are at the leaf nodes, the higher level nodes have the partial maximums

  public RefUltraFast() {
    n = 0;
    h = 0;
    sum = new long[(1<<h) - 1];
    max = new int[(1<<h) - 1];
  }

  protected void grow() {
    // the height of our trees is going up by 1
    h += 1;
    int new_size = (1<<h) - 1; // tree size = 2^h - 1
    long[] tmpsum = new long[new_size];
    int[] tmpmax = new int[new_size];
    if(new_size > 1){
      // copy each level of old tree into the left subtree, right subtree is empty
      for(int i=0; i<n; i=2*i+1){ // each level
        for(int j=0; j<=i; j++){ // each node
          tmpsum[2*i+1+j] = sum[i+j];
          tmpmax[2*i+1+j] = max[i+j];
        }
      }
      // new root is the old root for both sum and max
      tmpsum[0] = tmpsum[1];
      tmpmax[0] = tmpmax[1];
    }
    sum = tmpsum;
    max = tmpmax;
  }

  protected void updatesum(int i, int delta) {
    // convert stack index to tree index
    int idx = (1<<(h-1))-1 + i;
    // propagate change of sum to the root
    while(idx >= 0){
      sum[idx] += delta;
      idx = (idx-1)>>1;
    }
  }

  protected void updatemax(int i, int x) {
    // convert stack index to tree index
    int idx = (1<<(h-1))-1 + i;
    // propagate max update to root by replacing parent with maximum of current node and its sibling
    max[idx] = x;
    while(idx > 0){
      int sibling;
      if((idx & 1) == 0)
        sibling = idx - 1;
      else sibling = idx + 1;
      int maxvalue = Math.max(max[idx], max[sibling]);
      idx = (idx-1)>>1;
      max[idx] = maxvalue;
    }
  }

  public void push(int x) {
    if (n == (1<<h)>>1) {
      grow();
    }
    int i = n++;
    updatesum(i, x);
    updatemax(i, x);
  }

  public Integer pop() {
    if(n == 0) return null;
    int i = n-1;
    Integer x = get(i);
    updatesum(i, -x);
    updatemax(i, 0);
    n--;
    return x;
  }

  public Integer get(int i) {
    if (i < 0 || i >= n) return null;
    int idx = (1<<(h-1))-1 + i;
    return (int) sum[idx];
  }

  public Integer set(int i, int x) {
    if (i < 0 || i >= n) return null;
    int oldx = get(i);
    int delta = x - oldx;
    updatesum(i, delta);
    updatemax(i, x);
    return oldx;
  }

  public Integer max(){
    if(n == 0) return null;
    return max[0];
  }

  public long ksum(int k) {
    if(n == 0 || k == 0) return 0;
    if(k > n) k = n;
    long sum = 0;
    // going from leaf to root, subtract left sibling (if any) -> same as subtracting prefix sum
    int idx = (1<<(h-1))-1 + n-k;
    while (idx > 0){
      if((idx & 1) == 0){
        sum -= this.sum[idx-1];
      }
      idx = (idx-1)>>1;
    }
    return sum + this.sum[0];
  }

  public int size() {
    return n;
  }

  public Iterator<Integer> iterator() {
    return new Iterator<Integer>() {
      int i = 0;
      public Integer next() {
        return get(i++);
      }

      public boolean hasNext() {
        return i < n;
      }
    };
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append('[');
    for (int i = 0; i < n; i++) {
      sb.append(Integer.toString(get(i)));
      if (i < n-1) {
        sb.append(',');
      }
    }
    sb.append(']');
    return sb.toString();
  }
}
