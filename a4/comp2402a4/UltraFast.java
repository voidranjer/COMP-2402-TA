package comp2402a4;

import java.util.Iterator;

public class UltraFast implements UltraStack {
  final static int INITIAL_HEIGHT = 3;

  long[] sumHeap;
  int[] maxHeap;
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

  // Update parent's value in maxHeap given any of the 2 children's index
  private void updateParentMax(int childIndex) {
    // should be left/right side agnostic, works with either child index

    int parentIndex = getParentIndex(childIndex);
    int leftChildValue = maxHeap[getLeftChildIndex(parentIndex)];
    int rightChildValue = maxHeap[getRightChildIndex(parentIndex)];
    int maxChildValue = Math.max(leftChildValue, rightChildValue);
    maxHeap[parentIndex] = maxChildValue;
  }

  // Completely reset the stack, given a starting height
  private void reset(int height) {
    this.height = height;
    size = 0;

    sumHeap = new long[getNumNodesOfHeap(height)];
    maxHeap = new int[getNumNodesOfHeap(height)];

    stackStart = sumHeap.length - getNumNodesOnLevel(height);
  }

  public UltraFast() {
    reset(INITIAL_HEIGHT);
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
    maxHeap[targetIndex] = x;

    // traverse up the tree and update partial sums and maxes
    int currentIndex = targetIndex;
    while (currentIndex > 0) {
      int parentIndex = getParentIndex(currentIndex);

      // update sum
      sumHeap[currentIndex] = sumHeap[currentIndex] + x;

      // update parent max
      updateParentMax(currentIndex);

      currentIndex = parentIndex;
    }

    // handle edge case of root node
    sumHeap[0] = sumHeap[0] + x;

    size++;
  }

  public Integer pop() {
    if (size == 0) {
      return null;
    }

    int targetIndex = stackStart + size - 1;
    int targetValue = maxHeap[targetIndex];

    /*
     * edge case: bottom-most level of maxHeap has to be manually updated. levels
     * above will be handled by `updateParentMax()` in the loop below
     */
    maxHeap[targetIndex] = 0;

    // traverse up the tree and update the sum and max
    int currentIndex = targetIndex;
    while (currentIndex > 0) {
      sumHeap[currentIndex] = sumHeap[currentIndex] - targetValue;
      updateParentMax(currentIndex);
      currentIndex = getParentIndex(currentIndex);
    }

    // handle edge case of root node
    sumHeap[0] = sumHeap[0] - targetValue;

    size--;

    return targetValue;
  }

  public Integer get(int i) {
    return maxHeap[stackStart + i];
  }

  public Integer set(int i, int x) {
    int targetIndex = stackStart + i;
    int targetValue = maxHeap[targetIndex];

    /*
     * edge case: bottom-most level of maxHeap has to be manually updated. levels
     * above will be handled by `updateParentMax()` in the loop below
     */
    maxHeap[targetIndex] = x;

    // traverse up the tree and update the sum and max
    int currentIndex = targetIndex;
    while (currentIndex > 0) {
      sumHeap[currentIndex] = sumHeap[currentIndex] - targetValue + x;
      updateParentMax(currentIndex);
      currentIndex = getParentIndex(currentIndex);
    }

    // handle edge case of root node
    sumHeap[0] = sumHeap[0] - targetValue + x;

    return targetValue;
  }

  public Integer max() {
    if (size == 0)
      return null;

    return maxHeap[0];
  }

  // Sum of the last k elements in the stack
  public long ksum(int k) {
    if (size == 0 || k <= 0) {
      return 0;
    }

    if (k >= size) {
      return sumHeap[0];
    }

    /*
     * Traverse to the top of the tree, add to sum each time we make a right turn to
     * get to parent
     */
    int currentIndex = stackStart + size - k;
    long sum = sumHeap[currentIndex];
    while (currentIndex > 0) {
      int parentIndex = getParentIndex(currentIndex);
      /*
       * If we made a right turn to get to parent (meaning that we're on the left
       * child right now), add the right child's value to the sum
       */
      if (getLeftChildIndex(parentIndex) == currentIndex)
        sum += sumHeap[currentIndex + 1];

      currentIndex = getParentIndex(currentIndex);
    }

    return sum;
  }

  private void addLevel() {
    int newTotalNodes = getNumNodesOfHeap(height + 1);

    // ArrayList<Long> newSumHeap = new ArrayList<Long>();
    // ArrayList<Integer> newMaxHeap = new ArrayList<Integer>();
    long[] newSumHeap = new long[newTotalNodes];
    int[] newMaxHeap = new int[newTotalNodes];

    // take new root node from old root node (root stays the same)
    newSumHeap[0] = sumHeap[0];
    newMaxHeap[0] = maxHeap[0];

    // use existing heap as left child, and merge with a right child that is empty
    int nodesRead = 0;
    for (int i = 1; i < height + 1; i++) {
      int halfNumNodesOnLevel = getNumNodesOnLevel(i);
      int levelStartIndex = getNumNodesOfHeap(i + 1) - getNumNodesOnLevel(i + 1);

      for (int j = 0; j < halfNumNodesOnLevel; j++) {
        // left child of root node
        newSumHeap[levelStartIndex + j] = sumHeap[nodesRead];
        newMaxHeap[levelStartIndex + j] = maxHeap[nodesRead];
        nodesRead++;

        // right child of root node (no need to actually set 0s because default Array
        // values are zeros)
        // newSumHeap[(levelStartIndex + j) + halfNumNodesOnLevel] = 0L;
        // newMaxHeap[(levelStartIndex + j) + halfNumNodesOnLevel] = 0;

      }
    }

    sumHeap = newSumHeap;
    maxHeap = newMaxHeap;
    height++;
    stackStart = sumHeap.length - getNumNodesOnLevel(height);
  }

  public int size() {
    return size;
  }

  public Iterator<Integer> iterator() {
    return new Iterator<Integer>() {
      int i = 0;

      public Integer next() {
        return get(i++);
      }

      public boolean hasNext() {
        return i < size;
      }
    };
  }
}
