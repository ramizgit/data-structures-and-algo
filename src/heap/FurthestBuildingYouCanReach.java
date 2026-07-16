package heap;

/*
You are given an array heights, where heights[i] is the height of the i-th building.

You start at building 0.

To move from building i to i + 1:

If heights[i+1] <= heights[i], you can move for free.
Otherwise, you must climb the difference:
Use bricks, or
Use one ladder (which can cover any height difference).

Return the furthest building index you can reach.

Method Signature
public int furthestBuilding(int[] heights, int bricks, int ladders)
Example 1
heights = [4,2,7,6,9,14,12]

bricks = 5

ladders = 1

Output:

4
Example 2
heights = [4,12,2,7,3,18,20,3,19]

bricks = 10

ladders = 2

Output:

7
Constraints
1 <= heights.length <= 100000

1 <= heights[i] <= 1000000

0 <= bricks <= 1000000000

0 <= ladders <= heights.length
 */

public class FurthestBuildingYouCanReach {

    /*
    maxHeap = empty

for i = 0 to heights.length - 2:

    diff = heights[i+1] - heights[i]

    if diff <= 0:
        continue

    // use bricks
    bricks -= diff

    // remember this climb
    maxHeap.add(diff)

    // ran out of bricks
    if (bricks < 0) {

        if (ladders == 0)
            return i;   // cannot move from i -> i+1

        // convert the largest brick climb into a ladder
        largest = maxHeap.poll()

        bricks += largest

        ladders--
    }

// reached every building
return heights.length - 1;
     */

    //todo : implement

    public int furthestBuilding(int[] heights, int bricks, int ladders)
    {
        return 0;
    }
}
