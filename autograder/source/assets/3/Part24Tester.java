public class Part24Tester extends BaseTester{
  protected static boolean doTest(int n) {
    if(!nonRecursiveTest(n))
      return false;
    return correctnessTest(n);
  }

  static boolean nonRecursiveTest(int n){
    try{
      RefBinaryTree t = RefBinaryTree.randomMBST(1);
      t.addLongPath(Math.max(n, 500));
      t.bracketSequence();
    }
    catch (StackOverflowError e){
      println("Failed non-recursiveness test.");
      return false;
    }
    catch (Exception e){
      println("Failed non-recursiveness test ("+e.getClass().getSimpleName()+").");
      return false;
    }
    return true;
  }

  static boolean correctnessTest(int n){
    try {
      RefBinaryTree t = RefBinaryTree.randomMBST(n);
      String a0 = t.refBracketSequence();
      String a1 = t.bracketSequence();
      if (!a0.equals(a1)) {
        println("Failed correctness test.");
        if (n < 100) {
          println("correct != result ("+a0+"!="+a1+").");
        }
        if (n < 100) {
          println("This is the tree on which the code failed:");
          println(t.treeToString());
        }
        return false;
      }
    }
    catch (Exception e){
      println("Failed correctness test ("+e.getClass().getSimpleName()+").");
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