import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Infix to Postfix Conversion ===");
        System.out.println("Operator: + - * / ^");
        System.out.println("Brackets: ( ) [ ] { }");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("\nInput infix expression (q = quit): ");
                if (!scanner.hasNextLine()) {
                    break;
                }
                String expression = scanner.nextLine().trim();
                if (expression.equals("q") || expression.equals("Q")) {
                    break;
                }
                try {
                    String postfix = InfixToPostfix.convert(expression);
                    System.out.println();
                    System.out.println("Infix  : " + expression);
                    System.out.println("Postfix: " + postfix);
                } catch (IllegalArgumentException e) {
                    System.out.println("\nInvalid expression: "
                            + e.getMessage());
                }
            }
        }
    }
}
