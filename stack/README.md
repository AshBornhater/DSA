# Infix to Postfix Converter (Java)

A small program for a Data Structures assignment that converts infix expressions (the ones we normally write, such as `A + B * C`) into postfix expressions (`A B C * +`) using a stack.

The stack is implemented manually using a linked list instead of `java.util.Stack`, so the implementation of `push()`, `pop()`, and `peek()` is clearly demonstrated.

## Folder Contents

| File | Description |
| --- | --- |
| `Node.java` | Linked list node containing a single `char` and a `next` pointer |
| `Stack.java` | Basic stack implementation using a linked list (`push`, `pop`, `peek`, `isEmpty`) |
| `InfixToPostfix.java` | Core logic for tokenizing, validating, and converting expressions |
| `Main.java` | Interactive program that reads input from the keyboard and prints the result |

## Features

- Supported operators: `+`, `-`, `*`, `/`, `^`
- Supports three types of brackets: `( )`, `[ ]`, `{ }`, with each opening bracket checked against its corresponding closing bracket
- Operands can contain multiple characters, so `12` or `3.5` is treated as a single operand
- Whitespace in the input is ignored
- Invalid expressions are rejected with clear error messages instead of producing incorrect results

The last feature is particularly important. The program validates the expression structure before performing the conversion, so invalid expressions such as `A + * B`, `(A+B`, or `A+B)` are rejected immediately.

## How to Run

Requires a JDK (the program uses modern Java syntax, including a `switch` expression in `precedence()`, so Java 14 or later is required).

Navigate to the project folder and run:

```bash
javac *.java
java Main
```

Example session:

```text
=== Infix to Postfix Conversion ===
Operator: + - * / ^
Brackets: ( ) [ ] { }

Input infix expression (q = quit): A+B*C

Infix  : A+B*C
Postfix: A B C * +

Input infix expression (q = quit): (A+B)*C

Infix  : (A+B)*C
Postfix: A B + C *

Input infix expression (q = quit): q
```

Additional examples:

| Infix | Postfix |
| --- | --- |
| `A+B*C` | `A B C * +` |
| `(A+B)*C` | `A B + C *` |
| `A*B+C/D` | `A B * C D / +` |
| `{a+(b*c)}^2` | `a b c * + 2 ^` |
| `12+3.5*4` | `12 3.5 4 * +` |

## How It Works

### 1. Tokenization

`tokenize()` reads the input string character by character. Letters, digits, and decimal points are combined into a single operand, while operators, brackets, and whitespace are handled separately. Any unsupported character immediately triggers an `IllegalArgumentException` (`Unknown character`).

### 2. Expression Validation

`validateOrder()` uses a single `expectingOperand` flag that alternates between states: after an operand, an operator is expected; after an operator, an operand is expected. This allows structurally invalid expressions to be rejected before conversion.

### 3. Conversion (Shunting-yard Algorithm)

The main logic is implemented in `convert()`:

- Operands are directly appended to the output.
- Opening brackets are pushed onto the stack.
- When a closing bracket is encountered, operators are popped from the stack and appended to the output until the corresponding opening bracket is found. The opening bracket is then popped, and its matching pair is checked.
- When an operator is encountered, operators at the top of the stack with higher precedence (or equal precedence, when applicable) are popped and appended to the output. The current operator is then pushed onto the stack.
- At the end of the expression, all remaining operators are popped from the stack and appended to the output. If an opening bracket remains, it means the bracket was never closed.

Operator precedence: `^` has the highest precedence (3), followed by `*` and `/` (2), then `+` and `-` (1). The `^` operator is **right-associative**, meaning `A^B^C` is evaluated as `A^(B^C)`, not `(A^B)^C`.
