import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        byte x1 = scanner.nextByte();
        byte y1 = scanner.nextByte();
        byte x2 = scanner.nextByte();
        byte y2 = scanner.nextByte();

        if (x1 == x2 || y1 == y2 || Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}