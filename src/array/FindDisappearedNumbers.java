package array;

import java.util.*;

public class FindDisappearedNumbers {

    public static void main(String[] args)
    {
        System.out.println(findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1}));
        System.out.println(findDisappearedNumbers(new int[]{1,1,1,1,1,1,3,1}));
    }

    /*
   Time : O(n)
   Space : O(1)
    */
    private static List<Integer> findDisappearedNumbers(int[] nums)
    {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();

        for(int i=0; i<n; i++){
            int idx = Math.abs(nums[i]) - 1;
            if(nums[idx] > 0){
                nums[idx] = -1 * nums[idx];
            }
        }

        for(int i=0; i<n; i++){
            if(nums[i] > 0){
                result.add(i+1);
            }
        }

        return result;
    }



    /*
    Time : O(n)
    Space : O(n)
     */
    private static List<Integer> findDisappearedNumbers_v2(int[] nums)
    {
        int n = nums.length;

        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }

        List<Integer> result = new ArrayList<>();

        for(int i=1; i<=n; i++){
            if(!set.contains(i)){
                result.add(i);
            }
        }

        return result;
    }
}
