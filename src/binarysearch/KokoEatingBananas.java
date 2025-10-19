package binarysearch;

package neetcode150.binarySearch;

public class KokoEatingBanana {
    public static void main(String[] args)
    {
        System.out.println(minEatingSpeed(new int[]{1,4,3,2}, 9)); //2
        System.out.println(minEatingSpeed(new int[]{25,10,23,4}, 4)); //2
    }

    private static int minEatingSpeed(int[] piles, int h)
    {
        int minSpeed = 1;
        int maxSpeed = 0;
        for(int pile : piles){
            maxSpeed = Math.max(maxSpeed, pile);
        }

        int answer = 0;

        while(minSpeed <= maxSpeed){
            int midSpeed = minSpeed + (maxSpeed - minSpeed)/2;

            if(canFinish(piles, midSpeed, h)){
                answer = midSpeed; //potential answer
                maxSpeed = midSpeed - 1;
            }else{
                minSpeed = midSpeed + 1;
            }
        }

        return answer;
    }

    private static boolean canFinish(int[] piles, int speed, int h)
    {
        int hour = 0;
        for(int pile : piles){
            hour += Math.ceilDiv(pile, speed);
        }

        return hour <= h;
    }
}
