package LIS.twoDimensionLIS;

import java.util.*;

/*
You are given an integer n and two integer arrays, north and south, each of length n.
The i-th bridge connects position north[i] on the North bank to position south[i] on the South bank.
Your task is to return the maximum number of bridges that can be built such that no two bridges cross each other.
Two bridges cross if their endpoints appear in opposite orders on the two banks.
Example 1
Input:
n = 4
north = [1, 2, 3, 4]
south = [4, 3, 1, 2]

Output:
2

Explanation: You can build bridges C and D, connecting (3, 1) and (4, 2), without crossing. No selection of three bridges can avoid crossings.

Constraints
- 1 <= n <= 100000
- north.length == south.length == n
- All North-bank coordinates are distinct.
- All South-bank coordinates are distinct.
- Every bridge connects its corresponding pair of coordinates.
 */

public class BuildingBridges {

    //Time: (O(n^2)) — nested loops for LIS DP.
    //Space: (O(n)) — bridge list and DP array.
    public int maxBridges(int[] north, int[] south)
    {
        /*
        Hint:-
        Sort bridges by north ascending, then find the LIS of their corresponding south coordinates.
        Why? Once North-bank positions are ordered, bridges won't cross only if their South-bank positions are also in increasing order.
         */

        List<Bridge> bridges = new ArrayList<>();
        int n = north.length;

        for(int i=0; i<n; i++){
            bridges.add(new Bridge(north[i], south[i]));
        }

        //sort north coordinates in asc order
        bridges.sort( (a, b) -> Integer.compare(a.northX, b.northX) );

        //LIS on south
        int[] dp = new int[n];
        Arrays.fill(dp, 1); //base case, every bridge can form a stack of one bridge by itself.

        int maxNonOverlappingBridges = 0;

        for(int i=0; i<n; i++){
            Bridge curr = bridges.get(i);
            for(int j=0; j<i; j++){
                Bridge prev = bridges.get(j);

                if(prev.southX < curr.southX){
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }

            maxNonOverlappingBridges = Math.max(maxNonOverlappingBridges, dp[i]);
        }

        return maxNonOverlappingBridges;

        /*
        Why it works: Since bridges are sorted by North coordinate, a previous bridge can extend the current sequence only when its South coordinate is also smaller.
        That ensures the selected bridges don't cross.
         */
    }

    static class Bridge{
        int northX;
        int southX;

        public Bridge(int northX, int southX) {
            this.northX = northX;
            this.southX = southX;
        }
    }
}
