package slidingWindow;

public class LongestNiceSubarray {

    // https://leetcode.com/problems/longest-nice-subarray/

    //similar to  : LongestSubstrWithKDistinctChar
    
    //todo : practice

    public int longestNiceSubarray(int[] nums)
    {
        /*
        store all set bits currently present in the window
        windowBits is the compressed representation of the entire current window.
        it tells us which bits are already occupied by numbers inside the window.
         */
        int windowBits = 0;

        int left = 0;
        int right = 0;
        int maxLen = 0;

        while(right < nums.length){

            /*
            If current number shares any set bit
            with existing window, shrink window.
             */
            while((windowBits & nums[right]) != 0){

                /*
                Remove left element's bits from window.
                XOR removes left element's set bits from windowBits
                because each bit appears only once in a valid nice subarray
                 */
                windowBits ^= nums[left];

                left++;
            }

            //add current number into window
            windowBits |= nums[right];

            //update answer
            maxLen = Math.max(maxLen, right - left + 1);

            right++;
        }

        return maxLen;
    }
}
