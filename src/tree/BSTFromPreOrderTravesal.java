package tree;

import java.util.Arrays;
import java.util.List;

public class BSTFromPreOrderTravesal {
    public static void main(String[] args)
    {
        List<Integer> preorder = Arrays.asList(8, 5, 1, 7, 10, 12);
        Node root = buildTree(preorder, new int[]{0}, Integer.MAX_VALUE);
        printTreeInOrder(root);
    }

    private static Node buildTree(List<Integer> preorder, int[] index, int maxbound)
    {
        if(index[0] == preorder.size() || preorder.get(index[0]) > maxbound){
            return null;
        }

        Node node = new Node(preorder.get(index[0]), null, null);
        index[0]++;
        node.left = buildTree(preorder, index, node.value);
        node.right = buildTree(preorder, index, maxbound);
        return node;
    }

    private static void printTreeInOrder(Node node)
    {
        if(node == null){
            return;
        }

        printTreeInOrder(node.left);
        System.out.print(node.value + " ");
        printTreeInOrder(node.right);
    }
}
