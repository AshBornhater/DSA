# Infix to Postfix Converter (Stack + Queue)

A small program for a Data Structures assignment that converts infix expressions (the ones we normally write, such as `A + B * C`) into postfix expressions (`A B C * +`) using a manually implemented stack and queue.

The stack is implemented as a linked list instead of `java.util.Stack`, so the implementation of `push()`, `pop()`, and `peek()` is clearly demonstrated.

The queue is also implemented manually as a linked list. It is used as the postfix output buffer: the conversion enqueues every postfix token in the order it is produced, then the program dequeues the tokens from the front to build the final result. This keeps the output in FIFO order without using `java.util.Queue`.

To comply with the assignment requirements, the only Java library used is `Scanner` for reading user input, and it is only used in `Main.java`. The program does not use `ArrayList`, `List`, `StringBuilder`, `Character`, `java.util.Queue`, or any other collection classes.

## Folder Contents

| File | Description |
| --- | --- |
| `Node.java` | Linked list node containing a single `char` and a `next` pointer |
| `Stack.java` | Basic stack implementation using a linked list (`push`, `pop`, `peek`, `isEmpty`) |
| `Queue.java` | Basic queue implementation using a linked list (`enqueue`, `dequeue`, `peek`, `isEmpty`) |
| `InfixToPostfix.java` | Core logic for reading operands, validating expressions, and performing the conversion |
| `Main.java` | Interactive program that reads user input using `Scanner` and prints the result |

## Running the Program

Compile all source files, then run `Main`:

```bash
javac *.java
java Main
```

Enter one infix expression per line. Type `q` or `Q` to quit. The program prints the original infix expression and the resulting postfix expression, for example:

```text
Input infix expression (q = quit): 5*(10+8)+9^3

Infix  : 5*(10+8)+9^3
Postfix: 5 10 8 + * 9 3 ^ +
```

## Features

- Supported operators: `+`, `-`, `*`, `/`, `^`
- Supports three types of brackets: `( )`, `[ ]`, `{ }`, with each opening bracket checked against its corresponding closing bracket
- Supports multi-character operands, so `12` or `3.5` is treated as a single operand
- Whitespace in the input is ignored
- Postfix tokens are buffered in a FIFO queue before the final string is printed
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

`convertToQueue()` and `validateOrder()` read the infix expression directly from left to right.

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

The main conversion logic is implemented in `convertToQueue()`:

- Operands are enqueued directly into the postfix queue.
- Opening brackets are pushed onto the stack.
- When a closing bracket is encountered, operators are popped from the stack and enqueued until the corresponding opening bracket is found. The opening bracket is then popped, and its matching pair is checked.
- When an operator is encountered, operators at the top of the stack with higher precedence (or equal precedence when applicable) are popped and enqueued. The current operator is then pushed onto the stack.
- At the end of the expression, all remaining operators are popped from the stack and enqueued. If an opening bracket remains, it means the bracket was never closed.

Operator precedence:

| Operator | Precedence | Associativity |
| --- | --- | --- |
| `^` | 3 (Highest) | Right-to-left |
| `*`, `/` | 2 | Left-to-right |
| `+`, `-` | 1 (Lowest) | Left-to-right |

The `^` operator is **right-associative**, meaning `A^B^C` is evaluated as `A^(B^C)`, not `(A^B)^C`.

### 4. Queue as the Postfix Output Buffer

`convertToQueue()` returns a `Queue` containing the postfix tokens in the exact order produced by the shunting-yard algorithm. The public `convert()` method then dequeues the queue one token at a time and joins the tokens with a single space:

```text
Queue after conversion: front -> [5] [10] [8] [+] [*] [9] [3] [^] [+] <- rear
Dequeue order:          5 10 8 + * 9 3 ^ +
```

Because `enqueue()` adds at the rear and `dequeue()` removes from the front, the FIFO order preserves the postfix sequence. A space is added only between tokens, so the final string never has a trailing space. This replaces the old string-building and `removeTrailingSpace()` logic.

### 5. Queue Implementation

`Queue.java` uses two references, `front` and `rear`:

- `enqueue()` creates a new node and links it after `rear`; if the queue is empty, the node becomes both `front` and `rear`.
- `dequeue()` saves the data at `front`, moves `front` to the next node, and resets `rear` to `null` when the queue becomes empty.
- `peek()` returns the front element without removing it.
- `isEmpty()` returns `true` when `front` is `null`.

All queue operations run in O(1) time, just like the stack operations. The conversion itself is O(n) in the number of characters in the expression.

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
