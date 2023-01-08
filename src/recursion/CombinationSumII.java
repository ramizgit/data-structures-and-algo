package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CombinationSumII {

    public static void main(String[] args)
    {
        int[] arr = {10,1,2,7,6,1,5};

        //with duplicates
        //sumWithDupes(arr, 8, 0, 0, new ArrayList<>());

        //without duplicates
        Arrays.sort(arr);
        sumWithoutDupes(arr, 8, 0, 0, new ArrayList<>());


    }

    public static void sumWithoutDupes(int[] arr, int target, int current, int index, List<Integer> result)
    {
        if(current > target){
            return;
        }

        if(current == target){
            System.out.println(result);
            return;
        }

        int prev = -1;

        for(int i=index; i<arr.length; i++){
            if(arr[i] == prev){
                continue;
            }

            result.add(arr[i]);
            sumWithoutDupes(arr, target, current + arr[i], i+1, result);
            result.remove(result.size()-1);

            prev = arr[i];
        }
    }

    public static void sumWithDupes(int[] arr, int target, int current, int index, List<Integer> result)
    {
        if(current > target){
            return;
        }

        if(current == target){
            System.out.println(result);
            return;
        }

        if(index >= arr.length){
            return;
        }

        //pick current element
        result.add(arr[index]);
        sumWithDupes(arr, target, current + arr[index], index+1, result);
        result.remove(result.size()-1);

        //not pick current element, just move pointer
        sumWithDupes(arr, target, current, index+1, result);
    }
}
