package arrays.twoDdominancequery;

import java.util.*;

public class QueueReconstructionByHeight {

    //https://leetcode.com/problems/queue-reconstruction-by-height/description/

    public int[][] reconstructQueue(int[][] people)
    {
        //sort people by descending height, if tie then ascending k
        Arrays.sort(people, (a, b) -> {
           if(a[0] == b[0]){
               return a[1] - b[1]; //asc k
           }

           return b[0] - a[0]; //desc height
        });

        //now move each person at index k
        List<int[]> result = new ArrayList<>();

        for(int i=0; i<people.length; i++){
            int index = people[i][1];
            result.add(index, people[i]);
        }

        return result.toArray(new int[people.length][]);
    }
}
