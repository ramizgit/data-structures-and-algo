package google;

public class SwapAdjacentInLRString {

    //https://leetcode.com/problems/swap-adjacent-in-lr-string/

    public boolean canTransform(String start, String result)
    {
        if(start.length() != result.length()){
            return false;
        }

        int n = start.length();

        int left = 0;
        int right = 0;

        while(left < n && right < n){

            while(left < n && start.charAt(left) == 'X'){
                left++;
            }

            while(right < n && result.charAt(right) == 'X'){
                right++;
            }

            if (left == n || right == n) {
                return left == n && right == n;
            }

            if(start.charAt(left) != result.charAt(right)){
                return false;
            }

            if(start.charAt(left) == 'L' && left < right){
                return false;
            }

            if(start.charAt(left) == 'R' && left > right){
                return false;
            }

            left++;
            right++;
        }

        return true;
    }
}
