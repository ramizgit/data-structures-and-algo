package array;

public class QuickSelect {
    public static void main(String[] args) {
        int[] arr = new int[]{10, 4, 5, 8, 6, 11, 26};
        System.out.println(select(arr, 3, 0, arr.length-1)); //6

        int[] arr2 = new int[]{4, 3, 2, 10, 11, 18, 5, 6, 7};
        System.out.println(select(arr2, 3, 0, arr2.length-1)); //4
    }

    public static int select(int[] arr, int k, int low, int high) {
        int p = partition(arr, low, high);

        if (p == k - 1) {
            return arr[p];
        } else if (p < k - 1) {
            return select(arr, k, p + 1, high);
        } else {
            return select(arr, k, low, p - 1);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int j = low;

        for (int i = low; i <= high; i++) {
            if (arr[i] < arr[high]) {
                swap(arr, i, j);
                j++;
            }
        }

        swap(arr, high, j);

        return j;
    }

    public static void swap(int[] arr, int index, int pivotIndex)
    {
        int tmp = arr[pivotIndex];
        arr[pivotIndex] = arr[index];
        arr[index] = tmp;
    }
}
