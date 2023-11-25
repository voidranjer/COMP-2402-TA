package comp2402a4;

import java.util.*;

public class CustomTester {
  static <T> void showContents(Iterable<Integer> ds) {
    // System.out.print("[");
    // Iterator<Integer> it = ds.iterator();
    // while (it.hasNext()) {
    // System.out.print(it.next());
    // if (it.hasNext()) {
    // System.out.print(",");
    // }
    // }
    // System.out.println("]");
  }
  // static void ultraTest(UltraStack rs, UltraStack fast, int n) {
  // System.out.println(rs.getClass().getName());
  //
  // Random rand = new Random();
  //
  // // Create a list to store the operations
  // List<Runnable> operations = new ArrayList<>();
  //
  // // Add push operations
  // for (int j = 0; j < n; j++) {
  // int x = rand.nextInt(3 * n / 2);
  // operations.add(() -> {
  // System.out.println("push(" + x + ")");
  // fast.push(x);
  // rs.push(x);
  // });
  // }
  //
  // // Add set operations
  // for (int j = 0; j < n / 2; j++) {
  // int i = rand.nextInt(rs.size());
  // int x = rand.nextInt(3 * n / 2);
  // operations.add(() -> {
  // System.out.println("set(" + i + ", " + x + ") = " + rs.set(i, x));
  // fast.set(i, x);
  // });
  // }
  //
  // // Add pop operations
  // while (rs.size() > 0) {
  // operations.add(() -> {
  // System.out.println("pop() = " + rs.pop());
  // fast.pop();
  // });
  // }
  //
  // // Shuffle the list of operations
  // Collections.shuffle(operations);
  //
  // // Iterate over the shuffled list to perform the operations
  // for (Runnable operation : operations) {
  // operation.run();
  //
  // // Show contents and compare outputs as needed
  // showContents(fast);
  // showContents(rs);
  //
  // // Randomly test get, max, ksum, etc. after each operation
  // // Adjust the code based on your testing requirements
  // }
  // }

  static void ultraTest(UltraStack rs, UltraStack fast, int n) {
    boolean passed = true;
    System.out.println(rs.getClass().getName());

    Random rand = new Random();
    for (int j = 0; j < n; j++) {
      int x = rand.nextInt(3 * n / 2);
      System.out.println("push(" + x + ")");
      fast.push(x);
      rs.push(x);
      showContents(fast);
      showContents(rs);
      int i = rand.nextInt(rs.size());
      System.out.println("get(" + i + ") = " + "SLOW: " + rs.get(i) + " FAST: " + fast.get(i));

      if (!rs.max().equals(fast.max())) {
        passed = false;
        System.out.println("------------------------------------");
      }
      System.out.println("max() = " + "SLOW: " + rs.max() + " FAST: " + fast.max());

      int k = rand.nextInt(rs.size() + 1);

      if (rs.ksum(k) != (fast.ksum(k))) {
        passed = false;
        System.out.println("------------------------------------");
      }
      System.out.println("ksum(" + k + ") = " + "SLOW: " + rs.ksum(k) + " FAST: " + fast.ksum(k));
    }

    for (int j = 0; j < n / 2; j++) {
      int i = rand.nextInt(rs.size());
      int x = rand.nextInt(3 * n / 2);
      System.out.println("set(" + i + ", " + x + ") = " + rs.set(i, x));
      fast.set(i, x);
      showContents(rs);
      i = rand.nextInt(rs.size());
      System.out.println("get(" + i + ") = " + "SLOW: " + rs.get(i) + " FAST: " + fast.get(i));

      if (!rs.max().equals(fast.max())) {
        passed = false;
        System.out.println("----------------------------");
      }
      System.out.println("max() = " + "SLOW: " + rs.max() + " FAST: " + fast.max());

      int k = rand.nextInt(rs.size() + 1);

      if (rs.ksum(k) != (fast.ksum(k))) {
        passed = false;
        System.out.println("------------------------------------");
      }
      System.out.println("ksum(" + k + ") = " + "SLOW: " + rs.ksum(k) + " FAST: " + fast.ksum(k));
    }

    while (rs.size() > 0) {
      System.out.println("pop() = " + rs.pop());
      fast.pop();
      showContents(rs);
      showContents(fast);
      if (rs.size() > 0) {
        int i = rand.nextInt(rs.size());
        System.out.println("get(" + i + ") = " + "SLOW: " + rs.get(i) + " FAST: " + fast.get(i));

        if (!rs.max().equals(fast.max())) {
          passed = false;
          System.out.println("----------------------------");
        }
        System.out.println("max() = " + "SLOW: " + rs.max() + " FAST: " + fast.max());

        int k = rand.nextInt(rs.size() + 1);

        if (rs.ksum(k) != fast.ksum(k)) {
          passed = false;
          System.out.println("------------------------------------");
        }
        System.out.println("ksum(" + k + ") = " + "SLOW: " + rs.ksum(k) + " FAST: " + fast.ksum(k));
      }
    }

    System.out.println(passed);
  }

  public static void main(String[] args) {
    // ultraTest(new UltraSlow(), new UltraFast(), 10000);

    UltraFast test = new UltraFast();

    test.push(0);
    // test.pop();

    // test.push(28);
    // test.push(29);

    // test.pop();

    // test.push(47);

    test.set(0, 75);

    test.max();

    // test.push(28);
    // test.push(29);
    // test.push(3);
    // test.push(4);

    // test.pop();
    // test.pop();
    // test.pop();
    // test.pop();
    // test.pop();
    // test.pop();
    // test.pop();
    // test.pop();

    // test.push(1);
    // test.push(2);
    // test.push(3);

    // System.out.println(test.ksum(4));

    // test.push(15);
    // test.push(1);
    // test.push(3);
    // test.push(2);
    // test.push(7);
    // test.push(5);
    // test.push(11);
    // test.push(8);
    // test.push(4);
    // test.push(9);
    // test.push(17);

    // System.out.println(test.ds);
    // System.out.println(test.ksum(0));
    // System.out.println(test.ksum(1));
    // System.out.println(test.ksum(2));

    // System.out.println(test.ksum(3));
    // System.out.println(test.ksum(4));
    // System.out.println(test.ksum(5));
    // System.out.println(test.ksum(6));
    // System.out.println(test.ksum(7));
    // System.out.println(test.ksum(8));
    // System.out.println(test.ksum(9));
    // System.out.println(test.ksum(10));

    // test.set(4,9);
    //
    // test.set(1,6);
    //
    // test.pop();

    // test.pop();
    // test.pop();
    // test.pop();
    // test.pop();

  }
}
