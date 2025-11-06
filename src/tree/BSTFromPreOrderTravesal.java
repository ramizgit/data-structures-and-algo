package tree;

public class BSTFromPreorder {

    private static Node buildBST(int[] preorder)
    {
        int[] idx = new int[1];
        return dfs(preorder, idx, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static Node dfs(int[] preorder, int[] idx, int lower, int upper)
    {
        if(idx[0] == preorder.length){
            return null;
        }

        int nodeVal = preorder[idx[0]];

        //check range
        if(nodeVal < lower || nodeVal > upper){
            return null;
        }

        Node node = new Node(nodeVal);
        idx[0]++;

        node.left = dfs(preorder, idx, lower, nodeVal);
        node.right = dfs(preorder, idx, nodeVal, upper);

        return node;
    }
}
