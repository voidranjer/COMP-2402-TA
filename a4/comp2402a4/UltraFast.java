package comp2402a4;

import java.util.Iterator;
import java.util.ArrayList;

public class UltraFast implements UltraStack {
  final static int INITIAL_HEIGHT = 3;

  ArrayList<Integer> sumHeap;
  ArrayList<Integer> maxHeap;
  int stackStart; // index of first element in stack (first element on the bottom level)
  int height;
  int size;

  // Get number of nodes present on the level 'h'
  private int getNumNodesOnLevel(int h) {
    return (int) Math.pow(2, h - 1);
  }

  // Get actual total number of nodes given the height 'h' of the heap
  private int getNumNodesOfHeap(int h) {
    return getNumNodesOnLevel(h + 1) - 1;
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

  // Clone from source UltraFast to this UltraFast
  private void clone(UltraFast source) {
    this.sumHeap = new ArrayList<Integer>(source.sumHeap);
    this.maxHeap = new ArrayList<Integer>(source.maxHeap);
    this.stackStart = source.stackStart;
    this.height = source.height;
    this.size = source.size;
  }

  // Update parent's value in maxHeap given any of the 2 children's index
  private void updateParentMax(int childIndex) {
    // should be left/right side agnostic, works with either child index

    int parentIndex = getParentIndex(childIndex);
    int leftChildValue = maxHeap.get(getLeftChildIndex(parentIndex));
    int rightChildValue = maxHeap.get(getRightChildIndex(parentIndex));
    int maxChildValue = Math.max(leftChildValue, rightChildValue);
    maxHeap.set(parentIndex, maxChildValue);
  }

  public UltraFast(int height) {
    this.height = height;
    size = 0;

    sumHeap = new ArrayList<Integer>();
    maxHeap = new ArrayList<Integer>();
    for (int i = 0; i < getNumNodesOfHeap(height); i++) {
      sumHeap.add(0);
      maxHeap.add(0);
    }

    stackStart = sumHeap.size() - getNumNodesOnLevel(height);
  }

  public UltraFast() {
    this(INITIAL_HEIGHT);
  }

  public void push(int x) {
    if (size == getNumNodesOnLevel(height)) {
      addLevel();
    }

    int targetIndex = stackStart + size;

    /*
     * edge case: bottom-most level of maxHeap has to be manually updated. levels
     * above will be handled by `updateParentMax()` in the loop below
     */
    maxHeap.set(targetIndex, x);

    // traverse up the tree and update partial sums and maxes
    int currentIndex = targetIndex;
    while (currentIndex > 0) {
      int parentIndex = getParentIndex(currentIndex);

      // update sum
      sumHeap.set(currentIndex, sumHeap.get(currentIndex) + x);

      // update parent max
      updateParentMax(currentIndex);

      currentIndex = parentIndex;
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

    /*
     * edge case: bottom-most level of maxHeap has to be manually updated. levels
     * above will be handled by `updateParentMax()` in the loop below
     */
    maxHeap.set(targetIndex, 0);

    // traverse up the tree and update the sum and max
    int currentIndex = targetIndex;
    while (currentIndex > 0) {
      sumHeap.set(currentIndex, sumHeap.get(currentIndex) - targetValue);
      updateParentMax(currentIndex);
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

    /*
     * edge case: bottom-most level of maxHeap has to be manually updated. levels
     * above will be handled by `updateParentMax()` in the loop below
     */
    maxHeap.set(targetIndex, x);

    // traverse up the tree and update the sum and max
    int currentIndex = targetIndex;
    while (currentIndex > 0) {
      sumHeap.set(currentIndex, sumHeap.get(currentIndex) - targetValue + x);
      updateParentMax(currentIndex);
      currentIndex = getParentIndex(currentIndex);
    }

    // handle edge case of root node
    sumHeap.set(0, sumHeap.get(0) - targetValue + x);

    return targetValue;
  }

  public Integer max() {
    if (size == 0)
      return null;

    return maxHeap.get(0);
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

  private void addLevel() {
    UltraFast newStack = new UltraFast(height + 1);

    // copy over values from old stack
    for (int i = 0; i < size; i++) {
      newStack.push(get(i));
    }

    clone(newStack);
  }

  public int size() {
    return size;
  }

  public Iterator<Integer> iterator() {
    // TODO: Your code goes here
    return null;
  }
}
