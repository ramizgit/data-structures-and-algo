package tree;

public class CountGoodNodes {

    public static void main(String[] args) {
        Node node7 = new Node(5, null, null);
        Node node6 = new Node(1, null, null);
        Node node4 = new Node(3, null, null);
        Node node3 = new Node(1, node6, node7);
        Node node2 = new Node(1, node4, null);
        Node node = new Node(2, node2, node3);

        System.out.println(goodNodes(node));

    }
    private static int goodNodes(Node root)
    {
        int[] count = new int[1];
        dfs(root, root.value, count);
        return count[0];
    }

    private static void dfs(Node node, int currMax, int[] count)
    {
        if(node == null){
            return;
        }

        if(node.value >= currMax){
            count[0]++;
        }

        dfs(node.left, Math.max(node.value, currMax), count);
        dfs(node.right, Math.max(node.value, currMax), count);
    }
}
