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

    private static boolean bracketsMatch(char open, char close) {
        return (open == '(' && close == ')')
                || (open == '[' && close == ']')
                || (open == '{' && close == '}');
    }

    private static int precedence(char op) {
        switch (op) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
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

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private static boolean isOperandChar(char c) {
        return isLetter(c) || isDigit(c) || c == '.';
    }

    private static boolean isWhitespace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    private static int operandEnd(String infix, int start) {
        int end = start;
        while (end < infix.length()
                && isOperandChar(infix.charAt(end))) {
            end++;
        }
        return end;
    }

    private static void misplaced(char c) {
        throw new IllegalArgumentException(
                "Misplaced operand/operator near '" + c + "'.");
    }

    private static void validateOrder(String infix) {
        boolean expectingOperand = true;
        boolean hasToken = false;
        int i = 0;
        while (i < infix.length()) {
            char c = infix.charAt(i);
            if (isWhitespace(c)) {
                i++;
                continue;
            }
            hasToken = true;
            if (isOperandChar(c)) {
                if (!expectingOperand) {
                    misplaced(c);
                }
                i = operandEnd(infix, i);
                expectingOperand = false;
            } else if (isOpening(c)) {
                if (!expectingOperand) {
                    misplaced(c);
                }
                i++;
            } else if (isClosing(c) || isOperator(c)) {
                if (expectingOperand) {
                    misplaced(c);
                }
                i++;
                expectingOperand = isOperator(c);
            } else {
                throw new IllegalArgumentException(
                        "Unknown character: '" + c + "'");
            }
        }
        if (!hasToken) {
            throw new IllegalArgumentException(
                    "Expression must not be empty.");
        }
        if (expectingOperand) {
            throw new IllegalArgumentException(
                    "Expression must not end with an operator "
                            + "or opening bracket.");
        }
    }

    public static String convert(String infix) {
        validateOrder(infix);

        Stack stack = new Stack();
        String postfix = "";
        int i = 0;

        while (i < infix.length()) {
            char c = infix.charAt(i);

            if (isWhitespace(c)) {
                i++;

            } else if (isOperandChar(c)) {
                int end = operandEnd(infix, i);
                postfix = postfix + infix.substring(i, end) + ' ';
                i = end;

            } else if (isOpening(c)) {
                stack.push(c);
                i++;

            } else if (isClosing(c)) {
                while (!stack.isEmpty() && !isOpening(stack.peek())) {
                    postfix = postfix + stack.pop() + ' ';
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
                i++;

            } else {
                while (!stack.isEmpty() && shouldPop(c, stack.peek())) {
                    postfix = postfix + stack.pop() + ' ';
                }
                stack.push(c);
                i++;
            }
        }

        while (!stack.isEmpty()) {
            char remaining = stack.pop();
            if (isOpening(remaining)) {
                throw new IllegalArgumentException("Opening bracket '"
                        + remaining + "' has no match.");
            }
            postfix = postfix + remaining + ' ';
        }

        return removeTrailingSpace(postfix);
    }

    private static String removeTrailingSpace(String text) {
        int end = text.length();
        while (end > 0 && text.charAt(end - 1) == ' ') {
            end--;
        }
        return text.substring(0, end);
    }
}
