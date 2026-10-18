package binarysearch.kth;

import java.util.*;

public class FindKTheSmallestPairDistance {

    //https://leetcode.com/problems/find-k-th-smallest-pair-distance/description/

    /*
    Appraoch:-
    1. Brute force : generate all "pair distances", then sort them, return kth distance in the sorted order. O(n^2)
    Complexity:
        - Generate all pairs: (O(n^2))
        - Number of pair distances: (n(n-1)/2 = O(n^2))
        - Sort distances: (O(n^2 log(n^2)) = O(n^2log n))
        So the full brute-force solution is [O(n^2)+O(n^2 log n)]

    2. Binary search : binary search on the distance, then counts valid pairs indirectly using sliding window : O(n logn + n logD)
     */

    /*
    hint why sort:
    Sorting is crucial for both parts of this solution.
    1. Binary search on the answer: We search the possible distance values, from 0 to max(nums) - min(nums). Sorting lets us easily determine the maximum possible distance from the endpoints.
    2. Sliding window to count pairs: Sorting ensures that as windowEnd moves forward, we can move windowStart forward whenever the distance exceeds maxDistance. We never need to move windowStart backward because the values are in ascending order.
    Without sorting, the two-pointer counting logic would not work correctly, because elements could appear in any order.
    One important distinction: Binary search on the answer doesn't inherently require sorting the input array. However, in this problem, sorting is what makes the countPairs() feasibility check efficient — \(O(n)\) rather than \(O(n^2)\).
     */

    //Time : O(n logn + n logD)
    public int smallestDistancePair(int[] nums, int k)
    {
        //sort input array
        Arrays.sort(nums); //O(nlogn)

        //binary search
        int low = 0; //min possible distance
        int high = nums[nums.length-1] - nums[0]; //max possible distance, larget - smallest
        int answer = 0;

        while(low <= high){ //O(logD), where D is the range = max(nums) - min(nums)
            int mid = low + (high - low)/2;

            if(countPairsWithDistanceAtMost(nums, mid) >= k){ //does this threshold has at least k paris
                answer = mid; //possible answer
                high = mid - 1; //try lower
            }else{
                low = mid + 1;
            }
        }

        return answer;
    }

    //O(n)
    private int countPairsWithDistanceAtMost(int[] nums, int maxDistance)
    {
        int windowStart = 0;
        int count = 0;

        for(int windowEnd = 0; windowEnd < nums.length; windowEnd++){

            while(nums[windowEnd] - nums[windowStart] > maxDistance){
                windowStart++; //shrink window until distance is valid
            }

            //every element before windowEnd inside the window forms a valid pair with nums[windowEnd]
            count += windowEnd - windowStart; //why no +1? because nums[windowEnd] cannot pair with itself. We count only the elements before windowEnd within the valid window.

        }

        return count;
    }
}
