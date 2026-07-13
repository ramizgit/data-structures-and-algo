package lineSweep;

public class CheckIfAllIntegersInRangeAreCovered {

    //https://leetcode.com/problems/check-if-all-the-integers-in-a-range-are-covered/description/

    /*
    1 <= ranges.length <= 50
    1 <= starti <= endi <= 50
    1 <= left <= right <= 50
     */

    // Time : O(n)
    // Space: O(1)
    public boolean isCovered(int[][] ranges, int left, int right)
    {
        //input validation
        if (ranges == null || ranges.length == 0) {
            return false;
        }

        //convert each range into difference array update using start (+1) and end (-1)
        int[] diff = new int[52];

        for(int[] range : ranges){

            int start = range[0];
            int end = range[1];

            diff[start] += 1;
            diff[end+1] -= 1; //end is inclusive
        }

        //sweep through the difference array using prefix sum
        for(int i=1; i<diff.length; i++){
            diff[i] += diff[i-1];
        }

        //check if each integer in the inclusive range [left, right] is covered or not
        for(int i=left; i<=right; i++){
            if(diff[i] == 0){
                return false;
            }
        }

        return true;
    }
}
