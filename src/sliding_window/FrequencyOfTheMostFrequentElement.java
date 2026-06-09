package consistenthashing.sliding_window;

import java.util.*;

/*
The frequency of an element is the number of times it occurs in an array.
You are given an integer array nums and an integer k. In one operation, you can choose an index of nums and increment the element at that index by 1.
Return the maximum possible frequency of an element after performing at most k operations.
 */

public class FrequencyOfTheMostFrequentElement {

    //https://leetcode.com/problems/frequency-of-the-most-frequent-element/description/

    //sliding window approach
    /*
    Sorting : O(n log n)
    Window  : O(n)
    Total   : O(n log n)
     */
    public int maxFrequency(int[] nums, int k)
    {
        Arrays.sort(nums);

        int left = 0;
        int right = 0;
        long windowSum = 0;
        int maxFreq = 0;

        while(right < nums.length){

            /*
            Key idea

            After sorting:

            [1, 2, 4]

            If right is at:

            4

            then we try to make the whole window:

            [1, 2, 4]

            equal to:

            4

            Cost needed:

            (4-1) + (4-2) + (4-4)
            =
            3 + 2 + 0
            =
            5

            Instead of computing that one-by-one, we use:

            target * windowSize - windowSum

            So:

            4 * 3 - (1 + 2 + 4)
            =
            12 - 7
            =
            5

            The validity condition is:

            nums[right]⋅(right−left+1)−windowSum≤k

            If it's invalid:

            while(cost > k)

            we move:

            left++

            until it becomes valid again.
             */

            windowSum += nums[right]; //expand window

            // shrink window until it becomes valid
            while((long) nums[right] * (right - left + 1) - windowSum > k){
                windowSum -= nums[left];
                left++;
            }

            maxFreq = Math.max(maxFreq, right - left + 1);

            right++;
        }

        return maxFreq;
    }

    //brute force
    /*
    Sorting     : O(n log n)
    Brute Force : O(n²)
    Total       : O(n²)
    */
    public int maxFrequencyBruteForce(int[] nums, int k)
    {
        Arrays.sort(nums);

        int answer = 1;

        for(int i = 0; i < nums.length; i++){

            long operations = 0;
            int freq = 1;

            for(int j = i - 1; j >= 0; j--){

                operations += nums[i] - nums[j];

                if(operations > k){
                    break;
                }

                freq++;
            }

            answer = Math.max(answer, freq);
        }

        return answer;
    }
}
