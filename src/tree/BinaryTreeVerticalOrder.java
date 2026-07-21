package tree;

import java.util.*;

public class BinaryTreeVerticalOrder {

    /*
    Use BFS so nodes are processed from top to bottom.
    Track each node's vertical column.
    Store nodes grouped by column in a TreeMap to keep columns sorted.
    */

    /*
    Time Complexity:
    - O(n log k), where n is the number of nodes and k is the number of vertical columns.
    - Each node is visited once, and each TreeMap insertion takes O(log k).

    Space Complexity:
    - O(n)
    - Queue and TreeMap together store at most n nodes.
    */
    private static List<List<Integer>> verticalTraversal(Node root)
    {
        if (root == null) {
            return Collections.emptyList();
        }

        Queue<State> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(new State(root, 0));  //start with root at vertical 0

        TreeMap<Integer, List<Integer>> result = new TreeMap<>(); // TreeMap keeps vertical columns sorted from left to right.

        while(!bfsQueue.isEmpty()){

            State curr = bfsQueue.poll();

            //collect result
            result.computeIfAbsent(curr.vertical, k -> new ArrayList<>()).add(curr.node.value);

            //add left child
            if(curr.node.left != null){
                bfsQueue.offer(new State(curr.node.left, curr.vertical - 1));
            }

            //add right child
            if(curr.node.right != null){
                bfsQueue.offer(new State(curr.node.right, curr.vertical + 1));
            }
        }

        return new ArrayList<>(result.values());
    }

    static class Node {
        int value;
        Node left;
        Node right;
    }

    static class State{
        Node node;
        int vertical;

        public State(Node node, int vertical) {
            this.node = node;
            this.vertical = vertical;
        }
    }
}


