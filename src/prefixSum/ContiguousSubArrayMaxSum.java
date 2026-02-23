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

    /*
    hint : maintain a map of element to prefixSum till prev index
    */
    public static void main(String[] args)
    {
        maxSum(new int[]{3, 2, 1, 2, 4, 3, 5}); //start = 0 end = 5, maxSum = 15
        maxSum(new int[]{3, 2, 1, 2, 4, 3, 5,10,10,10}); //start = 7 end = 9, maxSum = 30
    }

        public static void maxSumSubarray(int[] arr)
    {
        Map<Integer, IdxPrefixPair> prefixMap = new HashMap<>();
        int maxSum = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        int currPrefix = 0;

        for(int i=0; i<arr.length; i++){
            if(prefixMap.containsKey(arr[i])){
                int sum = currPrefix - prefixMap.get(arr[i]).prefix + arr[i];
                if(sum > maxSum){
                    maxSum = sum;
                    start = prefixMap.get(arr[i]).idx;
                    end = i;
                }
            }

            if(!prefixMap.containsKey(arr[i]) || prefixMap.get(arr[i]).prefix > currPrefix){
                prefixMap.put(arr[i], new IdxPrefixPair(i, currPrefix));
            }

            currPrefix += arr[i];
        }

        System.out.println("max sum = " + maxSum + " start = " + start + " end = " + end);
    }
}

class IdxPrefixPair{
    int idx;
    int prefix;

    public IdxPrefixPair(int idx, int prefix) {
        this.idx = idx;
        this.prefix = prefix;
    }
}
