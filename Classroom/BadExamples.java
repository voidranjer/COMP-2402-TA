
import java.util.*;


public class BadExamples {
  static class Point {
    int x, y;
    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public boolean equals(Object o) {
      if (o == this) {
          return true;
      }
      if (!(o instanceof Point)) {
          return false;
      }
      Point c = (Point) o;
      return c.x == x && c.y == y;
    }

    public String toString() {
      return "(" + x + "," + y + ")";
    }

    //bad hashCode function! (but better than none)
    // public int hashCode() {
    //   return x ^ y;
    // }

    
    public int hashCode() {
     // random numbers from rand.org
     long[] z = {0x2058cc50L, 0xcb19137eL};
     long zz = 0xbea0107e5067d19dL;

     // convert (unsigned) hashcodes to long
     long h0 = x & ((1L<<32)-1);
     long h1 = y & ((1L<<32)-1);

     return (int)(((z[0]*h0 + z[1]*h1)*zz) >>> 32);
    }
  }

  static void pointExampleA() {
    System.out.print("Creating set of points...");
    System.out.flush();
    Set<Point> s = new HashSet<>();

    Random rand = new Random(0);
    int n = 10;
    for (long i = 0; i < n; i++) {
      int rx = rand.nextInt(3);
      int ry = rand.nextInt(3);
      Point p = new Point(rx, ry);
      s.add(p);
    }
    System.out.println("done");

    System.out.println(s);
  }

  static void pointExample(int n) {
    System.out.print("Creating set of n = " + n + " points...");
    System.out.flush();
    Set<Point> s = new HashSet<>();

    Random rand = new Random();
    for (long i = 0; i < n; i++) {
      int rx = rand.nextInt();
      int ry = rand.nextInt(); 
      Point p = new Point(rx, rx);
      // Point p = new Point(rx, ry);
      s.add(p);
    }
    System.out.println("done");
  }

  static void longExample(int n) {
    System.out.print("Creating set of longs...");
    System.out.flush();
    long y = 1L << 32L;  // y = 2^32 = 4294967296
    Set<Long> s = new HashSet<>();

    Random rand = new Random();
    for (long i = 0; i < n; i++) {
      long j = rand.nextInt();
      long x = j * y + j;  // x is j shifted by 32 bits and added to original j
      // Long xx = x;
      // System.out.println(xx.hashCode());
      s.add(x);
    }
    System.out.println("done");

    System.out.print("Searching set of longs...");
    System.out.flush();
    for (long i = 0; i < n; i++) {
      long j = rand.nextInt(n);
      long x = j * y + j;
      s.contains(x);
    }
    System.out.println("done");
  }

  static void mapExample(int n) {
    System.out.print("Creating a map");
    System.out.flush();
    Map<Integer,Integer> m = new HashMap<>();
    Random rand = new Random();
    for (long i = 0; i < n; i++) {
      int rx = rand.nextInt();
      m.put(rx, rx);
    }
    System.out.println("done");

    System.out.print("Adding map entries to set");
    System.out.flush();
    Set<Map.Entry<Integer,Integer>> s = new HashSet<>();
    for (Map.Entry<Integer,Integer> me : m.entrySet()) {
      s.add(me);
    }
    System.out.println("done");
  }


  public static void main(String[] args) {
    int n = 1000000;
    pointExampleA();
    pointExample(n);
    // mapExample(n);
    // longExample(n);
  }
}
