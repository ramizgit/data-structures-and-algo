package tree;

import java.util.*;

public class BinTreeFromPreAndInOrder {

    public Node buildTree(int[] preorder, int[] inorder)
    {
        Map<Integer, Integer> inorderIdxMap = new HashMap<>();
        for(int i=0; i<inorder.length; i++){
            inorderIdxMap.put(inorder[i], i);
        }

        int[] preorderIdx = {0};

        return dfs(preorder, 0, inorder.length-1, inorderIdxMap, preorderIdx);
    }

    public Node dfs(int[] preorder, int left, int right, Map<Integer, Integer> inorderIdxMap, int[] preorderIdx)
    {
        if(left > right){
            return null;
        }

        Node node = new Node(preorder[preorderIdx[0]++]);
        int mid = inorderIdxMap.get(node.value);
        node.left = dfs(preorder, left, mid-1, inorderIdxMap, preorderIdx);
        node.right = dfs(preorder, mid+1, right, inorderIdxMap, preorderIdx);

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
