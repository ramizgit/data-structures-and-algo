public class IsBinaryTreeBalanced {
    public static void main(String[] args)
    {
        Node node12 = new Node(10, null, null);
        Node node13 = new Node(10, null, null);
        Node node10 = new Node(10, null, null);
        Node node11 = new Node(11, node12, node13);
        Node node7 = new Node(7, node10, node11);
        Node node6 = new Node(6, null, null);
        Node node5 = new Node(5, null, null);
        Node node4 = new Node(4, null, null);
        Node node3 = new Node(3, node6, node7);
        Node node2 = new Node(2, node4, node5);
        Node node = new Node(1, node2, node3);

        System.out.println(isTreeBalanced(node));

        System.out.println("Efficient");
        boolean[] balanced = new boolean[1];
        dfs(node, balanced);
        System.out.println(balanced[0]);
    }

    //-----------efficient------------------
    private static int dfs(Node node, boolean[] balanced)
    {
        if(node == null){
            return 0;
        }

        int left = dfs(node.left, balanced);
        int right = dfs(node.right, balanced);

        if(Math.abs(left - right) > 1){
            balanced[0] = false;
        }

        return 1 + Math.max(left, right);
    }
    //-----------efficient------------------

    public static boolean isTreeBalanced(Node node)
    {
        if(node == null)
        {
            return true;
        }

        int diff = depth(node.left) - depth(node.right);
        if(diff > 1 || diff < -1)
        {
            System.out.println("diff = "+diff);
            return false;
        }

        return isTreeBalanced(node.left) && isTreeBalanced(node.right);
    }

    public static int depth(Node node)
    {
        if(node == null)
        {
            return 0;
        }

        int left = depth(node.left);
        int right = depth(node.right);

        return 1 + Math.max(left, right);
    }


}