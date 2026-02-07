package tree;

public class LongestUnivaluePath {

    //https://leetcode.com/problems/longest-univalue-path/
    public int longestUnivaluePath(Node root)
    {
        int[] result = new int[1];
        dfs(root, result);
        return result[0];
    }

    private static int dfs(Node node, int[] result)
    {
        if(node == null){
            return 0;
        }

        int left = dfs(node.left, result);
        int right = dfs(node.right, result);

        int leftPath = 0;
        int rightPath = 0;

        if(node.left != null && node.value == node.left.value){
            leftPath = left + 1;
        }
        if(node.right != null && node.value == node.right.value){
            rightPath = right + 1;
        }

        result[0] = Math.max(result[0], leftPath + rightPath);

        return Math.max(leftPath, rightPath);
    }
}
