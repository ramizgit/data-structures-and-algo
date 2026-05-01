package google;

import java.util.HashMap;
import java.util.Map;

public class ContiguousSubArrayMaxSum {

    //hint : https://leetcode.com/discuss/post/6808476/google-e4-telephonic-round-by-acharyarik-ujm0/

    /*
    Find i and j such that sum of Contiguous array is maximised.

    a[i] = a[j] where j > i
    Maximise (a[i] + a[i+1] + . . . . + a[j])
     */

    /*
    hint : maintain a map of element to prefixSum till prev index
    */

    public static void maxSumSubarray(int[] arr)
    {
        Map<Integer, Pair> prefixMap = new HashMap<>();
        int maxSum = Integer.MIN_VALUE;
        int start = -1;
        int end = -1;
        int prefixSum = 0;

        for(int i=0; i<arr.length; i++){

            Pair prev = prefixMap.get(arr[i]);

            //Populate answer if condition  a[i] = a[j] where j > i is met
            if(prev != null){
                int sum = prefixSum - prev.prefix + arr[i]; //// sum from prev.idx to i using prefix sum
                if(sum > maxSum){
                    maxSum = sum;
                    start = prev.idx;
                    end = i;
                }
            }

            // Store best (minimum prefix) occurrence
            if(prev == null || prev.prefix > prefixSum){
                prefixMap.put(arr[i], new Pair(i, prefixSum));
            }

            prefixSum += arr[i];
        }

        System.out.println("max sum = " + maxSum + " start = " + start + " end = " + end);
    }
}

class Pair {
    int idx;
    int prefix;

    public Pair(int idx, int prefix) {
        this.idx = idx;
        this.prefix = prefix;
    }
}
