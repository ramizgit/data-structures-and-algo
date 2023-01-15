package tree;

import javafx.util.Pair;

public class HouseRobberIIILeetcode {
    public static void main(String[] args)
    {
        Node node4 = new Node(100, null, null);
        Node node3 = new Node(1, null, null);
        Node node2 = new Node(2, null, node4);
        Node node1 = new Node(3, null, node3);
        Node node = new Node(3, node2, node1);

        System.out.println(maxrob(node));
    }

    private static int maxrob(Node node)
    {
        Pair<Integer, Integer> dfs = dfs(node);
        return Math.max(dfs.getKey(), dfs.getValue());
    }

    private static Pair<Integer, Integer> dfs(Node node)
    {
        if(node == null){
            return new Pair<>(0, 0);
        }

        Pair<Integer, Integer> left = dfs(node.left);
        Pair<Integer, Integer> right = dfs(node.right);

        int maxWithRoot = node.value + left.getValue() + right.getValue();
        int maxWithoutRoot = Math.max(left.getKey(), left.getValue()) + Math.max(right.getKey(), right.getValue());

        return new Pair(maxWithRoot, maxWithoutRoot);
    }
}
