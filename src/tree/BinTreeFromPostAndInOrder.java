package tree;

import java.util.HashMap;
import java.util.Map;

public class BinTreeFromPostAndInOrder {

    public Node buildTree(int[] postorder, int[] inorder)
    {
        Map<Integer, Integer> inorderIdxMap = new HashMap<>();
        for(int i=0; i<inorder.length; i++){
            inorderIdxMap.put(inorder[i], i);
        }

        int[] postorderIdx = { postorder.length - 1 };

        return dfs(postorder, 0, inorder.length-1, inorderIdxMap, postorderIdx);
    }

    public Node dfs(int[] postorder, int left, int right, Map<Integer, Integer> inorderIdxMap, int[] postorderIdx)
    {
        if(left > right){
            return null;
        }

        Node node = new Node(postorder[postorderIdx[0]--]);
        int mid = inorderIdxMap.get(node.value);
        node.right = dfs(postorder, mid+1, right, inorderIdxMap, postorderIdx); //important : build right first
        node.left = dfs(postorder, left, mid-1, inorderIdxMap, postorderIdx);

        return node;
    }

    class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }
}
