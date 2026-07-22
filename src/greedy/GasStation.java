package greedy;

//todo : practice

public class GasStation {

    // https://leetcode.com/problems/gas-station/

    /*
    Greedy Idea:

    1. At each station, calculate the net gain:
       gain = gas[i] - cost[i].

    2. Traverse the stations while maintaining:
       - tank  : current fuel from the current candidate start.
       - total : total fuel surplus/deficit over the entire circuit.
       - start : current candidate starting station.

    3. If tank becomes negative at station i:
       - We cannot reach station i + 1 from the current start.
       - None of the stations between 'start' and 'i' can be a valid start.
       - Therefore, set start = i + 1 and reset tank = 0.

    4. After processing all stations:
       - If total < 0, completing the circuit is impossible.
       - Otherwise, 'start' is the unique valid answer.

    Time Complexity: O(n)
    Space Complexity: O(1)
    */

    public int canCompleteCircuit(int[] gas, int[] cost) {

        int start = 0;
        int tank = 0;
        int total = 0;

        for (int i = 0; i < gas.length; i++) {

            int gain = gas[i] - cost[i];

            tank += gain;
            total += gain;

            // Current candidate cannot reach the next station.
            if (tank < 0) {
                start = i + 1;
                tank = 0; //reset tank
            }
        }

        return total >= 0 ? start : -1;
    }
}