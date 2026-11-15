# Sorting Algorithms

This is a small Java project with six sorting algorithms and a linked list. It runs from the command line and lets you sort integers, doubles, characters, or a linked list.

## Files

- `BubbleSort.java`: bubble sort for int, double, and char arrays
- `SelectionSort.java`: selection sort for int, double, and char arrays
- `InsertionSort.java`: insertion sort for int, double, and char arrays
- `ShellSort.java`: shell sort for int, double, and char arrays
- `MergeSort.java`: merge sort for int, double, and char arrays
- `QuickSort.java`: quick sort for int, double, and char arrays
- `LinkedList/Node.java`: node class for the linked list
- `LinkedList/LinkedList.java`: linked list with insert, bubble sort, and display
- `Main.java`: program entry point

Each sorting class has separate methods for each type. There are no generic types.

## How to run

Compile everything:

```
javac *.java LinkedList/*.java
```

Run the program:

```
java Main
```

## How it works

When you start the program, pick a data type:

- `1` Integer
- `2` Double
- `3` Character
- `4` Linked list

For integers, doubles, and characters, enter the number of items and the values. Then pick a sorting algorithm:

- `1` Bubble sort
- `2` Selection sort
- `3` Insertion sort
- `4` Shell sort
- `5` Merge sort
- `6` Quick sort

The program shows the data before and after sorting. Arrays print in brackets like `[3, 1, 4, 2, 5]`. Characters also print their ASCII values.

For the linked list, enter the number of nodes and the value for each node. The list prints before and after sorting using arrows, like `3 -> 1 -> 4 -> 2 -> 5` and `1 -> 2 -> 3 -> 4 -> 5`.

## Example

Integer input:

```
Pilih tipe (1=Int,2=Double,3=Char,4=LinkedList): 1
Jumlah data: 5
Masukkan data: 3 1 4 2 5
Sebelum: [3, 1, 4, 2, 5]
Pilih algoritma (1=Bubble,2=Selection,3=Insertion,4=Shell,5=Merge,6=Quick): 1
Setelah: [1, 2, 3, 4, 5]
```

Linked list input:

```
Pilih tipe (1=Int,2=Double,3=Char,4=LinkedList): 4
Jumlah node: 5
Masukkan data node: 3 1 4 2 5
Sebelum: 3 -> 1 -> 4 -> 2 -> 5
Setelah: 1 -> 2 -> 3 -> 4 -> 5
```

## Notes

- Only `java.util.Scanner` is used.
- Comments in the code use Indonesian with the prefix "meng-".
- The output is kept minimal. No extra decoration or marketing language.