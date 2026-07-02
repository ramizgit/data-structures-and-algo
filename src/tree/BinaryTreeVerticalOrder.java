package tree;

import java.util.*;

public class BinaryTreeVerticalOrder {

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

    static class Node {
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
}


