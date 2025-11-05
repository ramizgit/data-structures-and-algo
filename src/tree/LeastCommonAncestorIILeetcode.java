package tree;

public class LeastCommonAncestorIILeetcode {

    public static void main(String[] args)
    {
        //note : nodes are NOT guaranteed to be found in the tree. if any of them not found, return null

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

        findLca(node, node4, node5); //2
        findLca(node, node6, node12); //3
        findLca(node, node3, node12); //3
        findLca(node, node4, node10); //1

        findLca(node, new Node(62, null, null), new Node(61, null, null)); //null
        findLca(node, new Node(1, null, null), new Node(61, null, null)); //null
        findLca(node, new Node(62, null, null), new Node(3, null, null)); //null
    }

    private static void findLca(Node root, Node node1, Node node2)
    {
        boolean[] found = new boolean[2];
        Node lca = dfs(root, node1, node2, found);

        if(lca != null && found[0] && found[1]){
            System.out.println("LCA found : "+lca.value);
        }else {
            System.out.println("LCA NOT found : "+null);
        }
    }

    private static Node dfs(Node root, Node node1, Node node2, boolean[] found)
    {
        if(root == null){
            return null;
        }

        //NOTE : we need to traverse whole tree first, hence this recursion call is before equality check
        Node left = dfs(root.left, node1, node2, found);
        Node right = dfs(root.right, node1, node2, found);

        if(root.value == node1.value){
            found[0] = true;
            return root;
        }

        if(root.value == node2.value){
            found[1] = true;
            return root;
        }

        if(left != null && right != null){
            return root;
        }

        return left != null ? left : right;
    }
}
