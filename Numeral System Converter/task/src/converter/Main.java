package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        byte radix = scanner.nextByte();
        scanner.nextLine();
        String[] input = scanner.nextLine().split("\\.");
        byte newRadix = scanner.nextByte();

        boolean isFractional = input.length == 2;
        String integer = input[0];

        String fractionalNewRadix = "";
        if (isFractional) {
            String fractional = input[1];
            fractionalNewRadix = convertFractionalPart(radix, fractional, newRadix);
        }

        String integerNewRadix = convertIntegerPart(radix, integer, newRadix);

        if (isFractional) {
            System.out.println(integerNewRadix + "." + fractionalNewRadix);
        } else {
            System.out.println(integerNewRadix);
        }
    }

    private static String convertIntegerPart(byte radix, String integer, byte newRadix) {
        if ("0".equals(integer)) {
            return integer;
        }
        int number;
        if (radix == 1) {
            number = integer.length();
        } else {
            number = Integer.parseInt(integer, radix);
        }
        if (newRadix == 1) {
            number = convertToRadixOne(number);
            return Integer.toString(number);
        } else {
            return Integer.toString(number, newRadix);
        }
    }

    private static String convertFractionalPart(byte radix, String fractional, byte newRadix) {
        double decimalValue = 0;
        if (radix == 10) {
            decimalValue = Double.parseDouble("0." + fractional);
        } else {
            String[] letters = fractional.split("");
            for (int i = 0; i < letters.length; i ++) {
                decimalValue += getIntFromSymbol(letters[i]) / Math.pow((radix), i + 1);
            }
        }

        if (newRadix == 10) {
            return convertToStringWithoutDot(decimalValue);
        } else {
            StringBuilder newBaseValue = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                double result = decimalValue * newRadix;
                int integerPart = (int) Math.floor(result);
                double remainder = result - integerPart;
                newBaseValue.append(getSymbolFromInt(integerPart));
                if (remainder == 0.0) {
                    break;
                }
                decimalValue = remainder;
            }
            return newBaseValue.toString();
        }
    }

    private static char getSymbolFromInt(int integerPart) {
        if (integerPart >= 0 && integerPart <= 9) {
            return (char) (integerPart + 48);
        } else {
            return (char) (integerPart + 87);
        }
    }

    private static String convertToStringWithoutDot(double decimalValue) {
        String output = Double.toString(decimalValue);
        return output.split("\\.")[1];
    }

    private static int getIntFromSymbol(String symbol) {
        char l = symbol.charAt(0);
        if ((int) l >= 48 && (int) l <= 57) { // '0' == 48, '9' == 57
            return (int) l - 48;
        } else if ((int) l >= 97 && (int) l <= 122) { // 'a' == 97, 'z' == 122
            return (int) l - 87;
        } else if ((int) l >= 65 && (int) l <= 90) { // 'A' == 65, 'Z' == 90
            return (int) l - 55;
        }
        return -1;
    }

    private static int convertToRadixOne(int number) {
        if (number == 1) {
            return 1;
        } else {
            return (int) (Math.pow(10, number - 1) + convertToRadixOne(number - 1));
        }
    }
}
