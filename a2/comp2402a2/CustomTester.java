package comp2402a2;

public class CustomTester {
  public static void println(Object line) {
    System.out.println(line);
  }

  public static void main(String[] args) {
    DuperFast df = new DuperFast();

    df.addFirst(1);
    df.addFirst(2);
    df.addLast(10);
    df.addLast(20);

    println("Size:" + df.size());
    println("Removing: " + df.removeLast());
    println("Size:" + df.size());
    println("Removing: " + df.removeLast());
    println("Size:" + df.size());
    println("Removing: " + df.removeLast());
    println("Size:" + df.size());
    println("Removing: " + df.removeLast());
    println("Size:" + df.size());
  }
}
