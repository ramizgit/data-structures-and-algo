package binarysearch;

package neetcode150.binarySearch;

public class CapacityToShipPackages {

    public static void main(String[] args)
    {
        System.out.println(leastCapacity(new int[]{1,2,3,4,5,6,7,8,9,10}, 5)); //15
        System.out.println(leastCapacity(new int[]{3,2,2,4,1,4}, 3)); //6
        System.out.println(leastCapacity(new int[]{1,2,3,1,1}, 4)); //3
    }

    private static int leastCapacity(int[] weights, int days)
    {
        int minCapacity = 0;
        int maxCapacity = 0;

        for(int w : weights){
            minCapacity = Math.max(minCapacity, w);
            maxCapacity += w;
        }

        int answer = 0;

        while(minCapacity <= maxCapacity){
            int midCapacity = minCapacity + (maxCapacity - minCapacity)/2;

            if(canShip(weights, days, midCapacity)){
                answer = midCapacity; //potentail answer
                maxCapacity = midCapacity - 1;
            }else{
                minCapacity = midCapacity + 1;
            }
        }

        return answer;
    }

    private static boolean canShip(int[] weights, int days, int capacity)
    {
        int totalDays = 1;
        int totalWeight = 0;

        for(int w : weights){
            totalWeight += w;

            if(totalWeight > capacity){
                totalDays++;
                totalWeight = w; //reset
            }
        }

        return totalDays <= days;
    }
}
