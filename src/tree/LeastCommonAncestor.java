package tree;

public class LeastCommonAncestor {
    public static void main(String[] args)
    {
        //note : nodes are guaranteed to be found in the tree

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

        System.out.println(findLca(node, node4, node5).value); // 2
        System.out.println(findLca(node, node6, node12).value); // 3
        System.out.println(findLca(node, node3, node12).value); // 3
        System.out.println(findLca(node, node4, node10).value); // 1
    }

    public static Node findLca(Node root, Node node1, Node node2)
    {
        if(root == null){
            return null;
        }

        if(root.value == node1.value || root.value == node2.value){
            return root;
        }

        Node left = findLca(root.left, node1, node2);
        Node right = findLca(root.right, node1, node2);

        if(left != null && right != null){
            return root;
        }

        return left != null ? left : right;
    }
}
