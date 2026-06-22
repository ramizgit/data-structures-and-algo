package graph.unionfindDSU;

import java.util.*;

public class EarliestMomentWhenEveryoneBecomeFriends {

    //https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/description/

    public int earliestAcq(int[][] logs, int n)
    {
        /*
        Approach:-
        Sort logs by timestamp.
        Initially every person is its own component.
        Process friendships in chronological order.
        Whenever two different components merge, decrement components.
        As soon as only one component remains, return the current timestamp.
         */
        //sort by timestamp asc order
        Arrays.sort(logs, (a, b) -> a[0] - b[0]); //O(m log m)

        graph.unionfindDSU.UnionFind uf = new graph.unionfindDSU.UnionFind(n);
        int components = n; //start with n isolated person for each n nodes

        for(int[] log : logs){
            if(uf.union(log[1], log[2])){ //O(α(n)) ~ O(1)
                components--;
            }

            if(components == 1){
                return log[0]; //everyone becomes acquainted
            }
        }

        return -1;
    }
}
