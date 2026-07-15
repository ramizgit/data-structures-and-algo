package recursion;

import java.util.*;

public class AllSubSetSums {

    //Time: O(2^n)
    public List<Integer> generateAllSubSetSums(int[] arr)
    {
        List<Integer> result = new ArrayList<>();

        dfs(arr, 0, 0, result);

        return result;
    }

    private void dfs(int[] arr, int index, int sum, List<Integer> result)
    {
        //recursion end condition
        if(index == arr.length){
            result.add(sum);
            return;
        }

        //pick current num
        dfs(arr, index+1, sum + arr[index], result);

        //don't pick current num
        dfs(arr, index+1, sum, result);
    }
}
