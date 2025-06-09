package binarysearch;

public class ArrangingCoins {

    //https://leetcode.com/problems/arranging-coins/
    /*
    You have n coins and you want to build a staircase with these coins. The staircase consists of k rows where the ith row has exactly i coins. The last row of the staircase may be incomplete.
    Given the integer n, return the number of complete rows of the staircase you will build.
     */
    public static void main(String[] args)
    {
        System.out.println(arrangeCoins(5));//2
        System.out.println(arrangeCoins(8));//3
    }

    private static int arrangeCoins(int n)
    {
        int left = 1;
        int right = n;
        int max = Integer.MIN_VALUE;

        while (left <= right){
            int mid = left + (right - left)/2;
            int coins = mid * (mid+1) / 2;
            if(coins == n){
                return mid;
            } else if (coins < n) {
                left = mid + 1;
                max = Math.max(max, mid);
            }else {
                right = mid - 1;
            }
        }

        return max;
    }
}

