package prefixSum;

public class KRadiusSubArrAverage {
    //https://leetcode.com/problems/k-radius-subarray-averages/description/

    public int[] getAverages(int[] nums, int k)
    {
        int n = nums.length;

        int[] radius = new int[n];
        for(int i=0; i<n; i++){
            radius[i] = -1; //default
        }

        int window = 2 * k + 1;
        if (window > n) {
            return radius;
        }

        long[] prefix = new long[n];
        prefix[0] = nums[0];
        for(int i=1; i<n; i++){
            prefix[i] = prefix[i-1] + nums[i];
        }

        for(int i=k; i<n-k; i++){
            int left = i - k;
            int right = i + k;

            long sum = prefix[right] - prefix[left] + nums[left];
            radius[i] = (int) (sum / window);
        }

        return radius;
    }
}
