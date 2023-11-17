import comp2402a2.SuperStack;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class SuperIteratorTester extends BaseTester{
  protected static boolean doTest(int n) {
    Random rand = new Random();

    try {
      List<Integer> data_list = new ArrayList<>();
      SuperStack ss = new comp2402a2.SuperFast();

      for(int i=0; i<n; i++){
        int x = rand.nextInt(1000);
        data_list.add(x);
        ss.push(x);
      }

      Iterator<Integer> it0 = data_list.iterator(), it1 = ss.iterator();
      if(it1 == null){
        println("ERROR: iterator() returns null");
        return false;
      }
      for(int i=0; i<n; i++){
        if(it0.hasNext() != it1.hasNext()){
          println("ERROR: Incorrect value of hasNext()");
          return false;
        }
        if(!compareIntegers(it0.next(), it1.next())){
          println("ERROR: Incorrect value of next()");
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
