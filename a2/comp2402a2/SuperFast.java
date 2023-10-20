
package comp2402a2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class SuperFast implements SuperStack {
  protected ArrayList<Long> sums;
  protected ArrayList<Integer> sf;
  protected ArrayList<Integer> maxes;
  protected Integer max;

  public SuperFast() {
    sf = new ArrayList<>();
    sums = new ArrayList<>();
    maxes = new ArrayList<>();
    max = null;
  }

  public void push(Integer x) {
    sums.add(Long.valueOf(x) + peekSum());

    sf.add(x);

    if (maxes.isEmpty() || max == null) {
      max = x;
    } else if (x > max) {
      max = x;
    }
    maxes.add(max);

  }

  public Integer pop() {
    if (size() <= 0) {
      return null;
    } else {
      sums.remove(sums.size() - 1);
      maxes.remove(maxes.size() - 1);
      return sf.remove(sf.size() - 1);
    }
  }

  public Integer max() {
    if (sf.isEmpty() || max == null) {
      return null;
    }
    return maxes.get(maxes.size() - 1);
  }

  // new arraylist where everything added is equal to the sum of everything before
  // it including itself
  public long ksum(int k) {
    int index = sf.size() - 1;
    if (sums.isEmpty() || k <= 0) {
      return 0;
    } else if (k > index) {
      return peekSum();
    }

    long sum = sums.get(index) - sums.get(index - k);
    return sum;
  }

  public int size() {
    return sf.size();
  }

  public long peekSum() {
    if (size() <= 0)
      return 0;
    else
      return sums.get(sums.size() - 1);
  }

  public Iterator<Integer> iterator() {
    return sf.iterator();
  }
}