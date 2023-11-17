package comp2402a4;

import java.util.Iterator;
import java.util.ArrayList;

public class UltraFast implements UltraStack {
  final static int INITIAL_SIZE = 7;
  final static int INITIAL_HEIGHT = 3;

  ArrayList<Integer> sumHeap;
  int stackStart; // index of first element in stack (first element on the bottom level)
  int height;
  int size;

  // Get number of nodes present on the level 'h'
  private int getNumNodes(int h) {
    return (int) Math.pow(2, h - 1);
  }

  // Get index of parent
  private int getParentIndex(int i) {
    return (i - 1) / 2; // implicit floor
  }

  // Get index of left child
  private int getLeftChildIndex(int i) {
    return 2 * i + 1;
  }

  // Get index of right child
  private int getRightChildIndex(int i) {
    return 2 * i + 2;
  }

  public UltraFast() {
    size = 0;
    height = INITIAL_HEIGHT;

    sumHeap = new ArrayList<Integer>();
    for (int i = 0; i < INITIAL_SIZE; i++) {
      sumHeap.add(0);
    }

    stackStart = sumHeap.size() - getNumNodes(height);
  }

  public void push(int x) {

    // traverse up the tree and update the sum
    int currentIndex = stackStart + size;
    while (currentIndex > 0) {
      sumHeap.set(currentIndex, sumHeap.get(currentIndex) + x);
      currentIndex = getParentIndex(currentIndex);
    }

    // handle edge case of root node
    sumHeap.set(0, sumHeap.get(0) + x);

    size++;
  }

  public Integer pop() {
    if (size == 0) {
      return null;
    }

    int targetIndex = stackStart + size - 1;
    int targetValue = sumHeap.get(targetIndex);

    // traverse up the tree and update the sum
    int currentIndex = targetIndex;
    while (currentIndex > 0) {
      sumHeap.set(currentIndex, sumHeap.get(currentIndex) - targetValue);
      currentIndex = getParentIndex(currentIndex);
    }

    // handle edge case of root node
    sumHeap.set(0, sumHeap.get(0) - targetValue);

    size--;

    return targetValue;
  }

  public Integer get(int i) {
    return sumHeap.get(stackStart + i);
  }

  public Integer set(int i, int x) {
    int targetIndex = stackStart + i;
    int targetValue = sumHeap.get(targetIndex);
    sumHeap.set(targetIndex, x);

    // traverse up the tree and update the sum
    int parentIndex = getParentIndex(targetIndex);
    while (parentIndex > 0) {
      sumHeap.set(parentIndex, sumHeap.get(parentIndex) - targetValue + x);
      parentIndex = getParentIndex(parentIndex);
    }

    // handle edge case of root node
    sumHeap.set(0, sumHeap.get(0) - targetValue + x);

    return targetValue;
  }

  public Integer max() {
    // TODO: Your code goes here
    return null;
  }

  // Sum of the last k elements in the stack
  public long ksum(int k) {
    if (size == 0) {
      return 0;
    }

    if (k >= size) {
      return sumHeap.get(0);
    }

    /*
     * Traverse to the top of the tree, add to sum each time we make a right turn to
     * get to parent
     */
    int currentIndex = stackStart + size - k;
    long sum = sumHeap.get(currentIndex);
    while (currentIndex > 0) {
      int parentIndex = getParentIndex(currentIndex);
      /*
       * If we made a right turn to get to parent (meaning that we're on the left
       * child right now), add the right child's value to the sum
       */
      if (getLeftChildIndex(parentIndex) == currentIndex) {
        sum += sumHeap.get(getRightChildIndex(parentIndex)); // or, just do `sum += sumHeap.get(currentIndex + 1)`
      }
      currentIndex = getParentIndex(currentIndex);
    }

    return sum;
  }

  public int size() {
    return size;
  }

  public Iterator<Integer> iterator() {
    // TODO: Your code goes here
    return null;
  }
}
