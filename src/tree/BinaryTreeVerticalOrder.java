package tree;

import java.util.*;

public class VerticalOrderTraversal {

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

        System.out.println(verticalTraversal(node));
    }

    private static List<List<Integer>> verticalTraversal(Node root)
    {
        if (root == null) {
            return new ArrayList<>();
        }

        Queue<Node> queue = new ArrayDeque<>();
        root.level = 0;
        queue.add(root);

        TreeMap<Integer, List<Integer>> result = new TreeMap<>();

        while(!queue.isEmpty()){
            Node node = queue.poll();

            //collect result
            result.computeIfAbsent(node.level, k -> new ArrayList<>()).add(node.value);

            if(node.left != null){
                node.left.level = node.level - 1;
                queue.add(node.left);
            }

            if(node.right != null){
                node.right.level = node.level + 1;
                queue.add(node.right);
            }
        }

        List<List<Integer>> verticalOrder = new ArrayList<>();
        verticalOrder.addAll(result.values());
        return verticalOrder;
    }
}

class Node {
    int value;
    Node left;
    Node right;
    int level;

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Node(int value, Node left, Node right, int level) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
