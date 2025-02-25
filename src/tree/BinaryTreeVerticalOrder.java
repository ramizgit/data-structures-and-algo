package meta;

import tree.Node;

import java.util.*;

public class BinaryTreeVerticalOrder {

    public static void main(String[] args)
    {
        //note : nodes are guaranteed to be found in the tree

        Node node12 = new Node(12, null, null);
        Node node10 = new Node(10, null, null);
        Node node11 = new Node(11, node12, null);
        Node node7 = new Node(7, node10, node11);
        Node node6 = new Node(6, null, null);
        Node node5 = new Node(5, null, null);
        Node node4 = new Node(4, null, null);
        Node node3 = new Node(3, node6, node7);
        Node node2 = new Node(2, node4, node5);
        Node node = new Node(1, node2, node3);

        /*
              1
           2     3
        4    5  6    7
                   10   11
                      12
         */

        verticalOrder(node);
    }

    private static void verticalOrder(Node node)
    {
        TreeMap<Integer, TreeMap<Integer, List<Integer>>> map = new TreeMap<>();

        dfs(node, 0, 0, map);

        // Convert the TreeMap to the required List<List<Integer>> format
        List<List<Integer>> result = new ArrayList<>();
        for (TreeMap<Integer, List<Integer>> levels : map.values()) {
            List<Integer> vertical = new ArrayList<>();
            for (List<Integer> nodes : levels.values()) {
                vertical.addAll(nodes);
            }
            result.add(vertical);
        }
        System.out.println(result);
    }

    //dfs
    private static void dfs(Node node, int level, int vertical, TreeMap<Integer, TreeMap<Integer, List<Integer>>> map)
    {
        if(node == null){
            return;
        }

        TreeMap<Integer, List<Integer>> levelmap = map.getOrDefault(vertical, new TreeMap<>());
        levelmap.computeIfAbsent(level, key -> new ArrayList<>()).add(node.value);
        map.put(vertical, levelmap);

        dfs(node.left, level+1, vertical-1, map);
        dfs(node.right, level+1, vertical+1, map);
    }
}
