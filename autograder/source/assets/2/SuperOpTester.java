import comp2402a2.SuperStack;
import java.util.Random;

public class SuperOpTester extends BaseTester{
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
      SuperStack ss0 = new RefSuperFast();
      SuperStack ss1 = new comp2402a2.SuperFast();
      // Do a bunch of insertions
      for (int i = 0; i < n; i++) {
        int x = dg.nextData();
        ss0.push(x);
        ss1.push(x);
        if (ss0.size() != ss1.size()) {
          println("ERROR: Incorrect value of size()");
          return false;
        }
        if (!compareIntegers(ss0.max(), ss1.max())) {
          println("ERROR: Incorrect value of max()");
          return false;
        }
        int k = rand.nextInt(ss0.size()+1);
        if (ss0.ksum(k) != ss1.ksum(k)) {
          println("ERROR: Incorrect value of ksum()");
          return false;
        }
      }

      // Do a bunch of random operations
      for (int i = 0; i < 10*n; i++) {
        if (rand.nextBoolean()) {
          int x = dg.nextData();
          ss0.push(x);
          ss1.push(x);
        } else {
          if (!compareIntegers(ss0.pop(), ss1.pop())) {
            println("ERROR: Incorrect value of pop()");
            return false;
          }
          if (ss0.size() != ss1.size()) {
            println("ERROR: Incorrect value of size()");
            return false;
          }
          if (!compareIntegers(ss0.max(), ss1.max())) {
            println("ERROR: Incorrect value of max()");
            return false;
          }
          int k = rand.nextInt(ss0.size()+1);
          if (ss0.ksum(k) != ss1.ksum(k)) {
            println("ERROR: Incorrect value of ksum()");
            return false;
          }
        }
      }

      // Do a bunch of deletions
      while (ss0.size() > 0) {
        if (!compareIntegers(ss0.pop(), ss1.pop())) {
          println("ERROR: Incorrect value of pop()");
          return false;
        }
        if (ss0.size() != ss1.size()) {
          println("ERROR: Incorrect value of size()");
          return false;
        }
        if (!compareIntegers(ss0.max(), ss1.max())) {
          println("ERROR: Incorrect value of max()");
          return false;
        }
        int k = rand.nextInt(ss0.size()+1);
        if (ss0.ksum(k) != ss1.ksum(k)) {
          println("ERROR: Incorrect value of ksum()");
          return false;
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
