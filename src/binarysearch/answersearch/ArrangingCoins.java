package binarysearch.answersearch;

public class ArrangingCoins {

    //https://leetcode.com/problems/arranging-coins/
    /*
    You have n coins and you want to build a staircase with these coins. The staircase consists of k rows where the ith row has exactly i coins. The last row of the staircase may be incomplete.
    Given the integer n, return the number of complete rows of the staircase you will build.
     */

    private static int arrangeCoins(int n)
    {
        int low = 1; //lowest possible complete rows
        int high = n; //highest possible complete rows
        int answer = 0;

        while (low <= high){

            int mid = low + (high - low)/2;
            int coinsNeeded = mid * (mid+1) / 2; //coins needed to build 'mid' complete rows

            if (coinsNeeded <= n) {
                answer = Math.max(answer, mid); //possible answer
                low = mid + 1; //try higher
            }else {
                high = mid - 1; //try lower
            }
        }

        return answer;
    }
}

