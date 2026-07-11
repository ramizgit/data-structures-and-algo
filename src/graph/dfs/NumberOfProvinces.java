package consistenthashing.graph.dfs;

public class NumberOfProvinces {

    //https://leetcode.com/problems/number-of-provinces/description/

    //IMPORTANT : this is both DFS and DSU problem

    public int findCircleNum(int[][] isConnected)
    {
        //input validation
        if(isConnected == null || isConnected.length == 0){
            return 0;
        }

        int n = isConnected.length;

        //dfs logic
        boolean[] visited = new boolean[n];
        int count = 0;

        for(int city = 0; city < n; city++){
            if(!visited[city]){
                count++;
                dfs(city, isConnected, visited);
            }
        }

        return count;
    }

    private void dfs(int city, int[][] isConnected, boolean[] visited)
    {
        visited[city] = true;

        //explore neighbours
        /*
        This loop runs:n times per city
        and there are: n cities
        Therefore: n * n = O(n²)
         */
        for(int neighbour=0; neighbour < isConnected.length; neighbour++){
            if(isConnected[city][neighbour] == 1 && !visited[neighbour]){
                dfs(neighbour, isConnected, visited);
            }
        }
    }
}
