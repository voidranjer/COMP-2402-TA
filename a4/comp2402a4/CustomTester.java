package comp2402a4;

public class CustomTester {

  public static void main(String[] args) {
    UltraFast stack = new UltraFast();

    // Push some values onto the stack
    int[] values = { 3, 5, 2 };
    for (int value : values) {
      stack.push(value);
    }

    for (int i = 0; i < 10; i++) {
      stack.pop();
    }

  }

}
