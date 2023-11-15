package comp2402a4;

public class CustomTester {

  public static void main(String[] args) {
    UltraFast stack = new UltraFast();

    // Push some values onto the stack
    int[] values = { 3, 5, 2, 7, 1, 4, 6 };
    for (int value : values) {
      stack.push(value);
    }

    // Test the visualizeKSum method
    testVisualizeKSum(stack, 3);
  }

  public static void testVisualizeKSum(UltraFast stack, int k) {
    System.out.println("Initial Stack:");
    stack.display();

    System.out.println("\nCalling visualizeKSum with k = " + k);
    UltraFast.visualizeKSum(stack, k);
  }
}
