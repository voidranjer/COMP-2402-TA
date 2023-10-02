package comp2402a2;

import java.util.Iterator;

public class DuperFast implements DuperDeque {
  SuperFast front;
  SuperFast back;

  public DuperFast() {
    front = new SuperFast();
    back = new SuperFast();
  }

  public void addFirst(Integer x) {
    front.push(x);
  }

  public void addLast(Integer x) {
    back.push(x);
  }

  public Integer removeFirst() {
    if (front.isEmpty())
      front.cloneProperties(back.splitHalves());
    return front.pop();
  }

  public Integer removeLast() {
    if (back.isEmpty())
      back.cloneProperties(front.splitHalves());
    return back.pop();
  }

  public Integer max() {
    Integer maxFront = front.max();
    Integer maxBack = back.max();
    if (maxFront == null)
      return maxBack;
    if (maxBack == null)
      return maxFront;
    return (maxFront >= maxBack) ? maxFront : maxBack;
  }

  public long ksumFirst(int k) {
    if (k <= front.size())
      return front.ksum(k);
    if (front.isEmpty())
      return back.getFromPrefixSum(k - 1);

    return front.getFromPrefixSum(front.size() - 1) + back.getFromPrefixSum(k - front.size() - 1);
  }

  public long ksumLast(int k) {
    if (k <= back.size())
      return back.ksum(k);
    if (back.isEmpty())
      return front.getFromPrefixSum(k - 1);
    return back.getFromPrefixSum(back.size() - 1) + front.getFromPrefixSum(k - back.size() - 1);
  }

  public int size() {
    return front.size() + back.size();
  }

  public Iterator<Integer> iterator() {
    // TODO: Your code goes here
    return null;
  }
}