public class MergeSort {
    // mengurutkan integer
    public static void sortInt(int[] arr) {
        int[] temp = new int[arr.length];
        mergeSortInt(arr, temp, 0, arr.length - 1);
    }

    private static void mergeSortInt(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortInt(arr, temp, left, mid);
            mergeSortInt(arr, temp, mid + 1, right);
            mergeInt(arr, temp, left, mid, right);
        }
    }

    private static void mergeInt(int[] arr, int[] temp, int left, int mid, int right) {
        // menggabungkan hasil
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            arr[k] = temp[i];
            i++;
            k++;
        }
    }

    // mengurutkan double
    public static void sortDouble(double[] arr) {
        double[] temp = new double[arr.length];
        mergeSortDouble(arr, temp, 0, arr.length - 1);
    }

    private static void mergeSortDouble(double[] arr, double[] temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortDouble(arr, temp, left, mid);
            mergeSortDouble(arr, temp, mid + 1, right);
            mergeDouble(arr, temp, left, mid, right);
        }
    }

    private static void mergeDouble(double[] arr, double[] temp, int left, int mid, int right) {
        // menggabungkan hasil
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            arr[k] = temp[i];
            i++;
            k++;
        }
    }

    // mengurutkan char
    public static void sortChar(char[] arr) {
        char[] temp = new char[arr.length];
        mergeSortChar(arr, temp, 0, arr.length - 1);
    }

    private static void mergeSortChar(char[] arr, char[] temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortChar(arr, temp, left, mid);
            mergeSortChar(arr, temp, mid + 1, right);
            mergeChar(arr, temp, left, mid, right);
        }
    }

    private static void mergeChar(char[] arr, char[] temp, int left, int mid, int right) {
        // menggabungkan hasil
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            arr[k] = temp[i];
            i++;
            k++;
        }
    }
}
