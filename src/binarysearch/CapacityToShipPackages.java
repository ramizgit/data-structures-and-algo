package binarysearch;

public class CapacityToShipPackages {

    //https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/

    public static void main(String[] args)
    {
        // weights = [3,2,2,4,1,4], days = 3
        System.out.println(leastCapacity(new int[]{1,2,3,4,5,6,7,8,9,10}, 5)); //15
        System.out.println(leastCapacity(new int[]{3,2,2,4,1,4}, 3)); //6
        System.out.println(leastCapacity(new int[]{1,2,3,1,1}, 4)); //3
    }

    private static int leastCapacity(int[] weights, int days)
    {

        int max = 0;
        int sum = 0;

        for(int weight : weights){
            sum += weight;
            max = Math.max(max, weight);
        }

        int low = max;
        int high = sum;
        int result = 0;

        while (low <= high){
            int capacity = low + (high - low)/2;
            int numOfDays = numOfDaysToShip(weights, capacity);

            if(numOfDays == days){
                return capacity;
            } else if (numOfDays < days) {
                high = capacity - 1;
                result = capacity; //could be potential answer
            }else {
                low = capacity + 1;
            }
        }

        return result;
    }

    private static int numOfDaysToShip(int[] weights, int capacity)
    {
        int numOfDays = 1;
        int sum = 0;
        for(int i=0; i< weights.length; i++){
            sum += weights[i];

            //reset if exceeds capacity
            if(sum > capacity){
                sum = weights[i];
                numOfDays++;
            }
        }

        return numOfDays;
    }
}

