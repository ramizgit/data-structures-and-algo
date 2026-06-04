package array;

import java.util.*;

public class ThreeSum {

    /*
    Sorting:      O(n log n)
    Outer loop:   O(n)
    Two pointers: O(n) per iteration (amortized)

    Total:        O(n²)
    Space:        O(1) excluding output
     */
    public List<List<Integer>> threeSum(int[] nums, int target)
    {
        //input validation
        if(nums == null || nums.length < 3){
            return Collections.emptyList();
        }

        Arrays.sort(nums); //sort the array so that we can use two pointer technique efficiently

        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        for(int i=0; i<n-2; i++)
        {
            //skip duplicate first elements
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }

            int required = target - nums[i];

            //look for required sum using two pointer technique on the sorted array
            int left = i+1;
            int right = n-1;

            while(left < right){

                int current = nums[left] + nums[right];

                if(current == required){

                    result.add(Arrays.asList(nums[i], nums[left], nums[right])); //collect result

                    //skip duplicate second elements
                    while(left < right && nums[left] == nums[left+1]){
                        left++;
                    }

                    //skip duplicate third elements
                    while(left < right && nums[right] == nums[right-1]){
                        right--;
                    }

                    left++;
                    right--;

                }else if(current < required){
                    left++;
                }else {
                    right--;
                }
            }
        }

        return result;
    }
}
