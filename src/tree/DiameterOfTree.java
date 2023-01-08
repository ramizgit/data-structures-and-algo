package tree;

public class DiameterOfTree {
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
        System.out.println("Tree diameter : "+result[0]);
    }

    public static int dfs(Node root, int[] result)
    {
        if(root == null){
            return 0;
        }

        int left = dfs(root.left, result);
        int right = dfs(root.right, result);

        result[0] = Math.max(result[0], 1+left+right);

        return 1 + Math.max(left, right);
    }
}
