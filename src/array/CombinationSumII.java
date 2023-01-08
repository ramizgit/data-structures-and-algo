package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
    public static void main(String[] args)
    {
        int[] input = {10,1,2,7,6,1,5};
        Arrays.sort(input);
        List<List<Integer>> output = new ArrayList<>();
        findCombinationSum(0, 0, 8, new ArrayList<>(), input, output);
        System.out.println(":::::::::::::::::::");
    }

    public static void findCombinationSum(int index, int sum, int target, List<Integer> list, int[] arr, List<List<Integer>> output)
    {
        if(sum == target){
            output.add(list);
            System.out.println(list);
            return;
        }

        if(sum > target || index > arr.length-1){
            return;
        }

        for(int i=index; i<arr.length; i++){
            if(i > index && arr[i] == arr[i-1]){
                continue;
            }
            if(arr[i] > target){
                break;
            }

            list.add(arr[i]);
            findCombinationSum(i+1, sum+arr[i], target, list, arr, output);
            list.remove(list.size()-1);
        }
    }
}
