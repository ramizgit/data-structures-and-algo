package binarySearch;

public class MinNumOfDaysToMakemBouquets {

    public static void main(String[] args)
    {
        System.out.println(min(new int[]{1,10,3,10,2}, 3, 1)); //3
        System.out.println(min(new int[]{1,10,3,10,2}, 3, 2)); //-1
        System.out.println(min(new int[]{7,7,7,7,12,7,7}, 2, 3)); //12
    }

    private static int min(int[] bloomDay, int m, int k)
    {
        if(m * k > bloomDay.length)
        {
            return -1;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int num : bloomDay){
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int left = min;
        int right = max;
        int answer = Integer.MAX_VALUE;

        while(left <= right) {
            int mid =  left + (right - left) / 2;

            if(canMakeBouquet(bloomDay, m, k, mid)){ //check feasibility
                answer = mid; //possible answer
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return answer;
    }

    private static boolean canMakeBouquet(int[] bloomDay, int m, int k, int days)
    {
        //bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
        int flowers = 0, bouquets = 0;

        for (int d : bloomDay) {
            if (d <= days) {
                flowers++;
                if (flowers == k) {
                    bouquets++;
                    if (bouquets == m) {
                        return true;
                    }
                    flowers = 0; //reset for next bouquet
                }
            } else {
                flowers = 0; //reset if adjacent condition not met
            }
        }

        return false;
    }
}
