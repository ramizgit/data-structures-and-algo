package binarySearch;

import java.util.Random;

//https://leetcode.com/problems/random-pick-with-weight/description/
/*
Think of it like:
each index owns some space
we throw a dart randomly
whichever space it lands in → that index
*/

public class RandomPickWithWeight {

    private int[] prefixSum;
    private Random random = new Random();

    public RandomPickWithWeight(int[] w)
    {
        this.prefixSum = new int[w.length];
        this.prefixSum[0] = w[0];

        for(int i=1; i<w.length; i++){
            this.prefixSum[i] = w[i] + this.prefixSum[i-1];
        }
    }

    public int pickIndex()
    {
        int total = prefixSum[prefixSum.length - 1];
        int rand = random.nextInt(total) + 1;

        //binary search
        int low = 0;
        int high = this.prefixSum.length-1;
        int answer = -1;

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(this.prefixSum[mid] >= rand){
                answer = mid; //possible answer
                high = mid -1; //try lower
            }else{
                low = mid + 1; //try higher
            }
        }

        return answer;
    }
}
