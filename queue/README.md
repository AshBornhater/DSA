# Queue Assignment — Palindrome Checker

This folder contains my own implementations of a few queue variants, plus a small interactive program (`Main`) that uses any of them to check whether a word or sentence is a palindrome.

Everything is plain Java — no external libraries, no generics, `char`-based to keep the focus on the data structures themselves.

## Files

| File | What it is |
|---|---|
| `LinkedListQueue.java` | Queue built on a **single linked-list**. `enqueue` appends at the tail, `dequeue` pops from the head. Both are O(1). |
| `DoublyLinkedListQueue.java` | Same idea, but every node carries a `prev` pointer too. That means the list can be printed backwards, which the round-trip `printQueueBackward()` demo shows off. |
| `CircularArrayQueue.java` | Queue built on a **circular array**. Indices wrap with `% capacity`, so the space freed by old `dequeue` calls gets reused without shifting anything. If it fills up, the capacity is doubled automatically. |
| `PriorityQueue.java` | Priority queue using a sorted linked-list (ascending insertion). `dequeue` always returns the element with the *highest* priority — in this implementation, the smallest character value. |
| `Main.java` | A menu-driven program that lets you pick a queue and then feeds your input into it to check for a palindrome. |

## Running it

```bash
javac *.java
java Main
```

Then just follow the menu. Sample inputs worth trying: `katak`, `radar`, `kasur rusak`, or anything with mixed case like `Nurses Run` — the checker lowercases the input and silently drops everything that isn't a letter, so spaces and punctuation don't get in the way.

### The idea behind the palindrome check

Since a (normal) queue is FIFO, characters come back out in exactly the order they went in. So the check is simple:

1. Enqueue every character of the cleaned string, in order.
2. Dequeue them one at a time, and compare each one against the string read **back to front**.
3. If every pair matches, it's a palindrome.

In other words: *forward order* (from the queue) vs. *backward order* (from the string). Equal means palindrome.

## Why `PriorityQueue` is not offered as a palindrome option

This was a deliberate design decision, not an oversight. Checking for a palindrome depends on the queue preserving the *original order* of the characters — which is exactly what a FIFO queue guarantees and exactly what a priority queue does **not**.

A priority queue removes elements by priority, not by arrival order. Concretely, if you enqueue `katak` and then dequeue everything, you get `aakkt` — the characters have been re-sorted and the original order is gone. Every subsequent comparison against the reversed string would be meaningless.

So `PriorityQueue.java` is fully written and compiles fine, but in `Main` the option 4 only runs a short **demo** that shows its reordering behavior and explains why that makes it unsuitable for palindrome checking. Picking it does not lead into the palindrome flow.

## On complexity

- All three palindrome-capable queues enqueue and dequeue in O(1) (the circular array version is O(1) amortized because of resizing).
- The overall palindrome check is O(n): each character is enqueued once, dequeued once, and compared once.
- The priority queue's `enqueue` is O(n) since it does a sorted insertion; that's the trade-off with a list-based approach versus a heap.

## Notes

- The implementations intentionally avoid Java's built-in `Queue` interface so the mechanics are visible instead of hidden behind library code.
- Menu input is validated, so typing something that isn't a number won't crash the program.
- Only the interface contract needed by `Main` (`enqueue`, `dequeue`, `isEmpty`) is required of the queues; the `peek`, `size`, `print` methods are extras for testing.
