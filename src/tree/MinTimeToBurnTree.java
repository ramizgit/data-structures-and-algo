package tree;

import javafx.util.Pair;

import java.util.*;

public class MinTimeToBurnTree {
    public static void main(String[] args)
    {
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

        System.out.println(minTimeToBurnTree(node, 4));

    }

    private static int minTimeToBurnTree(Node root, int leaf)
    {
        //collect child to parent node mapping
        Map<Node, Node> childToParentMap = childToParentNodeMapping(root);
        Node leafNode = dfs(root, leaf);

        return bfs(leafNode, childToParentMap);
    }

    private static int bfs(Node leaf, Map<Node, Node> childToParentMap)
    {
        Queue<Pair<Node, Integer>> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(new Pair<>(leaf, 0));
        visited.add(leaf.value);
        int count=0;

        while (!queue.isEmpty())
        {
            Pair<Node, Integer> poll = queue.poll();
            Node node = poll.getKey();
            count = poll.getValue();

            //left child
            if(node.left != null && !visited.contains(node.left.value)){
                queue.add(new Pair<>(node.left, poll.getValue()+1));
                visited.add(node.left.value);
            }

            //right child
            if(node.right != null && !visited.contains(node.right.value)){
                queue.add(new Pair<>(node.right, poll.getValue()+1));
                visited.add(node.right.value);
            }

            //parent
            if(childToParentMap.get(node) != null && !visited.contains(childToParentMap.get(node).value)){
                queue.add(new Pair<>(childToParentMap.get(node), poll.getValue()+1));
                visited.add(childToParentMap.get(node).value);
            }
        }
        return count;
    }

    private static Node dfs(Node root, int leaf)
    {
        if(root == null){
            return null;
        }

        if(root.value == leaf){
            return root;
        }

        if(root.left != null){
            return dfs(root.left, leaf);
        }

        return dfs(root.right, leaf);
    }

    private static Map<Node, Node> childToParentNodeMapping(Node root)
    {
        Map<Node, Node> childToParentMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        childToParentMap.put(root, null);
        queue.add(root);

        while (!queue.isEmpty())
        {
            Node node = queue.poll();

            if(node.left != null){
                childToParentMap.put(node.left, node);
                queue.add(node.left);
            }

            if(node.right != null){
                childToParentMap.put(node.right, node);
                queue.add(node.right);
            }
        }

        return childToParentMap;
    }
}
