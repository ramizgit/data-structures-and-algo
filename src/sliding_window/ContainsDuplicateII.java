package slidingwindow;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicateII {
    //https://leetcode.com/problems/contains-duplicate-ii/description/

    public static void main(String[] args)
    {
        System.out.println(containsNearbyDuplicate(new int[]{1,2,3,1}, 3)); //true
        System.out.println(containsNearbyDuplicate(new int[]{1,0,1,1}, 1)); //true
        System.out.println(containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 2)); //false
    }

    private static boolean containsNearbyDuplicate(int[] nums, int k)
    {
        Set<Integer> window = new HashSet<>();
        int left = 0;

        for(int i=0; i<nums.length; i++){
            if(i > k){
                window.remove(nums[left++]);
                //window.remove(nums[i - k -1]); ------//COULD ALSO BE DONE THIS LIKE THIS, LEFT WONT BE NEEDED
            }

            if(!window.add(nums[i])){
                return true;
            }
        }

        return false;
    }
}
