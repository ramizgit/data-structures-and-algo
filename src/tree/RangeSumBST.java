package meta;

import tree.Node;

public class RangeSumBST {

    public static void main(String[] args)
    {
        Node node7 = new Node(18, null, null);
        Node node5 = new Node(7, null, null);
        Node node4 = new Node(3, null, null);
        Node node3 = new Node(15, null, node7);
        Node node2 = new Node(5, node4, node5);
        Node node = new Node(10, node2, node3);

        System.out.println(dfs(node, 7, 15));

    }

    private static int dfs(Node node, int low, int high)
    {
        if(node == null) {
            return 0;
        }

        if(node.value > high){
            return dfs(node.left, low, high);
        }else if(node.value < low){
            return dfs(node.right, low, high);
        }

        return node.value + dfs(node.left, low, high) + dfs(node.right, low, high);
    }
}
