package arrays.allElementExceptCurr;

import java.util.*;

/*
Given a list of sets, you need to remove one set to maximise the intersection of remaining sets.

Eg.
List_of_set = [
[2,3,4,5],
[2,4,3,8],
[1,4,9,6]
]

Current intersection = [4]
After removal of set at index 2
intersection is [2,3,4]

so answer is 2
 */

public class MaximizeIntersectionAfterRemovingOneSet {

    public static int findIndexToRemove(List<Set<Integer>> sets)
    {
        int n = sets.size();

        List<Set<Integer>> prefix = new ArrayList<>();
        List<Set<Integer>> suffix = new ArrayList<>();

        // initialize
        for(int i = 0; i < n; i++){
            prefix.add(new HashSet<>());
            suffix.add(new HashSet<>());
        }

        // prefix intersection
        prefix.set(0, new HashSet<>(sets.get(0)));

        for(int i = 1; i < n; i++)
        {
            Set<Integer> curr = new HashSet<>(prefix.get(i - 1));

            curr.retainAll(sets.get(i));

            prefix.set(i, curr);
        }

        // suffix intersection
        suffix.set(n - 1, new HashSet<>(sets.get(n - 1)));

        for(int i = n - 2; i >= 0; i--)
        {
            Set<Integer> curr = new HashSet<>(suffix.get(i + 1));

            curr.retainAll(sets.get(i));

            suffix.set(i, curr);
        }

        int bestIndex = -1;
        int maxSize = -1;

        for(int i = 0; i < n; i++)
        {
            Set<Integer> intersection;

            // remove first
            if(i == 0){

                intersection = new HashSet<>(suffix.get(1));

            }

            // remove last
            else if(i == n - 1){

                intersection = new HashSet<>(prefix.get(n - 2));

            }

            // remove middle
            else{

                intersection = new HashSet<>(prefix.get(i - 1));

                intersection.retainAll(suffix.get(i + 1));
            }

            if(intersection.size() > maxSize){
                maxSize = intersection.size();
                bestIndex = i;
            }
        }

        return bestIndex;
    }
}
