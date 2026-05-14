package array;

import java.util.*;

public class LargestNumber {

    //https://leetcode.com/problems/largest-number/description/

    private static String largestNumber(int[] nums)
    {
        if(nums == null || nums.length == 0){
            return "";
        }

        int n = nums.length;

        String[] numsStr = new String[n];
        for(int i=0; i<n; i++){
            numsStr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(numsStr, (a, b) -> (b+a).compareTo(a+b));

        StringBuilder sb = new StringBuilder();
        for(String s : numsStr){
            sb.append(s);
        }

        if(sb.charAt(0) == '0'){
            return "0";
        }

        return sb.toString();
    }
}
