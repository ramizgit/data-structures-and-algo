package graph.dfs;

import java.util.*;

public class CloneAGraphDFS {

    public void cloneGraph(Gnode node)
    {
        Map<Gnode, Gnode> oldToNewNodeMap = new HashMap<>();
        dfs(node, oldToNewNodeMap);
    }

    private Gnode dfs(Gnode node, Map<Gnode, Gnode> oldToNewNodeMap)
    {
        if(oldToNewNodeMap.containsKey(node)){
            return oldToNewNodeMap.get(node);
        }

        Gnode copy = new Gnode(node.val); //make copy

        oldToNewNodeMap.put(node, copy);

        //add neighbours
        for(Gnode neighbor : node.neighbors){
            copy.neighbors.add(dfs(neighbor, oldToNewNodeMap));
        }

        return copy;
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
}


