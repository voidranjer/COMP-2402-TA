// package comp2402a2;

// import java.util.*;

// public class DuperFastReference implements DuperDeque {

// SuperFast front;
// SuperFast back;

// public DuperFastReference() {
// front = new SuperFast();
// back = new SuperFast();
// }

// public void addFirst(Integer x) {
// front.push(x);
// }

// public void addLast(Integer x) {
// back.push(x);
// }

// public Integer removeFirst() {
// if (front.isEmpty())
// refillFront();
// return front.pop();
// }

// public Integer removeLast() {
// if (back.isEmpty())
// refillBack();
// return back.pop();
// }

// public Integer max() {
// Integer frontMax = front.max();
// Integer backMax = back.max();
// if (frontMax == null)
// return backMax;
// if (backMax == null)
// return frontMax;
// return (frontMax >= backMax) ? frontMax : backMax;
// }

// public long ksumFirst(int k) {
// if (k <= front.size())
// return front.ksum(k);
// if (front.isEmpty())
// return back.sum.get(k - 1);
// return front.sum.get(front.size() - 1) + back.sum.get(k - front.size() - 1);
// }

// public long ksumLast(int k) {
// if (k <= back.size())
// return back.ksum(k);
// if (back.isEmpty())
// return front.sum.get(k - 1);
// return back.sum.get(back.size() - 1) + front.sum.get(k - back.size() - 1);
// }

// public int size() {
// return front.size() + back.size();
// }

// public Iterator<Integer> iterator() {
// return new Iterator<>() {
// Iterator<Integer> frontiterator = front.descendingIterator();
// Iterator<Integer> backiterator = back.iterator();

// public boolean hasNext() {
// if (frontiterator.hasNext())
// return true;
// else
// return backiterator.hasNext();
// }

// public Integer next() {
// if (frontiterator.hasNext())
// return frontiterator.next();
// return backiterator.next();
// }
// };
// }

// // Original ideas are from Morin's book but they don't resemble his code
// // anymore...
// public void refillFront() {
// int backSize = (back.size()) / 2;
// int frontSize = back.size() - backSize;
// SuperFast newFront = new SuperFast();
// SuperFast newBack = new SuperFast();
// List<Integer> temp = back.num.subList(0, frontSize);
// // Collections.reverse(temp);
// // for (Integer x : temp) newFront.push(x);
// for (int i = frontSize - 1; i >= 0; i--)
// newFront.push(temp.get(i));
// for (int i = 0; i < backSize; i++)
// newBack.push(back.num.get(frontSize + i));
// front = newFront;
// back = newBack;
// }

// public void refillBack() {
// int frontSize = (front.size()) / 2;
// int backSize = front.size() - frontSize;
// SuperFast newFront = new SuperFast();
// SuperFast newBack = new SuperFast();
// List<Integer> temp = front.num.subList(0, backSize);
// // Collections.reverse(temp);
// // for (Integer x : temp) newBack.push(x);
// for (int i = backSize - 1; i >= 0; i--)
// newBack.push(temp.get(i));
// for (int i = 0; i < frontSize; i++)
// newFront.push(front.num.get(backSize + i));
// front = newFront;
// back = newBack;
// }
// }