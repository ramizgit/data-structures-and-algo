package recursion;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumI {
    public static void main(String[] args)
    {
        sum(new int[]{2,3,6,7}, 7, 0, 0, new ArrayList<>());
        System.out.println("-------------");
        sum(new int[]{2,3,5}, 8, 0, 0, new ArrayList<>());
        System.out.println("-------------");
        sum(new int[]{2}, 1, 0, 0, new ArrayList<>());
    }

    public static void sum(int[] arr, int target, int current, int index, List<Integer> result)
    {
        if(index == arr.length || current > target){
            return;
        }

        if(current == target){
            System.out.println(result);
            return;
        }

        //pick current element, and continue wth same index as same element can be repeated as many times as possible
        result.add(arr[index]);
        sum(arr, target, current + arr[index], index, result);
        result.remove(result.size()-1);

        //not pick current element, just move pointer
        sum(arr, target, current, index+1, result);

    }
}
