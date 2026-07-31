package twopointers;

public class SwapAdjacentInLRString {

    //https://leetcode.com/problems/swap-adjacent-in-lr-string/

    public boolean canTransform(String start, String result)
    {
        if(start.length() != result.length()){
            return false;
        }

        int n = start.length();

        int left = 0; //pointer on start
        int right = 0; //pointer on target

        while(left < n && right < n){

            //skip 'X' characters
            while(left < n && start.charAt(left) == 'X'){
                left++;
            }

            //skip 'X' characters
            while(right < n && result.charAt(right) == 'X'){
                right++;
            }

            //check if one pointer reaches the end before the other
            if (left == n || right == n) {
                return left == n && right == n;
            }

            //match?
            if(start.charAt(left) != result.charAt(right)){
                return false;
            }

            //handle case where start  : LX target : XL, LX cant be replaced with XL, it can only move left
            if(start.charAt(left) == 'L' && left < right){
                return false;
            }

            //handle case where start  : XR target : RX, XR cant be replaced with RX, it can only move right
            if(start.charAt(left) == 'R' && left > right){
                return false;
            }

            left++;
            right++;
        }

        return true;
    }
}
