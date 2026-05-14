package arrays;

import java.util.*;

public class SecondLargestNumber {

    private static String secondLargestNumber(int[] nums)
    {
        Set<String> all = new TreeSet<>(Collections.reverseOrder());

        backtrack(nums, 0, all);

        int count = 0;

        for(String s : all){
            count++;
            if(count == 2){
                return s;
            }
        }

        return "";
    }

    private static void backtrack(int[] arr, int index, Set<String> all)
    {
        if(index == arr.length){
            StringBuilder sb = new StringBuilder();
            for(int num : arr){
                sb.append(num);
            }
            all.add(sb.toString());
            return;
        }

        for(int i=index; i<arr.length; i++){
            swap(arr, index, i);
            backtrack(arr, index + 1, all);
            swap(arr, index, i);
        }
    }

    private static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
