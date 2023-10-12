package comp2402a2;

public class CustomTester {
  public static void println(Object line) {
    System.out.println(line);
  }

  public static void main(String[] args) {
    DuperFast df = new DuperFast();

    df.addFirst(2);
    df.addFirst(1);
    df.removeLast();
    // df.addLast(3);
    // df.addLast(4);

    System.out.printf("Front (reversed): %s\n", df.front.arraylist);
    System.out.printf("Back: %s\n", df.back.arraylist);

    println(df.ksumLast(0));
    println(df.ksumLast(1));
    println(df.ksumLast(2));
    println(df.ksumLast(3));

    // System.out.print("[ ");
    // for (Integer i : df) {
    // System.out.print(i + " ");
    // }
    // println("]");
  }
}
