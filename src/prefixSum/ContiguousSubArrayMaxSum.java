package array;

import java.util.HashMap;
import java.util.Map;

public class ContiguousSubArrayMaxSum {

    //hint : https://leetcode.com/discuss/post/6808476/google-e4-telephonic-round-by-acharyarik-ujm0/

    /*
    Find i and j such that sum of Contiguous array is maximised.

    a[i] = a[j] where j > i
    Maximise (a[i] + a[i+1] + . . . . + a[j])
     */

    public static void main(String[] args)
    {
        maxSum(new int[]{3, 2, 1, 2, 4, 3, 5}); //start = 0 end = 5, maxSum = 15
        maxSum(new int[]{3, 2, 1, 2, 4, 3, 5,10,10,10}); //start = 7 end = 9, maxSum = 30
    }

    private static void maxSum(int[] arr)
    {
        Map<Integer, IdxSumPair> prefixSum = new HashMap<>();
        int runningSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;


        for(int i=0; i<arr.length; i++){
            runningSum += arr[i];

            if(!prefixSum.containsKey(arr[i])){
                prefixSum.put(arr[i], new IdxSumPair(i, runningSum));
            }else {
                int tmpSum = runningSum - prefixSum.get(arr[i]).sum + arr[i];
                if(maxSum < tmpSum){
                    //reset
                    maxSum = tmpSum;
                    start = prefixSum.get(arr[i]).idx;
                    end = i;
                }
            }
        }

        System.out.println("maxSum : "+ maxSum);
        System.out.println("start = " + start + " end = " + end);
    }
}

class IdxSumPair{
    int idx;
    int sum;

    public IdxSumPair(int idx, int sum) {
        this.idx = idx;
        this.sum = sum;
    }
}

