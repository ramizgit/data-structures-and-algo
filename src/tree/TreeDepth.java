package tree;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class TreeDepth {
    public static void main(String[] args)
    {
        Node node10 = new Node(10, null, null);
        Node node11 = new Node(11, null, null);
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
               4  5   6     7
                         10   11
         */

        //MAXIMUM depth of tree using DFS
        System.out.println(dfs(node));

        //MINIMUM depth of tree using BFS
        System.out.println(bfs(node));
    }

    public static int dfs(Node node) //for maximum depth
    {
        if(node == null)
        {
            return 0;
        }

        int left = dfs(node.left);
        int right = dfs(node.right);

        return 1 + Math.max(left, right);
    }

    public static int bfs(Node node) //for minimum depth
    {
        if(node == null){
            return 0;
        }

        Queue<Pair<Node, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(node, 1));

        while(!queue.isEmpty()){
            Pair<Node, Integer> poll = queue.poll();
            Node tmpnode = poll.getKey();
            if(tmpnode.left == null &&  tmpnode.right == null){
                return poll.getValue();
            }

            if(tmpnode.left != null){
                queue.add(new Pair<>(tmpnode.left, poll.getValue()+1));
            }

            if(tmpnode.right != null){
                queue.add(new Pair<>(tmpnode.right, poll.getValue()+1));
            }
        }
        return 0;
    }
}
