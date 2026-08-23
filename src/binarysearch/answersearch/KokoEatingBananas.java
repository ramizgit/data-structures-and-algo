package binarysearch.answersearch;

public class KokoEatingBananas {

    //https://leetcode.com/problems/koko-eating-bananas/description/

    /*
    Complexity:-

    Let M = max(piles) and n = piles.length.

    canFinish() → O(n)
    Binary search → O(log M)
    Total → O(n log M)
    Space → O(1)
     */
    private static int minEatingSpeed(int[] piles, int h)
    {
        int maxPile = 0;

        for(int pile : piles){
            maxPile = Math.max(maxPile, pile);
        }

        int low = 1; //slowest possible positive speed
        int high = maxPile; //fastest speed needed: every pile can be finished in 1 hour

        int answer = 0;

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

            //early exit
            if (time > h) {
                return false; //cant finish as guard has arrived
            }
        }

        return true;
    }
}
