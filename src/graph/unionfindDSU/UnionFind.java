package graph.unionfindDSU;

public class UnionFind {

    int[] parent;

    public UnionFind(int n) {
        this.parent = new int[n];

        for(int i=0; i<n; i++){
            this.parent[i] = i;
        }
    }

    public int find(int x)
    {
        if(parent[x] != x){
            parent[x] = find(parent[x]); //path compression
         }

        return parent[x]; //returns top most parent
    }

    public boolean union(int x, int y)
    {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY){
            return false; //detects cycle
        }

        parent[rootY] = rootX;

        return true;
    }
}
