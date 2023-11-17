import java.util.Random;

public class BaseTester {
    protected interface DataGenerator {
        public int nextData();
    }

    protected static class RandomDataGenerator implements DataGenerator {
        Random rand;
        int n;
        public RandomDataGenerator(int n) {
            this.n = n;
            rand = new Random(0);
        }

        public int nextData() {
            return rand.nextInt(n);
        }
    }

    protected static class IncreasingDataGenerator implements DataGenerator {
        int i;
        int n;
        public IncreasingDataGenerator(int n) {
            this.n = n;
        }

        public int nextData() {
            return i++;
        }
    }

    protected static class DecreasingDataGenerator implements DataGenerator {
        int i;
        int n;
        public DecreasingDataGenerator(int n) {
            this.n = n;
            this.i = 100*n;
        }

        public int nextData() {
            return i--;
        }
    }

    protected static boolean compareIntegers(Integer x, Integer y){
        if(x == null && y == null)return true;
        if(x == null)return false;
        if(y == null)return false;
        return x.equals(y);
    }

    protected static void println(String msg) {
        for (String line : msg.split("\n")) {
            System.err.print("MAGICODE:");
            System.err.println(line);
        }
    }

    protected static void printspcl() {
        println("NoMonkeyBusiness");
    }
}
