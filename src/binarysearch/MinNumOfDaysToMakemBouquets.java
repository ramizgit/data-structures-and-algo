package binarysearch;

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

        for(int i=0; i<bloomDay.length; i++){
            min = Math.min(min, bloomDay[i]);
            max = Math.max(max, bloomDay[i]);
        }

        int left = min;
        int right = max;
        int answer = Integer.MAX_VALUE;

        while(left <= right) {
            int mid =  left + (right - left) / 2;
            //check feasibility
            int numOfDays = numDays(bloomDay, m, k, mid);

            if(numOfDays >= m){
                answer = Math.min(answer, mid);
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return answer;
    }

    private static int numDays(int[] bloomDay, int m, int k, int days)
    {
        //bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
        int counter = 0;
        int num = 0;
        for(int i=0; i<bloomDay.length; i++){
            if(bloomDay[i] <= days){
                counter++;

                if(counter == k){
                    num++;
                    if (num >= m) {
                        return num; // early exit
                    }
                    //reset
                    counter = 0;
                }
            }else{
                //reset
                counter = 0;
            }
        }

        return num;
    }
}
