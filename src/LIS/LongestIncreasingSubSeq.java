package longestIncreasingSubseqVariants;

import java.util.*;

public class LongestIncreasingSubSeq {

    public static void main(String[] args)
    {
        //nums = [0, 8, 4, 12, 2]

        //---------print just length of LIS
        System.out.println(longestIncreasingSubSeqViaDP(new int[]{0, 8, 4, 12, 2})); //3
        System.out.println(longestIncreasingSubSeqViaBinarySearch(new int[]{0, 8, 4, 12, 2})); //3

        //----------print actual LIS
        System.out.println(printLISViaDP(new int[]{0, 8, 4, 12, 2})); //[0, 8, 12]

        //---------min deletion to sort array
        System.out.println(minDeletionsToSortArr(new int[]{0, 8, 4, 12, 2}));
    }

    //Time : O(n^2)
    //Space : O(n)
    private static int longestIncreasingSubSeqViaDP(int[] nums)
    {
        int n = nums.length;
        int[] dp = new int[n]; //dp array
        Arrays.fill(dp, 1); //base case

        int maxLen = 1; //base case, as min 1 length seq is possible

        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    //Time : O(nlogn)
    //Space : O(n)
    private static int longestIncreasingSubSeqViaBinarySearch(int[] nums)
    {
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]); //base case

        for(int i=1; i<nums.length; i++){
            //do binary search on list
            int low = 0;
            int high = list.size()-1;

            while(low <= high){
                int mid = low + (high - low) / 2;
                if(nums[i] > list.get(mid)){
                    low = mid + 1;
                }else{
                    high = mid - 1;
                }
            }

            if(low == list.size()){
                list.add(nums[i]);
            }else{
                list.set(low, nums[i]);
            }
        }

        return list.size();
    }

    //Time : O(n^2)
    //Space : O(n)
    private static List<Integer> printLISViaDP(int[] nums)
    {
        int n = nums.length;
        int[] dp = new int[n]; //dp array to track max len
        int[] prev = new int[n]; //prev array to keep track of chain

        Arrays.fill(dp, 1); //base case, as each element itself is LIS
        Arrays.fill(prev, -1);

        int maxLen = 1; //base case, as min 1 length seq is possible
        int maxIdx = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                if(nums[j] < nums[i]){
                    if(dp[i] < 1 + dp[j]){
                        dp[i] = 1 + dp[j];
                        prev[i] = j;
                    }
                }
            }

            if(maxLen < dp[i]){
                maxLen = dp[i];
                maxIdx = i;
            }
        }

        // reconstruct LIS
        List<Integer> list = new ArrayList<>();

        int curr = maxIdx;
        while (curr != -1) {
            list.add(nums[curr]);
            curr = prev[curr];
        }

        Collections.reverse(list);
        return list;
    }

    private static int minDeletionsToSortArr(int[] nums)
    {
        return nums.length - longestIncreasingSubSeqViaBinarySearch(nums);
    }

}
