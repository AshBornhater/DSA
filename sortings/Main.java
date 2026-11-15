import java.util.Scanner;

public class Main {
    // fungsi konversi ke ascii
    public static int charToAscii(char c) {
        return (int) c;
    }

    // fungsi salin array int
    public static int[] salinInt(int[] arr) {
        return arr.clone();
    }

    // fungsi salin array double
    public static double[] salinDouble(double[] arr) {
        return arr.clone();
    }

    // fungsi salin array char
    public static char[] salinChar(char[] arr) {
        return arr.clone();
    }

    // tampilkan array int
    public static void tampilkanInt(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }

    // tampilkan array double
    public static void tampilkanDouble(double[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }

    // tampilkan array char
    public static void tampilkanChar(char[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n1=Int\n2=Double\n3=Cha\n4=LinkedList\n");
        System.out.print("\nPilih Tipe: ");
        int tipe = sc.nextInt();

        if (tipe == 1) {
            System.out.print("Jumlah data: ");
            int n = sc.nextInt();
            int[] arr = new int[n];
            System.out.print("Masukkan data: ");
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            System.out.print("Before Sorting: [");
            for (int i = 0; i < n; i++) {
                System.out.print(arr[i]);
                if (i < n - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");

            System.out.print("(1=Bubble Sort\n2=Selection Sort\n3=Insertion Sort\n4=Shell Sort\n5=Merge Sort\n6=Quick Sort\n");
            System.out.print("\nPilih Algoritma: ");

            int algo = sc.nextInt();

            int[] a = salinInt(arr);
            if (algo == 1) {
                BubbleSort.sortInt(a);
            } else if (algo == 2) {
                SelectionSort.sortInt(a);
            } else if (algo == 3) {
                InsertionSort.sortInt(a);
            } else if (algo == 4) {
                ShellSort.sortInt(a);
            } else if (algo == 5) {
                MergeSort.sortInt(a);
            } else if (algo == 6) {
                QuickSort.sortInt(a);
            }

            System.out.print("After Sorting: [");
            for (int i = 0; i < n; i++) {
                System.out.print(a[i]);
                if (i < n - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        } else if (tipe == 2) {
            System.out.print("Jumlah data: ");
            int n = sc.nextInt();
            double[] arr = new double[n];
            System.out.print("Masukkan data: ");
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextDouble();
            }

            System.out.print("Before Sorting: [");
            for (int i = 0; i < n; i++) {
                System.out.print(arr[i]);
                if (i < n - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");

            System.out.print("Pilih algoritma (1=Bubble,2=Selection,3=Insertion,4=Shell,5=Merge,6=Quick): ");
            int algo = sc.nextInt();

            double[] a = salinDouble(arr);
            if (algo == 1) {
                BubbleSort.sortDouble(a);
            } else if (algo == 2) {
                SelectionSort.sortDouble(a);
            } else if (algo == 3) {
                InsertionSort.sortDouble(a);
            } else if (algo == 4) {
                ShellSort.sortDouble(a);
            } else if (algo == 5) {
                MergeSort.sortDouble(a);
            } else if (algo == 6) {
                QuickSort.sortDouble(a);
            }

            System.out.print("After Sorting: [");
            for (int i = 0; i < n; i++) {
                System.out.print(a[i]);
                if (i < n - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        } else if (tipe == 3) {
            System.out.print("Jumlah data: ");
            int n = sc.nextInt();
            char[] arr = new char[n];
            System.out.print("Masukkan data: ");
            for (int i = 0; i < n; i++) {
                arr[i] = sc.next().charAt(0);
            }

            System.out.print("Before Sorting: [");
            for (int i = 0; i < n; i++) {
                System.out.print(arr[i]);
                if (i < n - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");

            System.out.print("Pilih algoritma (1=Bubble,2=Selection,3=Insertion,4=Shell,5=Merge,6=Quick): ");
            int algo = sc.nextInt();

            char[] a = salinChar(arr);
            if (algo == 1) {
                BubbleSort.sortChar(a);
            } else if (algo == 2) {
                SelectionSort.sortChar(a);
            } else if (algo == 3) {
                InsertionSort.sortChar(a);
            } else if (algo == 4) {
                ShellSort.sortChar(a);
            } else if (algo == 5) {
                MergeSort.sortChar(a);
            } else if (algo == 6) {
                QuickSort.sortChar(a);
            }

            System.out.print("After Sorting: [");
            for (int i = 0; i < n; i++) {
                System.out.print(a[i]);
                if (i < n - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");

            System.out.print("ASCII: [");
            for (int i = 0; i < n; i++) {
                System.out.print(charToAscii(arr[i]));
                if (i < n - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        } else if (tipe == 4) {
            System.out.print("Jumlah node: ");
            int n = sc.nextInt();
            LinkedList list = new LinkedList();
            System.out.print("Masukkan data node: ");
            for (int i = 0; i < n; i++) {
                int val = sc.nextInt();
                list.insert(val);
            }

            System.out.print("Before Sorting: ");
            list.display();

            list.sort();

            System.out.print("After Sorting: ");
            list.display();
        }

        sc.close();
    }
}
