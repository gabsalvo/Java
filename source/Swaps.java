public class Swaps {
public static void main(String[] args) {
    int a = 5;
    int b = 10;

    System.out.println("Before swapping: a = " + a + ", b = " + b);

    // Swapping using a temporary variable
    int temp = a;
    a = b;
    b = temp;

    System.out.println("After swapping: a = " + a + ", b = " + b);
 }
}
