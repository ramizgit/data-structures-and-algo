package graph;

import java.util.*;

public class CloneAGraphDFS {
    public static void main(String[] args)
    {
        Gnode node1 = new Gnode(1);
        Gnode node2 = new Gnode(2);
        Gnode node3 = new Gnode(3);
        Gnode node4 = new Gnode(4);
        Gnode node5 = new Gnode(5);
        Gnode node6 = new Gnode(6);

        node1.neighbors.addAll(Arrays.asList(node2, node3));
        node2.neighbors.addAll(Arrays.asList(node1, node4));
        node3.neighbors.addAll(Arrays.asList(node1, node4));
        node4.neighbors.addAll(Arrays.asList(node2, node3, node5));
        node5.neighbors.addAll(Arrays.asList(node4, node6));
        node6.neighbors.addAll(Arrays.asList(node5));

        cloneGraph(node1);
    }

    public static void cloneGraph(Gnode node)
    {
        Map<Gnode, Gnode> oldToNewNodeMap = new HashMap<>();

        dfs(node, oldToNewNodeMap);

        System.out.println("done");
    }

    public static Gnode dfs(Gnode node, Map<Gnode, Gnode> oldToNewNodeMap)
    {
        if(oldToNewNodeMap.containsKey(node)){
            return oldToNewNodeMap.get(node);
        }

        Gnode copy = new Gnode(node.val);
        oldToNewNodeMap.put(node, copy);
        for(Gnode neighbor : node.neighbors){
            copy.neighbors.add(dfs(neighbor, oldToNewNodeMap));
        }
        return copy;
    }
}

class Gnode{
    public int val;
    public List<Gnode> neighbors;

    public Gnode() {
        val = 0;
        neighbors = new ArrayList<Gnode>();
    }
    public Gnode(int _val) {
        val = _val;
        neighbors = new ArrayList<Gnode>();
    }
    public Gnode(int _val, List<Gnode> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
