package arrays;

public class MaximumEnemyFortsThatCanBeCaptured {

    //https://leetcode.com/problems/maximum-enemy-forts-that-can-be-captured/

    public int captureForts(int[] forts)
    {
        //input validation
        if(forts == null || forts.length == 0){
            return 0;
        }

        int n = forts.length;
        int prevNonZeroIdx = -1;
        int maxForts = 0;

        for(int i=0; i<n; i++){

            //skip empty forts
            if(forts[i] == 0){
                continue;
            }

            //If previous non-zero fort exists and signs are opposite
            if(prevNonZeroIdx != -1 && forts[prevNonZeroIdx] != forts[i]){
                int numOfFortsCaptured = i - prevNonZeroIdx - 1; //number of forts BETWEEN i and prev idx
                maxForts = Math.max(maxForts, numOfFortsCaptured);
            }

            prevNonZeroIdx = i; //update prev non zero index to current
        }

        return maxForts;
    }
}
