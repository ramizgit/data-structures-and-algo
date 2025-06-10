package binarysearch;

public class KokoEatingBananas {
    //https://leetcode.com/problems/koko-eating-bananas/description/

    public static void main(String[] args)
    {
        System.out.println(minEatingSpeed(new int[]{3,6,7,11}, 8)); //4
        System.out.println(minEatingSpeed(new int[]{30,11,23,4,20}, 5)); //30
        System.out.println(minEatingSpeed(new int[]{30,11,23,4,20}, 6)); //23
        System.out.println(minEatingSpeed(new int[]{1000}, 1000)); //1
    }

    private static int minEatingSpeed(int[] piles, int h)
    {
        int min = 1;
        int max = Integer.MIN_VALUE;
        int result = 0;

        for(int pile : piles){
            max = Math.max(max, pile);
        }

        while (min <= max){
            int speed = min + (max - min)/2;

            int timeToFinish = timeToFinish(piles, speed);

            if(timeToFinish == h){
                return speed;
            } else if (timeToFinish < h) {
                max = speed - 1;
                result = speed; //potential answer, lets see if we can do better, otherwise this will be the answer
            }else {
                min = speed + 1;
            }
        }

        return result;
    }

    private static int timeToFinish(int[] piles, int speed)
    {
        int time = 0;
        for(int i=0; i< piles.length; i++){
            time += Math.ceilDiv(piles[i], speed);
        }
        return time;
    }
}

