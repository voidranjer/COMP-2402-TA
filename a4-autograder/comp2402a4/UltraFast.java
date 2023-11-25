package comp2402a4;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class UltraFast implements UltraStack {
  List<Integer> ds;
  long[] sums;
  int[] max;
  int height, resizeCount;
  int stackAt, start;

  public UltraFast() {
    ds = new ArrayList<Integer>();
    max = new int[3];
    sums = new long[3];
    height = 2;
    stackAt = 1;
    start = 1;
    resizeCount = 0;
  }

  public void push(int x) {
    // System.out.println(stackAt);
    //// System.out.println(max.length);
    //// System.out.println("VAELUE : " + x);
    //// System.out.println("MAX ARRAY: " + Arrays.toString(max));
    //// System.out.println("sums ARRAY: " + Arrays.toString(sums));
    // System.out.println(resizeCount);
    if (stackAt == max.length) {
      // Resize the arrays
      resize(stackAt * 2 + 1);
      stackAt = (int) (sums.length - (Math.pow(2, resizeCount)));
    }

    ds.add(x);
    max[stackAt] = x;
    sums[stackAt] = x;
    int curr = stackAt;
    // System.out.println("STACK AT:" + stackAt);
    while (curr >= 1) {

      int parent = (curr - 1) >> 1;

      if (curr % 2 == 0) {
        max[parent] = Math.max(max[curr], max[curr - 1]);
        sums[parent] = sums[curr] + sums[curr - 1];
        // System.out.println(sums[curr]);
      } else {
        max[parent] = Math.max(max[curr], max[curr + 1]);
        sums[parent] = sums[curr] + sums[curr + 1];
      }
      curr = parent;
      // System.out.println(curr);
    }

    // System.out.println("MAX ARRAY: " + Arrays.toString(max));
    // System.out.println("sums ARRAY: " + Arrays.toString(sums));

    stackAt++;

  }

  public Integer pop() {
    if (ds.isEmpty())
      return null;

    // System.out.println("STACK AT" + stackAt);
    max[stackAt - 1] = 0;
    sums[stackAt - 1] = 0;
    int curr = stackAt - 1;

    while (curr >= 1) {

      int parent = (curr - 1) >> 1;
      // System.out.println("CURR INDEX: " + curr);
      // System.out.println("PARENT INDEXL " + parent);
      // System.out.println("SUMS AVLUE: " + sums[curr]);
      // System.out.println("PARENT VALUE: " + sums[parent]);
      if (curr % 2 == 0) {
        max[parent] = Math.max(max[curr], max[curr - 1]);
        sums[parent] = sums[curr] + sums[curr - 1];
      } else {
        max[parent] = max[curr];
        sums[parent] = sums[curr];
      }
      curr = parent;
    }
    //
    // System.out.println("MAX ARRAY: " + Arrays.toString(max));
    // System.out.println("sums ARRAY: " + Arrays.toString(sums));
    stackAt--;

    return ds.remove(ds.size() - 1);
  }

  public Integer get(int i) {
    if (i < 0 || i >= ds.size())
      return null;
    return ds.get(i);
  }

  // DONE
  public Integer set(int i, int x) {
    // System.out.println("MAX ARRAY BEFORE: " + Arrays.toString(max));
    // System.out.println("SUMS ARRAY BEFORE: " + Arrays.toString(sums));

    if (i < 0 || i >= ds.size())
      return null;

    int curr = start + i;

    max[curr] = x;
    sums[curr] = x;

    while (curr >= 1) {

      int parent = (curr - 1) >> 1;
      // System.out.println(parent);

      if (curr % 2 == 0) {
        max[parent] = Math.max(max[curr], max[curr - 1]);
        sums[parent] = sums[curr] + sums[curr - 1];
      } else {
        max[parent] = Math.max(max[curr], max[curr + 1]);
        sums[parent] = sums[curr] + sums[curr + 1];
      }
      curr = parent;
    }

    // System.out.println("MAX ARRAY: " + Arrays.toString(max));
    // System.out.println("sums ARRAY: " + Arrays.toString(sums));

    return ds.set(i, x);
  }

  // DONE
  public Integer max() {
    if (ds.isEmpty()) {
      return null;
    }
    return max[0];
  }

  public long ksum(int k) {

    if (k <= 0)
      return 0;

    if (k == 1) {
      return ds.get(ds.size() - 1);
    }

    // System.out.println(ds.size() + start);
    int curr = start + ds.size() - k;
    // System.out.println("STARTING INDEX " + curr);
    long leftNodeSums = 0;

    while (curr >= 1) {

      int parent = (curr - 1) >> 1;
      // System.out.println(parent);

      if (curr % 2 == 0) {
        // sum += sums[curr];
        leftNodeSums += sums[curr - 1];
        // System.out.println("LEFT NODE SUMS " + leftNodeSums);
      }
      curr = parent;
    }

    return sums[0] - leftNodeSums;
  }

  public int size() {
    return ds.size();
  }

  public Iterator<Integer> iterator() {
    return ds.iterator();
  }

  private void resize(int newSize) {
    // System.out.println("NEWSIZE: "+ newSize);
    resizeCount++;
    start = stackAt;
    // System.out.println("START " + start);
    int[] newMax = new int[newSize];
    long[] newSums = new long[newSize];

    int index = 0;

    newSums[1] = sums[0];

    int oldIn = 0;
    int newIn = 1;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < (1 << i); j++) {
        newSums[newIn] = sums[oldIn];
        newMax[newIn] = max[oldIn];

        oldIn++;
        newIn++;
      }
      newIn += (1 << i);

      // System.out.println("NEW IN" + newIn);

    }

    // System.out.println(Arrays.toString(newSums));
    max = newMax;

    sums = newSums;

    height++;
  }

}