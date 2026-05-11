package graph.unionfindDSU;

import java.util.*;

public class EarliestMomentWhenEveryoneBecomeFriends {

    //https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/description/

    public int earliestAcq(int[][] logs, int n)
    {
        //sort by timestamp asc order
        Arrays.sort(logs, (a, b) -> a[0] - b[0]); //O(m log m)

        UnionFind uf = new UnionFind(n);
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
