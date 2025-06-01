package meta;

import com.sun.source.tree.Tree;
import tree.Node;

import java.util.*;

public class BinaryTreeVerticalOrder {

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

        verticalOrderBfs(node);
    }
    
    private static void verticalOrderBfs(Node node)
    {
        Queue<VNode> queue = new LinkedList<>();
        VNode root = new VNode(node, 0);
        queue.add(root);
        TreeMap<Integer, List<Integer>> verticalOrder = new TreeMap<>();

        while (!queue.isEmpty()){
            VNode poll = queue.poll();

            //add to vertical oder tree map
            verticalOrder.computeIfAbsent(poll.vertical, key ->new ArrayList<>()).add(poll.node.value);

            if(poll.node.left != null){
                queue.add(new VNode(poll.node.left, poll.vertical-1));
            }

            if(poll.node.right != null){
                queue.add(new VNode(poll.node.right, poll.vertical+1));
            }
        }

        //traverse vertical order tree map and collect result
        List<List<Integer>> result = new ArrayList<>();
        for(List<Integer> values : verticalOrder.values()){
            result.add(values);
        }
        System.out.println(result);
    }
}

class VNode{
    Node node;
    int vertical;

    public VNode(Node node, int vertical) {
        this.node = node;
        this.vertical = vertical;
    }
}
