package graph.unionfindDSU;

public class UnionFind {

    int[] parent;
    int[] size;

    public UnionFind(int n) {
        this.parent = new int[n];
        this.size = new int[n];

        for(int i=0; i<n; i++){ //Time : O(n)
            this.parent[i] = i;
            this.size[i] = i;
        }
    }

    //Time : O(α(n)) amortized → ~O(1)
    public int find(int x)
    {
        if(parent[x] != x){
            parent[x] = find(parent[x]); //path compression:directly connect node to root to keep tree flat efficient find() operations
        }

        return parent[x]; //returns top most parent
    }

    //Time : O(α(n)) amortized → ~O(1)
    public boolean union(int x, int y)
    {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY){
            return false; //detects cycle
        }

        // Union by size: attach the smaller component under the larger one
        // This keeps the tree shallow (avoids skewed trees) and ensures efficient find() operations
        if (size[rootY] < size[rootX]) {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        } else {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        }

        return true;
    }
}
