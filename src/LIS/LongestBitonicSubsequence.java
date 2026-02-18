package longestIncreasingSubseqVariants;

import java.util.*;

public class LongestBitonicSubsequence {

    public static void main(String[] args)
    {

        int[] nums = {1, 11, 2, 10, 4, 5, 2, 1};

        System.out.println(longestBitonicSubsequence(nums));
        // Expected: 6  → [1, 2, 10, 5, 2, 1]
    }

    public static int longestBitonicSubsequence(int[] nums)
    {
        int n = nums.length;
        int[] lis = new int[n];
        int[] lds = new int[n];

        //base case
        Arrays.fill(lis, 1);
        Arrays.fill(lds, 1);

        //left to right, populate lis
        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                if(nums[j] < nums[i]){
                    lis[i] = Math.max(lis[i], 1 + lis[j]);
                }
            }
        }

        //right to left, populate lds
        for(int i=n-1; i>=0; i--){
            for(int j=n-1; j>i; j--){
                if(nums[j] < nums[i]){
                    lds[i] = Math.max(lds[i], 1 + lds[j]);
                }
            }
        }

        int maxBitonic = 0;
        for(int i=0; i<n; i++) {
            int len = lis[i] + lds[i] - 1;
            maxBitonic = Math.max(maxBitonic, len);
        }

        return maxBitonic;
    }
}
