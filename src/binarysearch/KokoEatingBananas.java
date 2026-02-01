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
        int low = 1;
        int high = 0;
        int answer = 0;

        for(int pile : piles){
            high = Math.max(high, pile);
        }

        while (low <= high){
            int mid = low + (high - low)/2;

            if (canFinish(piles, mid, h)) {
                answer = mid; //possible answer
                high = mid - 1; //slow down as koko able to eat all banana within h
            }else {
                low = mid + 1; //hurry up
            }
        }

        return answer;
    }

    private static boolean canFinish(int[] piles, int speed, int h)
    {
        int time = 0;
        for (int pile : piles) {
            time += Math.ceilDiv(pile, speed);

            if (time > h) {
                return false;
            }
        }
        return true;
    }
}
