package recursion;

import java.util.ArrayList;
import java.util.List;

public class AllSubsets {

    //https://leetcode.com/problems/subsets/description/

    /*
    Time Complexity
    There are 2^n subsets.
    Copying each subset takes O(n) in the worst case.

    Time: O(n × 2^n)

    Auxiliary Space: O(n) (recursion stack + current subset)

    Output Space: O(n × 2^n)
     */

    public List<List<Integer>> subsets(int[] nums)
    {
        List<List<Integer>> subsets = new ArrayList<>();
        generateAllSubsets(nums, 0, new ArrayList<>(), subsets);
        return subsets;
    }

    public static void generateAllSubsets(int[] arr, int index, List<Integer> subset, List<List<Integer>> subsets)
    {
        if(index == arr.length){
            subsets.add(new ArrayList<>(subset));
            return;
        }

        //pick
        subset.add(arr[index]);
        generateAllSubsets(arr, index+1, subset, subsets);
        subset.removeLast(); //backtrack

        //don't pick
        generateAllSubsets(arr, index+1, subset, subsets);
    }
}
