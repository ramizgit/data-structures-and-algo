package graph.unionfindDSU;

public class SimilarStringGroups {

    //https://leetcode.com/problems/similar-string-groups/description/

    public int numSimilarGroups(String[] strs)
    {

        /*
        Approach:-
            Model each string as a node.
            Union two nodes if the corresponding strings are similar
            (identical or differ in exactly two positions).
            The number of remaining disjoint sets is the answer.
         */

        //input validation
        if(strs == null || strs.length == 0){
            return 0;
        }

        int n = strs.length;

        //initialize union find of length n
        UnionFind uf = new UnionFind(n);

        int groups = n; //initially place each string in separate group

        //try union every pair
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){

                if(similar(strs[i], strs[j])){
                    //if the words are similar, means an edge exists between them, hence try to union them
                    if(uf.union(i, j)){ //DSU implementation is almost always built on integer indices, not arbitrary objects like strings, hence using indices
                        groups--; //reduce groups count if union is successful
                    }
                }
            }
        }

        return groups;
    }

    private boolean similar(String s1, String s2)
    {
        int diff = 0;

        for(int i=0; i<s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
                diff++;

                if (diff > 2) {
                    return false;
                }
            }
        }

        return diff == 0 || diff == 2; //at most two char much differ
    }
}
