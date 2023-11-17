import comp2402a2.DuperDeque;
import java.util.Random;

public class DuperOpTester extends BaseTester{
  protected static boolean doTest(int n) {
    DataGenerator[] dgs = {new RandomDataGenerator(3*n), new IncreasingDataGenerator(3*n), new DecreasingDataGenerator(3*n)};

    for (DataGenerator dg : dgs) {
      boolean result = doTest(dg, n);
      if (!result) {
        println("Test failed on " + dg.getClass().getSimpleName());
        return false;
      }
    }
    return true;
  }

  protected static boolean doTest(DataGenerator dg, int n) {
    Random rand = new Random(0);

    try {
      DuperDeque dd0 = new RefDuperFast();
      DuperDeque dd1 = new comp2402a2.DuperFast();
      // Do a bunch of insertions
      for (int i = 0; i < n; i++) {
        int x = dg.nextData();
        if (rand.nextBoolean()) {
          dd0.addFirst(x);
          dd1.addFirst(x);
        } else {
          dd0.addLast(x);
          dd1.addLast(x);
        }
        if (dd0.size() != dd1.size()) {
          println("ERROR: Incorrect value of size()");
          return false;
        }
        if (!compareIntegers(dd0.max(), dd1.max())) {
          println("ERROR: Incorrect value of max()");
          return false;
        }
        int k = rand.nextInt(dd0.size()+1);
        if(rand.nextBoolean()){
          if (dd0.ksumFirst(k) != dd1.ksumFirst(k)) {
            println("ERROR: Incorrect value of ksumFirst()");
            return false;
          }
        }
        else {
          if (dd0.ksumLast(k) != dd1.ksumLast(k)) {
            println("ERROR: Incorrect value of ksumLast()");
            return false;
          }
        }
      }

      // Do a bunch of random operations
      for (int i = 0; i < 10*n; i++) {
        int x = dg.nextData();
        switch (rand.nextInt(4)) {
          case 0:
            dd0.addFirst(x);
            dd1.addFirst(x);
            break;
          case 1:
            dd0.addLast(x);
            dd1.addLast(x);
            break;
          case 2:
            if (!compareIntegers(dd0.removeFirst(), dd1.removeFirst())) {
              println("ERROR: Incorrect value of removeFirst()");
              return false;
            }
            break;
          case 3:
            if (!compareIntegers(dd0.removeLast(), dd1.removeLast())) {
              println("ERROR: Incorrect value of removeLast()");
              return false;
            }
            break;
        }
        if (dd0.size() != dd1.size()) {
          println("ERROR: Incorrect value of size()");
          return false;
        }
        if (!compareIntegers(dd0.max(), dd1.max())) {
          println("ERROR: Incorrect value of max()");
          return false;
        }
        int k = rand.nextInt(dd0.size()+1);
        if(rand.nextBoolean()){
          if (dd0.ksumFirst(k) != dd1.ksumFirst(k)) {
            println("ERROR: Incorrect value of ksumFirst()");
            return false;
          }
        }
        else {
          if (dd0.ksumLast(k) != dd1.ksumLast(k)) {
            println("ERROR: Incorrect value of ksumLast()");
            return false;
          }
        }
      }

      // Do a bunch of deletions
      while (dd0.size() > 0) {
        if (rand.nextBoolean()) {
          if (!compareIntegers(dd0.removeFirst(), dd1.removeFirst())) {
            println("ERROR: Incorrect value of removeFirst()");
            return false;
          }
        } else {
          if (!compareIntegers(dd0.removeLast(), dd1.removeLast())) {
            println("ERROR: Incorrect value of removeLast()");
            return false;
          }
        }
        if (dd0.size() != dd1.size()) {
          println("ERROR: Incorrect value of size()");
          return false;
        }
        if (!compareIntegers(dd0.max(), dd1.max())) {
          println("ERROR: Incorrect value of max()");
          return false;
        }
        int k = rand.nextInt(dd0.size()+1);
        if(rand.nextBoolean()){
          if (dd0.ksumFirst(k) != dd1.ksumFirst(k)) {
            println("ERROR: Incorrect value of ksumFirst()");
            return false;
          }
        }
        else {
          if (dd0.ksumLast(k) != dd1.ksumLast(k)) {
            println("ERROR: Incorrect value of ksumLast()");
            return false;
          }
        }
      }
    }
    catch (Exception | Error e){
      String excpname = e.getClass().getSimpleName();
      StackTraceElement[] traces = e.getStackTrace();
      for(StackTraceElement trace: traces){
        String classname = trace.getClassName();
        String methodname = trace.getMethodName();
        if(classname.startsWith("comp2402")){
          println(classname+"."+methodname+"() resulted in "+excpname+".");
          return false;
        }
      }
      println("Exception on server side, please report this.");
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    int n = 10;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    }

    boolean result = doTest(n);
    if (!result) {
      System.exit(-1);
    }
    printspcl();
    System.exit(0);
  }
}
