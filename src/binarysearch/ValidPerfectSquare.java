package binarysearch;

public class ValidPerfectSquare {

    //https://leetcode.com/problems/valid-perfect-square/description/

    public static void main(String[] args)
    {
        System.out.println(isPerfectSquare(16));
        System.out.println(isPerfectSquare(14));

    }

    private static boolean isPerfectSquare(int num)
    {
        int low = 1;
        int high = num;

        while (low <= high){
            int mid = low + (high - low) / 2;
            if(mid * mid == num){
                return true;
            } else if (mid * mid < num) {
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }

        return false;
    }
}
