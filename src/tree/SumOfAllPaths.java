package tree;

public class SumOfAllPaths {

    public static void main(String[] args)
    {
        Node node7 = new Node(7, null, null);
        Node node4 = new Node(4, null, null);
        Node node3 = new Node(3, null, node7);
        Node node2 = new Node(2, node4, null);
        Node node = new Node(1, node2, node3);

        System.out.println(pathSum(node));

    }

    private static int pathSum(Node root)
    {
        int[] output = new int[1];
        dfs(root, 0, output);
        return output[0];
    }

    private static void dfs(Node node, int currSum, int[] total)
    {
        if(node == null){
            return;
        }

        currSum = currSum * 10 + node.value;

        //leaf node
        if(node.left == null && node.right == null){
            total[0] += currSum;
            return;
        }

        dfs(node.left, currSum, total);
        dfs(node.right, currSum, total);
    }
}
