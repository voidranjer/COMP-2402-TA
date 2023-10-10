// package comp2402a2;

// import java.util.*;

// public class SuperFastReference implements SuperStack {

// public List<Integer> num;
// public List<Integer> max;
// public List<Long> sum;

// public SuperFastReference() {
// num = new ArrayList<>();
// sum = new ArrayList<>();
// max = new ArrayList<>();
// }

// public void push(Integer x) {

// /*
// * if (isEmpty()) sum.push(Long.valueOf(x));
// * else sum.push(sum.peek() + x);
// *
// * if (isEmpty() || x > max.peek()) max.push(x);
// * else max.push(max.peek());
// */

// if (isEmpty()) {
// sum.add(Long.valueOf(x));
// max.add(x);
// } else {
// sum.add(sum.get(size() - 1) + x);
// if (x > max.get(size() - 1))
// max.add(x);
// else
// max.add(max.get(size() - 1));
// }

// num.add(x);
// }

// public Integer pop() {
// if (isEmpty())
// return null;
// sum.remove(size() - 1);
// max.remove(size() - 1);
// return num.remove(size() - 1);
// }

// public Integer max() {
// if (isEmpty())
// return null;
// return max.get(size() - 1);
// }

// public long ksum(int k) {
// if (k == 0)
// return 0;
// if (k > size() - 1)
// return sum.get(size() - 1);
// return sum.get(size() - 1) - sum.get(size() - k - 1);
// }

// public boolean isEmpty() {
// return num.isEmpty();
// }

// public int size() {
// return num.size();
// }

// public Iterator<Integer> iterator() {
// return num.iterator();
// }

// public Iterator<Integer> descendingIterator() {
// return new Iterator<Integer>() {
// ListIterator<Integer> iterator = num.listIterator(size());

// public boolean hasNext() {
// return iterator.hasPrevious();
// }

// public Integer next() {
// return iterator.previous();
// }
// };
// }
// }
