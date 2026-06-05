package tree;

public class DiameterOfTree {

    public static int dfs(Node root, int[] result)
    {
        if(root == null){
            return 0;
        }

        int left = dfs(root.left, result);
        int right = dfs(root.right, result);

        result[0] = Math.max(result[0], left+right);

        return 1 + Math.max(left, right);
    }

    class Node{
        int value;
        Node left;
        Node right;
    }
}
