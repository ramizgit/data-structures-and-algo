package array;

import java.util.*;

public class LargestNumber {
    //https://leetcode.com/problems/largest-number/description/

    public static void main(String[] args)
    {
        System.out.println(largestNumber(new int[]{3,30,34,5,9}));
        System.out.println(largestNumber(new int[]{0,0,0,0}));
    }

    private static String largestNumber(int[] nums)
    {
        List<String> numList  = new ArrayList<>();
        for(int num : nums){
            numList.add(Integer.toString(num));
        }

        Collections.sort(numList, (a, b) -> (b+a).compareTo(a+b));

        StringBuilder sb = new StringBuilder();
        for(String n : numList){
            sb.append(n);
        }

        if(sb.charAt(0) == '0'){
            return "0";
        }

        return sb.toString();
    }
}
