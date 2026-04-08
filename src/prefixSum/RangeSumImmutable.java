package prefixSum;

//https://leetcode.com/problems/range-sum-query-immutable/
public class RangeSumImmutable {

    int[] prefixSum;

    public RangeSumImmutable(int[] nums)
    {
        int n = nums.length;
        this.prefixSum = new int[n];

        //populate
        this.prefixSum[0] = nums[0];
        for(int i=1; i<n; i++){
            this.prefixSum[i] = nums[i] + this.prefixSum[i-1];
        }
    }

    public int sumRange(int left, int right)
    {
        if(left == 0){
            return this.prefixSum[right];
        }else{
            return this.prefixSum[right] - this.prefixSum[left - 1];
        }
    }
}
