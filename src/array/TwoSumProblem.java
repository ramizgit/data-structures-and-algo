package array;

import java.util.HashMap;
import java.util.Map;

public class TwoSumProblem {
    public static void main(String[] args)
    {
        printTwoSum(new int[]{2,7,11,15}, 9);//0 1
        printTwoSum(new int[]{3,2,4}, 6);//1 2
        printTwoSum(new int[]{3,3}, 6);//0 1
    }

    public static void printTwoSum(int[] arr, int target)
    {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<arr.length; i++)
        {
            if(map.containsKey(target-arr[i])){
                System.out.println(map.get(target-arr[i])+" "+i);
            }else{
                map.put(arr[i], i);
            }
        }
    }
}
