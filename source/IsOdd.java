import java.util.Scanner;

public class IsOdd {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digit an integer");
        int num = scanner.nextInt();

        scanner.close();

        if (num % 2 != 0) {
            System.out.println(num + "is odd.");

        }
        else {
             System.out.println(num + "is even.");
        }

    }
    
}
