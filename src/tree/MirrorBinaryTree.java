package tree;

public class MirrorBinaryTree {
    public static void main(String[] args)
    {
        //todo:make it work
        Node node4 = new Node(4, null, null);
        Node node3 = new Node(3, null, node4);
        Node node2 = new Node(2, null, null);
        Node node = new Node(1, node2, node3);

        System.out.println("########Actual##############");
        printTreeInOrder(node);

        mirror(node);

        System.out.println();
        System.out.println("########Mirror##############");
        printTreeInOrder(node);

    }

    private static void mirror(Node node)
    {
        if(node == null){
            return;
        }

        //swap
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;

        mirror(node.left);
        mirror(node.right);
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
