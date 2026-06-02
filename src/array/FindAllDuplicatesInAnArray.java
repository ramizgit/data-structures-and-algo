package consistenthashing.array;

import java.util.*;

/*
Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each integer appears at most twice,
return an array of all the integers that appears twice.
You must write an algorithm that runs in O(n) time and uses only constant auxiliary space, excluding the space needed to store the output
 */

public class FindAllDuplicatesInAnArray {

    //https://leetcode.com/problems/find-all-duplicates-in-an-array/

    public List<Integer> findDuplicates(int[] nums)
    {
        //input validation
        if(nums == null || nums.length == 0){
            return Collections.emptyList();
        }

        int n = nums.length;
        List<Integer> duplicates = new ArrayList<>();


        for(int i=0; i<n; i++){

            //current num will act as index
            int index = Math.abs(nums[i]) - 1; //since array is zero based indexing but nums are in the range [1, n], hence -1

            if(nums[index] < 0){
                duplicates.add(Math.abs(nums[i])); //dupe found
            }else{
                nums[index] = -nums[index]; //mark num as -ve to keep track of visited num
            }
        }

        return duplicates;
    }
}
