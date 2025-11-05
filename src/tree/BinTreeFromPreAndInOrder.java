package tree;

import java.util.*;

public class BinTreeFromPreAndInOrder {

    private static Node buildTree(int[] preorder, int[] inorder)
    {
        Map<Integer, Integer> inOrderIdxMap = new HashMap<>();
        for(int i=0; i<inorder.length; i++){
            inOrderIdxMap.put(inorder[i], i);
        }

        int[] preOrderIdx = new int[1];

        return dfs(preorder, inOrderIdxMap, preOrderIdx, 0, inorder.length-1);
    }

    private static Node dfs(int[] preorder, Map<Integer, Integer> inOrderIdxMap, int[] preOrderIdx, int start, int end)
    {
        if(start > end){
            return null;
        }

        Node node = new Node(preorder[preOrderIdx[0]]);
        preOrderIdx[0]++;
        int inOrderIdx = inOrderIdxMap.get(node.value);

        node.left = dfs(preorder, inOrderIdxMap, preOrderIdx, start, inOrderIdx-1);
        node.right = dfs(preorder, inOrderIdxMap, preOrderIdx, inOrderIdx+1, end);

        return node;
    }
}
