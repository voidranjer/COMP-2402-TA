import comp2402a3.IndexedSSet;
import java.util.Random;

public class Part1Tester extends BaseTester {
  protected static boolean doTest(int n) {
    DataGenerator[] dgs = {new RandomDataGenerator(4*n), new IncreasingDataGenerator(4*n), new DecreasingDataGenerator(4*n)};

    for (DataGenerator dg : dgs) {
      boolean result = doTest(dg, n);
      if (!result) {
        println("Test failed on " + dg.getClass().getSimpleName());
        return false;
      }
    }
    return true;
  }

  public static boolean doTest(DataGenerator dg, int n) {
    Random rand = new Random();

    try {
      IndexedSSet<Integer> iss0 = new RefSkippityFast<>();
      IndexedSSet<Integer> iss1 = new comp2402a3.SkippityFast<>();
      for (int k = 0; k < 4*n; k++) {
        int op = rand.nextInt(6);
        if (op < 2) {
          int x = dg.nextData();
          iss0.add(x);
          iss1.add(x);
        } else if (op == 2 && iss0.size() > 0) {
          int i = rand.nextInt(iss0.size());
          int x = iss0.get(i);
          iss0.remove(x);
          iss1.remove(x);
        } else if (op == 3 && iss0.size() > 0) {
          int i = rand.nextInt(iss0.size());
          if (!compareIntegers(iss0.get(i), iss1.get(i))) {
            println("ERROR: Incorrect value of get(i)");
            return false;
          }
        } else if (op == 4 && iss0.size() > 0) {
          int x = rand.nextInt(4*n);
          int y = rand.nextInt(4*n);
          if (iss0.rangecount(x, y) != iss1.rangecount(x, y)) {
            println("ERROR: Incorrect value of rangecount(x, y)");
            return false;
          }
        } else if (op == 5 && iss0.size() > 1) {
          int i = rand.nextInt(iss0.size()-1);
          int x = iss0.get(i);
          x += rand.nextInt(2);
          if (!compareIntegers(iss0.find(x), iss1.find(x))) {
            println("ERROR: Incorrect value of find(x)");
            return false;
          }
        }
        if (iss0.size() != iss1.size()) {
          println("ERROR: Incorrect value of size()");
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
    int n = 20;
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
