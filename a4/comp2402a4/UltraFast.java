package comp2402a4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PrimitiveIterator;

import comp2402a4.UltraStack;

public class UltraFast implements UltraStack {
  // TODO: Your data structures go here

  final static int INITIAL_HEIGHT = 4;

  int height;
  int n;

  int[] maxRows;
  long[] sumRows;

  int listBegin;

  public UltraFast() {
    // TODO: Your code goes here
    height = INITIAL_HEIGHT;

    n = 0;
    maxRows = new int[(int) Math.pow(2, height)];
    sumRows = new long[(int) Math.pow(2, height)];
    listBegin = (int) Math.pow(2, height - 1) - 1; // bottom row, pos 0

  }

  /*
   * Call after changing maxRows[0][i] and sumRows[0][i]
   * Will update all nodes that need to be updated
   * Input n as position in stack
   */
  private void updateRows(int i) {
    int currentIndex = listBegin + i;

    while (currentIndex > 0) {

      // formula for index of parent node (in Alexa's notes)
      currentIndex = (currentIndex - 1) / 2;

      maxRows[currentIndex] = Math.max(maxRows[2 * currentIndex + 1], maxRows[2 * currentIndex + 2]);
      sumRows[currentIndex] = sumRows[2 * currentIndex + 1] + sumRows[2 * currentIndex + 2];
    }

  }

  // public void display() {
  // System.out.println("Max rows:");
  // for (int h = height - 1; h >= 0; h--) {
  // System.out.println();
  // for (int c = (int) Math.pow(2, h - 1) - 1; c < (int) Math.pow(2, h); c++) {
  // System.out.print(maxRows[c]);
  // }
  // }
  // System.out.println();
  // System.out.println("Sum rows:");
  // for (int h = height - 1; h >= 0; h--) {
  // System.out.println();
  // for (int c = (int) Math.pow(2, h - 1) - 1; c < (int) Math.pow(2, h); c++) {
  // System.out.print(sumRows[c]);
  // }
  // }
  // System.out.println();
  // }

  public void push(int x) {
    // TODO: Your code goes here
    if (listBegin + n + 2 >= maxRows.length) {
      expand();
    }

    maxRows[listBegin + n] = x;
    sumRows[listBegin + n] = x;
    n++;
    maxRows[listBegin + n] = Integer.MIN_VALUE;
    updateRows(n - 1);
  }

  public Integer pop() {
    if (n == 0)
      return null;
    n--;
    Integer x = maxRows[listBegin + n];
    maxRows[listBegin + n] = Integer.MIN_VALUE;
    sumRows[listBegin + n] = 0;
    updateRows(n);
    return x;

  }

  public Integer get(int i) {
    if (i < 0 || i >= n)
      return null;

    return maxRows[listBegin + i];
  }

  public Integer set(int i, int x) {
    if (i < 0 || i >= n)
      return null;

    Integer prev = maxRows[listBegin + i];
    maxRows[listBegin + i] = x;
    sumRows[listBegin + i] = x;

    updateRows(i);
    // updateRows(i+1);
    // updateRows(i-1);

    return prev;
  }

  public Integer max() {
    if (n == 0)
      return null;
    return maxRows[0];
  }

  public long ksum(int k) {
    if (k >= n)
      return sumRows[0];
    if (k <= 0)
      return 0;

    int currentIndex = 0;
    long sum = 0;

    int numZeros = (int) Math.pow(2, height - 1) - n;

    k += numZeros;

    int nodesOnRight = (int) Math.pow(2, height - 2);
    while (k > 0) {
      if (k >= nodesOnRight) {
        k -= nodesOnRight;
        sum += sumRows[currentIndex * 2 + 2]; // Add right
        currentIndex = currentIndex * 2 + 1; // Go to left
      } else {
        currentIndex = currentIndex * 2 + 2; // Go to right
      }

      nodesOnRight /= 2;
    }

    return sum;
  }

  public int size() {
    return n;
  }

  private void expand() {

    int newHeight = height + 1;
    int[] newMaxRows = new int[(int) Math.pow(2, newHeight)];
    long[] newSumRows = new long[(int) Math.pow(2, newHeight)];
    int newListBegin = (int) Math.pow(2, newHeight - 1) - 1;

    int size = 1;
    int pos = 0;
    for (int h = height - 1; h >= 0; h--) {
      System.arraycopy(maxRows, pos, newMaxRows, 2 * pos + 1, size);
      System.arraycopy(sumRows, pos, newSumRows, 2 * pos + 1, size);
      size *= 2;
      pos = 2 * pos + 1;
    }

    listBegin = newListBegin;
    height = newHeight;
    maxRows = newMaxRows;
    sumRows = newSumRows;

    maxRows[0] = Math.max(maxRows[1], maxRows[2]);
    sumRows[0] = sumRows[1] + sumRows[2];

  }

  public Iterator<Integer> iterator() {
    ArrayList<Integer> a = new ArrayList<>();
    for (int index = listBegin; index < listBegin + n; index++) {
      a.add(maxRows[index]);
    }
    return a.iterator();
  }

}
