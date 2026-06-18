package consistenthashing.differenceArray;

/*
You are given an array arr of size n and q queries.

Each query updates all elements from index l to index r to value k.

Return the final state of the array after processing all queries in order.

There are two variations:
All queries use the same value k.
Different queries may use different values k.
Method Signatures
Same k for all queries
List<Integer> updateArrayWithSameK(List<Integer> arr, List<String> ranges, int k)
arr is the initial array.
Each string in ranges is in the format "l,r".
For every range, update all indices from l to r to the same value k.
Return the final state of the array after all updates.
Different k for different queries
List<Integer> updateArray(List<Integer> arr, List<String> queries)
arr is the initial array.
Each string in queries is in the format "l,r,k".
For each query, update all indices from l to r to value k.
Return the final state of the array after all queries are processed in order.
Constraints
1 ≤ n ≤ 10^5
1 ≤ q ≤ 10^5
0 ≤ l ≤ r < n
arr.size() = n
All queries are valid.
Process queries in the given order.
You must never use null as a parameter value.
Notes
If multiple queries update the same index, the later query overwrites the earlier value.
The final array depends on the order of the queries.
For the same k variation, every update uses the exact same value k.
For the different k variation, each query may use its own value k.
 */

import java.util.List;

public class ArrayRangeUpdateQueries {

    //https://codezym.com/question/148

    int[] updateArrayWithSameK(int[] arr, String[] ranges, int k)
    {
        int n = arr.length;

        //diff array
        int[] diff = new int[n+1];

        for(String range : ranges){

            String[] entry = range.split(",");
            int start = Integer.parseInt(entry[0]);
            int end = Integer.parseInt(entry[1]);

            diff[start] += 1;
            diff[end + 1] -= 1;
        }

        int[] result = new int[n];
        int updatedCount = 0;

        for(int i=0; i<n; i++){
            updatedCount += diff[i];

            if(updatedCount > 0){
                result[i] = k;
            }else{
                result[i] = arr[i];
            }
        }

        return result;
    }
}
