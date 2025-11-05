package tree;

public class ValidateBST {

    private static boolean isValidBST(Node root)
    {
        return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean dfs(Node node, int leftMax, int rightMax)
    {
        if(node == null){
            return true;
        }

        if(node.value <= leftMax || node.value >= rightMax){
            return false; //out of range
        }

        return dfs(node.left, leftMax, node.value) && dfs(node.right, node.value, rightMax);
    }
}
