package comp2402a2;

public class CustomTester {
  public static void println(Object line) {
    System.out.println(line);
  }

  public static void main(String[] args) {
    SuperFast sf = new SuperFast();

    sf.push(1);
    sf.push(4);

    // [1, 4]

    sf.pop(); // max = 4

    // [1]

    sf.push(3);

    // [1, 3]

    System.out.println(sf.max());

    // System.out.println(sf.maxes);
    // System.out.println(sf.max());

    // sf.pop();
    // sf.pop();
    // sf.pop();
    // sf.pop();
    // sf.pop();

    // System.out.println(sf.max());

    // System.out.print("[ ");
    // for (Integer i : df) {
    // System.out.print(i + " ");
    // }
    // println("]");
  }
}
