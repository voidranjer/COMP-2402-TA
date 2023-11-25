import comp2402a4.UltraStack;
import java.util.Random;

public class UltraOpTester extends BaseTester{
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
            UltraStack us0 = new RefUltraFast();
            UltraStack us1 = new comp2402a4.UltraFast();
            for (int k = 0; k < 4*n; k++) {
                int op = rand.nextInt(7);
                if (op < 2) {
                    int x = dg.nextData();
                    us0.push(x);
                    us1.push(x);
                } else if (op == 2 && us0.size() > 0) {
                    if (!compareIntegers(us0.pop(), us1.pop())) {
                        println("ERROR: Incorrect value of pop()");
                        return false;
                    }
                } else if (op == 3 && us0.size() > 0) {
                    int i = rand.nextInt(us0.size());
                    if (!compareIntegers(us0.get(i), us1.get(i))) {
                        println("ERROR: Incorrect value of get(i)");
                        return false;
                    }
                } else if (op == 4 && us0.size() > 0) {
                    int i = rand.nextInt(us0.size());
                    int x = dg.nextData();
                    if (!compareIntegers(us0.set(i, x), us1.set(i, x))) {
                        println("ERROR: Incorrect value of set(i, x)");
                        return false;
                    }
                } else if (op == 5) {
                    if (!compareIntegers(us0.max(), us1.max())) {
                        println("ERROR: Incorrect value of max()");
                        return false;
                    }
                }
                else {
                    int x = rand.nextInt(us0.size()+10);
                    if (us0.ksum(x) != us1.ksum(x)) {
                        println("ERROR: Incorrect value of ksum(x)");
                        return false;
                    }
                }
                if (us0.size() != us1.size()) {
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
