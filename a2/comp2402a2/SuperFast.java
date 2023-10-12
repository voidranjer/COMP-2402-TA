package comp2402a2;

import java.util.*;

public class SuperFast implements SuperStack {
  ArrayList<Integer> arraylist;
  ArrayList<Integer> maxlist;
  ArrayList<Long> sumlist;

  public SuperFast() {
    arraylist = new ArrayList<>();
    maxlist = new ArrayList<>();
    sumlist = new ArrayList<>();
  }

  public void push(Integer x) {
    if (isEmpty()) {
      maxlist.add(x);
      sumlist.add(Long.valueOf(x));
    } else {
      Integer currentMax = maxlist.get(maxlist.size() - 1);
      if (x >= currentMax) // This part works hand in hand with the logic in pop()
        maxlist.add(x);

      Long lastElementOfSum = sumlist.get(sumlist.size() - 1);
      sumlist.add(lastElementOfSum + x);
    }

    arraylist.add(x);

    /*
     * Example maxlist:
     * maxlist | arraylist
     * [1] | [1]
     * [1, 1000] | [1, 1000]
     * [1, 1000, 1000] | [1, 1000, 2]
     * [1, 1000, 1000, 1000] | [1, 1000, 2, 3]
     * [1, 1000, 1000] | [1, 1000, 2]
     * 
     * Logic: We maintain a maxlist that is the same size as the arraylist. Each
     * index of the maxlist represents the current max value of the arraylist up to
     * that index (stores a snapshot of what the current max element is in the state
     * given by the index of maxlist). This only works because we are implementing a
     * stack, and adding/removing elements are always done in Stack order.
     * For example, this wouldn't work if we were implementing an arraylist
     */
  }

  public Integer pop() {
    if (isEmpty())
      return null;

    Integer removed = arraylist.remove(arraylist.size() - 1);
    sumlist.remove(sumlist.size() - 1);
    if (removed == maxlist.get(maxlist.size() - 1))
      maxlist.remove(maxlist.size() - 1); // If the removed element is the max element, remove it from maxlist

    return removed;
  }

  public Integer max() {
    if (isEmpty())
      return null;
    return maxlist.get(maxlist.size() - 1);
  }

  public long ksum(int k) {
    /*
     * Input: [1000, 20, 1, 10]
     *
     * k=1 1000 10
     * k=2 1020 11
     * k=3 1030 31
     * k=4 1031 1031
     *
     * left sum of k largest numbers
     * right: sum of k newest/latest numbers added
     */

    if (k == 0)
      return 0;
    if (k >= sumlist.size()) // just return max sum
      return sumlist.get(sumlist.size() - 1);
    return sumlist.get(sumlist.size() - 1) - sumlist.get(sumlist.size() - k - 1);
  }

  public long ksumFirst(int k) {
    if (k == 0 || sumlist.size() == 0)
      return 0;
    if (k > sumlist.size()) // just return max sum
      return sumlist.get(sumlist.size() - 1);
    return sumlist.get(k - 1); // [0, 1, 2, 3]
  }

  public long ksumLast(int k) {
    return ksum(k);
  }

  public int size() {
    return arraylist.size();
  }

  public boolean isEmpty() {
    return size() <= 0;
  }

  public Iterator<Integer> iterator() {
    return arraylist.iterator();
  }

  public Iterator<Integer> reverseIterator() {
    return new Iterator<Integer>() {
      ListIterator<Integer> iterator = arraylist.listIterator(size());

      public boolean hasNext() {
        return iterator.hasPrevious();
      }

      public Integer next() {
        return iterator.previous();
      }
    };
  }
}