import comp2402a2.SuperStack;
import java.util.ArrayList;
import java.util.Iterator;

public class RefSuperFast implements SuperStack {
  protected ArrayList<Integer> data_list, max_list;
  protected ArrayList<Long> sum_list;

  public RefSuperFast() {
    // TODO: Your code goes here
    data_list = new ArrayList<>();
    max_list = new ArrayList<>();
    sum_list = new ArrayList<>();
  }

  public void push(Integer x) {
    // TODO: Your code goes here
    data_list.add(x);
    if(max_list.isEmpty() || x > max_list.get(max_list.size()-1))
      max_list.add(x);
    else
      max_list.add(max_list.get(max_list.size()-1));
    if(sum_list.isEmpty())
      sum_list.add((long)x);
    else
      sum_list.add(sum_list.get(sum_list.size()-1) + x);
  }

  public Integer pop() {
    // TODO: Your code goes here
    if(size() <= 0)
      return null;
    else{
      max_list.remove(max_list.size()-1);
      sum_list.remove(sum_list.size()-1);
      return data_list.remove(data_list.size()-1);
    }
  }

  public Integer max() {
    // TODO: Your code goes here
    if(size() <= 0)
      return null;
    else
      return max_list.get(max_list.size()-1);
  }

  public long ksum(int k) {
    // TODO: Your code goes here
    if(size() <= 0 || k <= 0)
      return 0;
    int n = sum_list.size();
    long upper_sum = sum_list.get(n-1);
    long lower_sum = 0;
    if(k < n)
      lower_sum = sum_list.get(n-1-k);
    return upper_sum-lower_sum;
  }

  public int size() {
    // TODO: Your code goes here
    return data_list.size();
  }

  public Iterator<Integer> iterator() {
    // TODO: Your code goes here
    return data_list.iterator();
  }
}