import comp2402a2.DuperDeque;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class RefDuperFast implements DuperDeque {
  protected RefSuperFast front;
  protected RefSuperFast back;

  public RefDuperFast() {
    // TODO: Your code goes here
    front = new RefSuperFast();
    back = new RefSuperFast();
  }

  protected void rebalance() {
    // put all the contents into a list, in the correct order
    List<Integer> tmp = new ArrayList<>();
    while (front.size() > 0) {
      tmp.add(front.pop());
    }
    for (int i = 0; i < back.size(); i++) {
      tmp.add(null);
    }
    int m = back.size();
    for (int i = 0; i < m; i++) {
      tmp.set(tmp.size()-i-1, back.pop());
    }
    // spread the contents evenly among front and back
    int n = tmp.size();
    for (int i = n/2; i < n; i++) {
      back.push(tmp.get(i));
    }
    for (int i = n/2-1; i >= 0; i--) {
      front.push(tmp.get(i));
    }
  }

  public void addFirst(Integer x) {
    // TODO: Your code goes here
    front.push(x);
  }

  public void addLast(Integer x) {
    // TODO: Your code goes here
    back.push(x);
  }

  public Integer removeFirst() {
    // TODO: Your code goes here
    if (front.size() == 0) {
      if (back.size() == 1) {
        return back.pop();
      }
      rebalance();
    }
    return front.pop();
  }

  public Integer removeLast() {
    // TODO: Your code goes here
    if (back.size() == 0) {
      rebalance();
    }
    return back.pop();
  }

  public Integer max() {
    // TODO: Your code goes here
    Integer fm = front.max(), bm = back.max();
    if(fm == null && bm == null)
      return null;
    if(fm == null)
      return bm;
    if(bm == null)
      return fm;
    return Math.max(fm, bm);
  }

  public long ksumFirst(int k) {
    // TODO: Your code goes here
    long sum = front.ksum(k);
    if(k > front.size())
      sum += back.ksum(back.size()) - back.ksum(back.size()- k + front.size());
    return sum;
  }

  public long ksumLast(int k){
    // TODO: Your code goes here
    long sum = back.ksum(k);
    if(k > back.size())
      sum += front.ksum(front.size()) - front.ksum(front.size()- k + back.size());
    return sum;
  }

  public int size() {
    // TODO: Your code goes here
    return front.size() + back.size();
  }

  protected class WTFIterator implements Iterator<Integer> {
    ListIterator<Integer> fi, bi;

    public WTFIterator(RefDuperFast fmd) {
      fi = fmd.front.data_list.listIterator(fmd.front.data_list.size());
      bi = fmd.back.data_list.listIterator();
    }
    public Integer next() {
      if (fi.hasPrevious()) {
        return fi.previous();
      }
      return bi.next();
    }

    public boolean hasNext() {
      return fi.hasPrevious() || bi.hasNext();
    }
  }

  public Iterator<Integer> iterator() {
    // TODO: Your code goes here
    return new WTFIterator(this);
  }
}