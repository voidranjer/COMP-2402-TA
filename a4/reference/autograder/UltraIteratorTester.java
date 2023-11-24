import comp2402a4.UltraStack;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class UltraIteratorTester extends BaseTester{
    public static boolean doTest(int n) {
        Random rand = new Random();

        try {
            List<Integer> data_list = new ArrayList<>();
            UltraStack us = new comp2402a4.UltraFast();

            for(int i=0; i<n; i++){
                int x = rand.nextInt(1000);
                data_list.add(x);
                us.push(x);
            }

            Iterator<Integer> it0 = data_list.iterator(), it1 = us.iterator();
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
        catch (Exception e){
            StackTraceElement[] traces = e.getStackTrace();
            println(traces[0].getMethodName()+"() threw "+e.getClass().getSimpleName()+" exception.");
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