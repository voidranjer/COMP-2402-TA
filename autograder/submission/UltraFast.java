package comp2402a4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UltraFast implements UltraStack {
  // TODO: Your data structures go here
  List<Integer> ds;

  public UltraFast() {
    // TODO: Your code goes here
    ds = new ArrayList<Integer>();
  }

  public void push(int x) {
    ds.add(x);
  }

  public Integer pop() {
    if(ds.size() == 0)
      return null;
    return ds.remove(ds.size()-1);
  }


  public Integer get(int i) {
    if(i < 0 || i >= ds.size())
      return null;
    return ds.get(i);
  }

  public Integer set(int i, int x) {
    if(i < 0 || i >= ds.size())
      return null;
    return ds.set(i, x);
  }

  public Integer max() {
    // TODO: Your code goes here
    return null;
  }

  public long ksum(int k) {
    // TODO: Your code goes here
    return 0;
  }

  public int size() {
    return ds.size();
  }

  public Iterator<Integer> iterator() {
    return ds.iterator();
  }
}
