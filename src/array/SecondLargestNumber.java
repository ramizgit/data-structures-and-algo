package array;

import java.util.*;

public class SecondLargestNumber {

    public static void main(String[] args)
    {
        System.out.println(secondLargestNumber(new int[]{3,30,34,5,9}));
    }

    private static String secondLargestNumber(int[] nums)
    {
        String[] arr = new String[nums.length];

        for(int i=0; i<nums.length; i++){
            arr[i] = String.valueOf(nums[i]);
        }

        Set<String> all = new TreeSet<>(Collections.reverseOrder());

        backtrack(arr, 0, all);

        int count = 0;

        for(String s : all){
            count++;
            if(count == 2){
                return s;
            }
        }

        return "";
    }

    private static void backtrack(String[] arr, int index, Set<String> all)
    {
        if(index == arr.length){
            StringBuilder sb = new StringBuilder();
            for(String s : arr){
                sb.append(s);
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

    private static void swap(String[] arr, int i, int j)
    {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
