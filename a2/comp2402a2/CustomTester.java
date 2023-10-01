package comp2402a2;

public class CustomTester {
  public static void println(Object line) {
    System.out.println(line);
  }

  public static void main(String[] args) {
    SuperFast sf = new SuperFast();

    sf.push(1000);
    sf.push(20);
    sf.push(1);
    sf.push(10);

    // println(sf.num);

    println(sf.ksum(1));
    println(sf.ksum(2));
    println(sf.ksum(3));
    println(sf.ksum(4));
  }
}
