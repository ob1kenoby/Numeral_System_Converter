import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int base10 = scanner.nextInt();
            System.out.println(base10 % 8);
        }
    }
}
