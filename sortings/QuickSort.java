public class QuickSort {
    // mengurutkan integer
    public static void sortInt(int[] arr) {
        quickSortInt(arr, 0, arr.length - 1);
    }

    private static void quickSortInt(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionInt(arr, low, high);
            quickSortInt(arr, low, pi - 1);
            quickSortInt(arr, pi + 1, high);
        }
    }

    private static int partitionInt(int[] arr, int low, int high) {
        // menempatkan pivot
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // mengurutkan double
    public static void sortDouble(double[] arr) {
        quickSortDouble(arr, 0, arr.length - 1);
    }

    private static void quickSortDouble(double[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionDouble(arr, low, high);
            quickSortDouble(arr, low, pi - 1);
            quickSortDouble(arr, pi + 1, high);
        }
    }

    private static int partitionDouble(double[] arr, int low, int high) {
        // menempatkan pivot
        double pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                double temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        double temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // mengurutkan char
    public static void sortChar(char[] arr) {
        quickSortChar(arr, 0, arr.length - 1);
    }

    private static void quickSortChar(char[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionChar(arr, low, high);
            quickSortChar(arr, low, pi - 1);
            quickSortChar(arr, pi + 1, high);
        }
    }

    private static int partitionChar(char[] arr, int low, int high) {
        // menempatkan pivot
        char pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        char temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}
