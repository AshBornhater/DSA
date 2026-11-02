import java.util.ArrayList;
import java.util.List;

public class InfixToPostfix {

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private static boolean isOpening(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private static boolean isClosing(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    private static boolean isOperand(String token) {
        char first = token.charAt(0);
        return Character.isLetterOrDigit(first) || first == '.';
    }

    private static boolean bracketsMatch(char open, char close) {
        return (open == '(' && close == ')')
                || (open == '[' && close == ']')
                || (open == '{' && close == '}');
    }

    private static int precedence(char op) {
        return switch (op) {
            case '^' -> 3;
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> 0;
        };
    }

    private static boolean shouldPop(char newOp, char topOp) {
        if (isOpening(topOp)) {
            return false;
        }
        if (newOp == '^') {
            return precedence(topOp) > precedence(newOp);
        }
        return precedence(topOp) >= precedence(newOp);
    }

    private static List<String> tokenize(String infix) {
        List<String> tokens = new ArrayList<>();
        StringBuilder operand = new StringBuilder();
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            if (Character.isLetterOrDigit(c) || c == '.') {
                operand.append(c);
                continue;
            }
            if (operand.length() > 0) {
                tokens.add(operand.toString());
                operand.setLength(0);
            }
            if (Character.isWhitespace(c)) {
                continue;
            }
            if (!isOperator(c) && !isOpening(c) && !isClosing(c)) {
                throw new IllegalArgumentException(
                        "Unknown character: '" + c + "'");
            }
            tokens.add(String.valueOf(c));
        }
        if (operand.length() > 0) {
            tokens.add(operand.toString());
        }
        return tokens;
    }

    private static void validateOrder(List<String> tokens) {
        if (tokens.isEmpty()) {
            throw new IllegalArgumentException(
                    "Expression must not be empty.");
        }
        boolean expectingOperand = true;
        for (String token : tokens) {
            char c = token.charAt(0);
            boolean startsOperand = isOperand(token) || isOpening(c);
            if (startsOperand != expectingOperand) {
                throw new IllegalArgumentException(
                        "Misplaced operand/operator near '" + token + "'.");
            }
            if (isOperand(token) || isClosing(c)) {
                expectingOperand = false;
            } else if (isOperator(c)) {
                expectingOperand = true;
            }
        }
        if (expectingOperand) {
            throw new IllegalArgumentException(
                    "Expression must not end with an operator "
                            + "or opening bracket.");
        }
    }

    public static String convert(String infix) {
        List<String> tokens = tokenize(infix);
        validateOrder(tokens);

        Stack stack = new Stack();
        StringBuilder postfix = new StringBuilder();

        for (String token : tokens) {
            char c = token.charAt(0);

            if (isOperand(token)) {
                postfix.append(token).append(' ');

            } else if (isOpening(c)) {
                stack.push(c);

            } else if (isClosing(c)) {
                while (!stack.isEmpty() && !isOpening(stack.peek())) {
                    postfix.append(stack.pop()).append(' ');
                }
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException(
                            "Closing bracket '" + c + "' has no match.");
                }
                char open = stack.pop();
                if (!bracketsMatch(open, c)) {
                    throw new IllegalArgumentException("Bracket '" + open
                            + "' does not match '" + c + "'.");
                }

            } else {
                while (!stack.isEmpty() && shouldPop(c, stack.peek())) {
                    postfix.append(stack.pop()).append(' ');
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            char remaining = stack.pop();
            if (isOpening(remaining)) {
                throw new IllegalArgumentException("Opening bracket '"
                        + remaining + "' has no match.");
            }
            postfix.append(remaining).append(' ');
        }
        return postfix.toString().trim();
    }
}
