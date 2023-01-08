package tree;

public class CloneBinaryTree {
    public static void main(String[] args)
    {
        Node node7 = new Node(3, null, null);
        Node node6 = new Node(4, null, null);
        Node node5 = new Node(4, null, null);
        Node node4 = new Node(3, null, null);
        Node node3 = new Node(2, node6, node7);
        Node node2 = new Node(2, node4, node5);
        Node node = new Node(1, node2, node3);

        System.out.println("########Actual##############");
        printTreeInOrder(node);

        System.out.println();
        System.out.println("########Clone##############");
        Node clone = clone(node);
        printTreeInOrder(clone);

    }

    private static Node clone(Node root)
    {
        if(root == null){
            return null;
        }

        Node clone = new Node(root.value, null, null);
        clone.left = clone(root.left);
        clone.right = clone(root.right);

        return clone;
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
