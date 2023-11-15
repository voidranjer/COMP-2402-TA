package comp2402a4;

import java.util.Iterator;

public class UltraFast implements UltraStack {
  int[] maxValues;
  long[] sumValues;
  int n;
  int max_value_height;
  int sum_height;
  long sumTrack;
  int begin;
  int[] values;

  public UltraFast() {
    values = new int[1];
    sum_height = 1;
    sumTrack = 0L;
    sumValues = new long[(2 << sum_height) - 1];
    max_value_height = 4;
    begin = (int) Math.pow(2, max_value_height - 1) - 1;
    maxValues = new int[(int) Math.pow(2, max_value_height)];
  }

  private void updateMaxValue(int index) {
    int cur_idx = begin + index;
    while (cur_idx > 0) {
      cur_idx = (cur_idx - 1) / 2;
      maxValues[cur_idx] = Math.max(maxValues[2 * cur_idx + 1], maxValues[2 * cur_idx + 2]);
    }
  }

  private void updateSums(int i, int delta) {
    long sum = 0;
    int capacity = 1 << (sum_height - 1);
    int j = 0;
    while (capacity > 0) {
      sumValues[j] += delta;
      if ((i & capacity) == 0)
        j++;
      else
        j += 2 * capacity;
      capacity >>= 1;
    }
    sumValues[j] += delta;
  }

  public void push(int x) {
    if (n + 1 > values.length)
      resize();
    if (n == 1 << sum_height)
      expand();
    if (begin + n + 2 >= maxValues.length)
      grow();
    values[n] = x;
    maxValues[begin + n] = x;
    int i = n++;
    maxValues[begin + n] = 0;
    updateMaxValue(n - 1);
    updateSums(i, x);
    sumTrack += x;
  }

  public Integer pop() {
    if (n == 0) {
      return null;
    }
    int y = values[n - 1];
    int i = n - 1;
    updateSums(i, -y);
    n--;
    maxValues[begin + n] = 0;
    updateMaxValue(n);
    sumTrack -= y;
    return y;
  }

  public Integer get(int i) {
    if (i < 0 || i >= n)
      return null;
    return values[i];
  }

  public Integer set(int i, int x) {
    if (i < 0 || i >= n)
      return null;
    int y = values[i];
    int delta = x - y;
    updateSums(i, delta);
    maxValues[begin + i] = x;
    updateMaxValue(i);
    values[i] = x;
    sumTrack += x - y;
    return y;
  }

  public Integer max() {
    if (n == 0)
      return null;
    return maxValues[0];
  }

  public long ksum(int k) {
    if (k <= 0)
      return 0L;
    if (k >= n) {
      return sumTrack;
    }
    long sum = 0;
    int idx = n - k - 1;
    int c = 1 << (sum_height - 1);
    int j = 0;
    while (c > 0) {
      if ((idx & c) == 0) {
        j += 1; // go to left child
      } else {
        sum += sumValues[j + 1]; // skip over left child
        j += 2 * c; // go to right child
      }
      c >>= 1;
    }
    // return sum + sums[j];
    return sumTrack - sum - sumValues[j];
  }

  public int size() {
    return n;
  }

  public Iterator<Integer> iterator() {
    Iterator<Integer> iterator = new Iterator<Integer>() {
      private int i = 0;

      @Override
      public boolean hasNext() {
        return n > i;
      }

      @Override
      public Integer next() {
        return Integer.valueOf(values[i++]);
      }
    };
    return iterator;
  }

  private void resize() {
    int biggerArray[] = new int[values.length * 2];
    System.arraycopy(values, 0, biggerArray, 0, n);
    values = biggerArray;
  }

  private void grow() {
    int new_height = max_value_height + 1;
    int[] new_max_values = new int[(int) Math.pow(2, new_height)];
    int new_begin = (int) Math.pow(2, new_height - 1) - 1;

    int size = 1;
    int pos = 0;
    for (int h = max_value_height - 1; h >= 0; h--) {
      System.arraycopy(maxValues, pos, new_max_values, 2 * pos + 1, size);
      size *= 2;
      pos = 2 * pos + 1;
    }

    begin = new_begin;
    max_value_height = new_height;
    maxValues = new_max_values;

    maxValues[0] = Math.max(maxValues[1], maxValues[2]);
  }

  private void expand() {
    sum_height += 1;
    long[] temp = new long[(2 << sum_height) - 1];
    System.arraycopy(sumValues, 0, temp, 1, sumValues.length);
    sumValues = temp;
    sumValues[0] = sumValues[1];
  }
}
