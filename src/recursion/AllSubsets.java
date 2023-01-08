package recursion;

import java.util.ArrayList;
import java.util.List;

public class AllSubsets {
    public static void main(String[] args)
    {
        printAllSubsets(new int[]{1,2,3}, 0, new ArrayList<>());
    }

    public static void printAllSubsets(int[] arr, int index, List<Integer> result)
    {
        if(index == arr.length){
            System.out.println(result);
            return;
        }

        //pick
        result.add(arr[index]);
        printAllSubsets(arr, index+1, result);
        result.remove(result.size()-1);

        //not pick
        printAllSubsets(arr, index+1, result);
    }
}
