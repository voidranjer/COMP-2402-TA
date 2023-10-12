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
    if (front.isEmpty())
      return back.ksumFirst(k);

    if (k <= front.size())
      return front.ksumLast(k);

    return front.ksumLast(k) + back.ksumFirst(k - front.size());
  }

  public long ksumLast(int k) {
    if (back.isEmpty())
      return front.ksumFirst(k);

    if (k <= back.size())
      return back.ksumLast(k);

    return back.ksumLast(k) + front.ksumFirst(k - back.size());
  }

  public int size() {
    return front.size() + back.size();
  }

  public Iterator<Integer> iterator() {
    return new Iterator<>() {
      Iterator<Integer> frontiterator = front.reverseIterator();
      Iterator<Integer> backiterator = back.iterator();

      public boolean hasNext() {
        return frontiterator.hasNext() || backiterator.hasNext();
      }

      public Integer next() {
        return frontiterator.hasNext() ? frontiterator.next() : backiterator.next();
      }
    };
  }

  public void balance() {
    if (front.size() == 0 && back.size() == 0)
      return;

    if (front.size() == 0) {
      int mid = back.size() / 2;
      for (int i = 0; i < mid; i++) {
        front.push(back.arraylist.get(i));
      }

      SuperFast newBack = new SuperFast();

      for (int i = back.arraylist.size(); i >= mid; i--) {
        newBack.push(back.arraylist.get(i));
      }

      back = newBack;
    }

    if (back.size() == 0) {
      for (int i = 0; i < front.size() / 2; i++) {
        back.push(front.pop());
      }
    }
  }
}