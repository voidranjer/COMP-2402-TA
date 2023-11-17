package comp2402a4;

public class CustomTester {

  public static void main(String[] args) {
    UltraStack stack = new UltraFast();

    // Push some values onto the stack
    int[] values = { 3, 5, 9, 9, 7 };
    // int[] values = { 3, 5, 2, 7, 9, 1, 4, 6, 8, 10, 10 };
    for (int value : values) {
      stack.push(value);
    }

    stack.pop();
    System.out.println(stack.max());
    stack.pop();
    System.out.println(stack.max());
    stack.pop();
    System.out.println(stack.max());
  }

}
