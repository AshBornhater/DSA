# Infix to Postfix Converter 

A small program for a Data Structures assignment that converts infix expressions (the ones we normally write, such as `A + B * C`) into postfix expressions (`A B C * +`) using a stack.

The stack is implemented manually using a linked list instead of `java.util.Stack`, so the implementation of `push()`, `pop()`, and `peek()` is clearly demonstrated.

To comply with the assignment requirements, the only Java library used is `Scanner` for reading user input, and it is only used in `Main.java`. The program does not use `ArrayList`, `List`, `StringBuilder`, `Character`, or any other collection classes.

## Folder Contents

| File | Description |
| --- | --- |
| `Node.java` | Linked list node containing a single `char` and a `next` pointer |
| `Stack.java` | Basic stack implementation using a linked list (`push`, `pop`, `peek`, `isEmpty`) |
| `InfixToPostfix.java` | Core logic for reading operands, validating expressions, and performing the conversion |
| `Main.java` | Interactive program that reads user input using `Scanner` and prints the result |

## Features

- Supported operators: `+`, `-`, `*`, `/`, `^`
- Supports three types of brackets: `( )`, `[ ]`, `{ }`, with each opening bracket checked against its corresponding closing bracket
- Supports multi-character operands, so `12` or `3.5` is treated as a single operand
- Whitespace in the input is ignored
- Invalid expressions are rejected with clear error messages instead of producing incorrect results

The program validates the expression structure before performing the conversion, so invalid expressions such as `A + * B`, `(A+B`, or `A+B)` are rejected immediately.

Additional examples:

| Infix | Postfix |
| --- | --- |
| `A+B*C` | `A B C * +` |
| `(A+B)*C` | `A B + C *` |
| `A*B+C/D` | `A B * C D / +` |
| `{a+(b*c)}^2` | `a b c * + 2 ^` |
| `12+3.5*4` | `12 3.5 4 * +` |

## How It Works

### 1. Reading Operands Without a Token List

`convert()` and `validateOrder()` read the infix expression directly from left to right.

The helper methods `isDigit()` and `isLetter()` check ASCII character ranges. `isOperandChar()` additionally handles decimal points, while `isWhitespace()` recognizes spaces, tabs, and newline characters.

`operandEnd()` returns the index immediately after the last character of an operand. This allows values such as `12` or `3.5` to be treated as a single unit without requiring `ArrayList` or `StringBuilder`.

### 2. Expression Validation

`validateOrder()` uses a single `expectingOperand` flag that alternates between states:

- After an operand, an operator or closing bracket is expected.
- After an operator, an operand or opening bracket is expected.
- After an opening bracket, an operand or another opening bracket is expected.

Invalid positions are reported through the `misplaced()` helper method.

The `hasToken` flag rejects empty input, while the final validation checks prevent expressions from ending with an operator or an opening bracket.

### 3. Conversion (Shunting-yard Algorithm)

The main conversion logic is implemented in `convert()`:

- Operands are directly appended to the output.
- Opening brackets are pushed onto the stack.
- When a closing bracket is encountered, operators are popped from the stack and appended to the output until the corresponding opening bracket is found. The opening bracket is then popped, and its matching pair is checked.
- When an operator is encountered, operators at the top of the stack with higher precedence (or equal precedence when applicable) are popped and appended to the output. The current operator is then pushed onto the stack.
- At the end of the expression, all remaining operators are popped from the stack and appended to the output. If an opening bracket remains, it means the bracket was never closed.

Operator precedence:

| Operator | Precedence | Associativity |
| --- | --- | --- |
| `^` | 3 (Highest) | Right-to-left |
| `*`, `/` | 2 | Left-to-right |
| `+`, `-` | 1 (Lowest) | Left-to-right |

The `^` operator is **right-associative**, meaning `A^B^C` is evaluated as `A^(B^C)`, not `(A^B)^C`.

The postfix output is cleaned up using `removeTrailingSpace()`, which only uses `charAt()` and `substring()`.

## Error Handling

The program handles the following errors:

- Empty input
- Unknown characters
- Incorrect operand/operator placement, such as `A + * B`
- Expressions ending with an operator or opening bracket
- Closing brackets without matching opening brackets
- Unclosed opening brackets
- Mismatched bracket pairs, such as `(A+B]`

All errors are thrown as `IllegalArgumentException` from `InfixToPostfix`, then caught in `Main.java` and displayed using the format:

```text
Invalid expression: ...
```

This prevents the program from terminating unexpectedly when invalid expressions are entered.