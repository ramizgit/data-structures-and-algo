package tree;

public class IsTreeSymmetric {
    public static void main(String[] args)
    {
        Node node7 = new Node(3, null, null);
        Node node6 = new Node(4, null, null);
        Node node5 = new Node(4, null, null);
        Node node4 = new Node(3, null, null);
        Node node3 = new Node(2, node6, node7);
        Node node2 = new Node(2, node4, node5);
        Node node = new Node(1, node2, node3);

        System.out.println(isSymmetric(node));
    }

    public static boolean isSymmetric(Node root)
    {
        if(root == null){
            return true;
        }

        return isSymmetric(root.left, root.right);
    }

    public static boolean isSymmetric(Node left, Node right)
    {
        if(left == null || right == null){
            return left == right;
        }

        if(left.value != right.value){
            return false;
        }

        return isSymmetric(left.left, right.right) &&  isSymmetric(left.right, right.left);
    }
}
