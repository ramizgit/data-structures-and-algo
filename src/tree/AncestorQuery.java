package consistenthashing.tree;

import java.util.*;

public class AncestorQuery {

    //todo
    /*
    Approach
    Naive Approach

Store:

child -> parent

For a query (X, Y):

Start from Y
Keep moving to parent
If you reach X, return true

Example:

7 -> 5 -> 2 -> 1

Query (2,7) => true

Time complexity:

Preprocessing: O(N)
Each query: O(height)

Good if queries are few.

Better Approach: DFS Entry/Exit Times

During DFS, assign every node:

tin[node]  = time when DFS enters node
tout[node] = time when DFS exits node

Example tree:

        1
      /   \
     2     3
    / \     \
   4   5     6
       |
       7

Suppose DFS order gives:

Node	tin	tout
1	1	14
2	2	9
4	3	4
5	5	8
7	6	7
3	10	13
6	11	12
Key Observation

X is an ancestor of Y iff:

tin[X] <= tin[Y]
AND
tout[X] >= tout[Y]

Why?
Because the DFS interval of every node completely contains the intervals of all nodes in its subtree.
     */

    Map<Integer, Integer> inTime = new HashMap<>();
    Map<Integer, Integer> outTime = new HashMap<>();

    int timer = 0;

    public void preprocess(Node root)
    {
        dfs(root);
    }

    private void dfs(Node node)
    {
        if(node == null){
            return;
        }

        inTime.put(node.value, timer++);

        dfs(node.left);
        dfs(node.right);

        outTime.put(node.value, timer++);
    }

    public boolean isAncestor(int ancestor, int node)
    {
        return inTime.get(ancestor) < inTime.get(node) && outTime.get(ancestor) > outTime.get(node);
    }

    class Node{
        int value;
        Node left;
        Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
