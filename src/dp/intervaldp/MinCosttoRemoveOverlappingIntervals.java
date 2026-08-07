package dp.intervaldp;

import java.util.*;

/*
Minimum Cost to Remove Overlapping Intervals

You are given an array of intervals where:

intervals[i] = [starti, endi, costi]

Each interval has:

starti — start time
endi — end time
costi — the cost to remove that interval

Two intervals overlap if:

start2 < end1

(Intervals that touch at an endpoint are not considered overlapping.)

You may remove any number of intervals.

Return the minimum total removal cost required so that the remaining intervals are pairwise non-overlapping.

Example 1
Input:

intervals = [
    [1,4,100],
    [2,3,1],
    [3,5,1]
]

Output:

1

Explanation:

Remove

[2,3,1]

Remaining:

[1,4]
[3,5]

These do not overlap because 3 == 3 is allowed.

Total removal cost:

1
Example 2
Input:

intervals = [
    [1,100,100],
    [2,3,60],
    [4,5,60],
    [6,7,60]
]

Output:

100

Explanation:

Remove

[1,100,100]

Keep

[2,3]
[4,5]
[6,7]

Removing the large interval costs 100, which is cheaper than removing the three smaller intervals (180).

Example 3
Input:

intervals = [
    [1,2,5],
    [2,3,10],
    [3,4,20]
]

Output:

0

Explanation:

No intervals overlap, so nothing needs to be removed.

Constraints
1 <= intervals.length <= 100000

0 <= starti < endi <= 10^9

1 <= costi <= 10^9
 */

public class MinCosttoRemoveOverlappingIntervals {

    public long minimumRemovalCost(int[][] intervals)
    {
        //intervals[i] = [start, end, cost]

        //input validation
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1])); //sort by end time

        long totalCost = 0;

        for(int[] interval : intervals){
            totalCost += interval[2];
        }

        int n = intervals.length;

        long[] dp = new long[n]; //dp[i] = maximum total cost of non-overlapping intervals we can KEEP considering intervals[0...i]

        dp[0] = intervals[0][2]; //base case

        for(int i=1; i<n; i++){

            int currStart = intervals[i][0];
            int currCost = intervals[i][2];

            //among intervals 0 to i-1, find the rightmost interval whose end <= current.start.
            int lastCompatible = binarySearchLastCompatible(intervals, i - 1, currStart);

            long take = currCost;

            if (lastCompatible != -1) {
                take += dp[lastCompatible];
            }

            //either keep the current interval or skip it.
            dp[i] = Math.max(
                    take, //take
                    dp[i-1] //don't take
            );
        }

        return totalCost - dp[n-1]; //min cost to remove overlapping ones
    }

    private int binarySearchLastCompatible(int[][] intervals, int high, int currStart)
    {
        int low = 0;
        int answer = -1; //index of the right most compatible interval

        while(low <= high){

            int mid = low + (high - low)/2;

            if(intervals[mid][1] <= currStart){
                answer = mid; //possible answer
                low = mid + 1; //try higher
            }else{
                high = mid - 1;
            }
        }

        return answer;
    }
}
