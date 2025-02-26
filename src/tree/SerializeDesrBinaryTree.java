package meta;

import tree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SerializeDesrBinaryTree {

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

        List<Integer> tree = new ArrayList<>();
        serialize(node, tree);

        System.out.println(tree);

        Node newnode = deserialize(tree, new AtomicInteger(0));

        System.out.println(newnode);

    }

    private static void serialize(Node node, List<Integer> tree)
    {
        if(node == null){
            tree.add(null);
            return;
        }

        tree.add(node.value);
        serialize(node.left, tree);
        serialize(node.right, tree);
    }

    private static Node deserialize(List<Integer> tree, AtomicInteger idx)
    {
        if(idx.get() >= tree.size()){
            return null;
        }

        Integer curr = tree.get(idx.getAndIncrement());

        if(curr == null){
            return null;
        }

        Node node = new Node(curr, null, null);
        node.left = deserialize(tree, idx);
        node.right = deserialize(tree, idx);

        return node;
    }
}
