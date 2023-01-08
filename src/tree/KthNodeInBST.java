package tree;

public class KthNodeInBST {
    public static void main(String[] args)
    {
        //in order traversal
        Node node6 = new Node(6, null, null);
        Node node8 = new Node(8, null, null);
        Node node7 = new Node(7, node6, node8);
        Node node4 = new Node(4, null, null);
        Node node2 = new Node(2, null, null);
        Node node3 = new Node(3, node2, node4);
        Node node = new Node(5, node3, node7);

        inorderTravesal(node);
        System.out.println();
        //System.out.println(getKthNodeInBST(node, 4).value);

        int[] num = new int[2];
        kthSmallest(node, num, 7);
        System.out.println(num[1]);

    }

    public static void kthSmallest(Node root, int[] arr, int k)
    {
        if(root == null){
            return;
        }

        kthSmallest(root.left, arr, k);

        if(++arr[0] == k){
            arr[1] = root.value;
            return;
        }

        kthSmallest(root.right, arr, k);

    }

    public static void inorderTravesal(Node root)
    {
        if(root == null){
            return;
        }

        inorderTravesal(root.left);
        System.out.print(root.value+ " ");
        inorderTravesal(root.right);
    }
}
