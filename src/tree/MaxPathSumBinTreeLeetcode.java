package tree;

public class MaxPathSumBinTreeLeetcode {
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

        int[] result = new int[1];
        dfs(node, result);
        System.out.println(result[0]);
    }

    public static int dfs(Node node, int[] result)
    {
        if(node == null){
            return 0;
        }

        int left = dfs(node.left, result);
        int right = dfs(node.right, result);

        //this is special case for -ve numbers, as we dont want to include -ve numbers, and skip them
        left = Math.max(left, 0);
        right = Math.max(right, 0);

        result[0] = Math.max(result[0], node.value + left + right);

        return node.value + Math.max(left, right);
    }
}
