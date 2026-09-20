package graph.unionfindDSU;

/*
You are given n cities numbered from 0 to n - 1.

You are also given an array roads, where:

roads[i] = [u, v, year]

This means there is a bidirectional road connecting cities u and v, and this road became available in year.

You are given an array queries, where:

queries[i] = [a, b, year]

For each query, determine whether city a can reach city b using only roads whose availability year is less than or equal to year.

Return an array of boolean values where answer[i] corresponds to queries[i].

Example
Input:
n = 5

roads = [
    [0, 1, 2000],
    [1, 2, 2005],
    [3, 4, 2003],
    [2, 3, 2010]
]

queries = [
    [0, 2, 2004],
    [0, 2, 2005],
    [0, 4, 2010],
    [3, 4, 2002]
]

Output:
[false, true, true, false]
Constraints
1 <= n <= 200,000
0 <= roads.length <= 300,000
0 <= queries.length <= 300,000

0 <= u, v, a, b < n
0 <= year <= 10^9

Assume roads and queries are not necessarily sorted by year.
 */

public class TimeRestrictedConnectivity {

    //todo : implement

    public boolean[] areConnected(int n, int[][] roads, int[][] queries)
    {

        /*
        Approach:
        1. Sort all roads by their availability year in ascending order.

        2. Sort all queries by their year in ascending order,
           but remember each query's original index.

        3. Initialize a DSU with n separate cities.

        4. Maintain a pointer into the sorted roads.

        5. Process queries in increasing year:
           - Add every road whose year <= current query's year to the DSU.
           - This means the DSU now represents exactly the graph
             containing all roads available at this query's year.
           - Check whether the two cities in the query have the same
             DSU representative.
           - Store the result at the query's original index.

        6. Return the answers in the original query order.

        Why this works:
        - As query years increase, roads only become available;
          they never need to be removed.
        - Therefore the DSU can be built incrementally.
        - Each road is processed exactly once.
        - Each query performs only DSU find operations.

        Time:
        O(M log M + Q log Q + (M + Q) * α(N))

        Space:
        O(N + M + Q)
        */

        return null;
    }
}
