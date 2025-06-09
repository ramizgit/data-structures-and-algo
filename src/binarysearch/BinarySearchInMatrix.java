package binarysearch;

public class BinarySearchInMatrix {
    public static void main(String[] args)
    {
        int[][] matrix = {  {1, 3, 5, 6},
                {7, 9, 10, 12},
                {15, 17, 18, 21},
                {22, 26, 45, 50}};

        System.out.println("Found : "+binarySearch(matrix, 1));
    }

    private static boolean binarySearch(int[][] matrix, int target)
    {
        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0;
        int right = m * n - 1; //15

        while (left <= right){
            int mid = (left + right) / 2; // (0+15)/2 = 7
            int row = mid / n; // 7 / 4 = 1
            int col = mid % n; // 7 % 4 = 3

            if(target == matrix[row][col]){
                return true;
            }else if(target < matrix[row][col]){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        return false;
    }
}
