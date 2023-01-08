package array;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args)
    {
        List<List<Integer>> output = new ArrayList<>();
        findCombinationSum(0, 0, 7, new ArrayList<>(), new int[]{2,3,6,7}, output);
        System.out.println(":::::::::::::::::::");
        findCombinationSum(0, 0, 8, new ArrayList<>(), new int[]{2,3,5}, output);
        System.out.println(":::::::::::::::::::");
        findCombinationSum(0, 0, 1, new ArrayList<>(), new int[]{2}, output);
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

        list.add(arr[index]);
        findCombinationSum(index, sum+arr[index], target, list, arr, output);
        list.remove(list.size()-1);
        findCombinationSum(index+1, sum, target, list, arr, output);
    }
}
