import java.util.Scanner;

public class CreditCardValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a credit card number: ");
        String cardNumber = scanner.nextLine().replaceAll("\\s", "");
        String issuer = getCardIssuer(cardNumber);
        if (isValidLuhn(cardNumber)) {
            System.out.println("The credit card number is VALID.");
        } else {
            System.out.println("The credit card number is INVALID.");
        }
        System.out.println("Card Issuer: " + issuer);
        scanner.close();
    }

    public static boolean isValidLuhn(String number) {
        int sum = 0;
        boolean alternate = false;
        for (int i = number.length() - 1; i >= 0; i--) {
            int n = number.charAt(i) - '0';
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    public static String getCardIssuer(String number) {
        if (number.matches("^4\\d{12}(?:\\d{3})?$")) {
            return "Visa";
        } else if (number.matches("^5[1-5]\\d{14}$")) {
            return "MasterCard";
        } else if (number.matches("^3[47]\\d{13}$")) {
            return "American Express";
        } else if (number.matches("^6(?:011|5\\d{2})\\d{12}$")) {
            return "Discover";
        } else if (number.matches("^3(?:0[0-5]|[68]\\d)\\d{11}$")) {
            return "Diners Club";
        } else if (number.matches("^35\\d{14}$")) {
            return "JCB";
        } else {
            return "Unknown";
        }
    }
}
