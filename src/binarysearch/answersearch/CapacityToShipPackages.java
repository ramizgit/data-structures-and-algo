package binarysearch.answersearch;

public class CapacityToShipPackages {

    //https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/

    private static int leastCapacity(int[] weights, int days)
    {
        int maxWeight = 0;
        int totalWeight = 0;

        for(int weight : weights){
            maxWeight = Math.max(maxWeight, weight);
            totalWeight += weight;
        }

        int low = maxWeight; //min possible ship capacity, to carry the heaviest package
        int high = totalWeight; //max possible ship capacity, to carry all packages in one day

        int answer = 0;

        while(low <= high){

            int midCapacity = low + (high - low)/2;

            //check whether this capacity can ship all packages within the given number of days
            if(canShip(weights, days, midCapacity)){
                answer = midCapacity; //potential answer
                high = midCapacity - 1; //try lower to minimize ship capacity
            }else{
                low = midCapacity + 1; //cant ship, try higher ship capacity
            }
        }

        return answer;
    }

    private static boolean canShip(int[] weights, int days, int shipCapacity)
    {
        int totalDays = 1;
        int currLoad = 0;

        for(int weight : weights){
            currLoad += weight;

            if(currLoad > shipCapacity){
                //exceeds ship capacity, start a new day
                totalDays++;
                currLoad = weight; //reset
            }

            //early exit
            if (totalDays > days) {
                return false;
            }
        }

        return true;
    }
}
