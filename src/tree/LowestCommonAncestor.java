package tree;

public class LowestCommonAncestor {

    //binary tree, nodes p and q are guranteed to be in the tree
    public Node lowestCommonAncestor(Node root, Node p, Node q) {

        if(root == null){
            return null;
        }

        if(root.value == p.value || root.value == q.value){
            return root;
        }

        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null){
            return root;
        }

        return left != null ? left : right;
    }

    //binary tree, nodes p and q are NOT guranteed to be in the tree
    public Node lowestCommonAncestor(Node root, Node p, Node q, boolean[] exists) {

        if(root == null){
            return null;
        }

        //we need to traverse whole tree first, hence this recursion call is before equality check
        Node left = lowestCommonAncestor(root.left, p, q, exists);
        Node right = lowestCommonAncestor(root.right, p, q, exists);

        if(root.value == p.value){
            exists[0] = true;
            return root;
        }

        if(root.value == q.value){
            exists[1] = true;
            return root;
        }

        if(left != null && right != null){
            return root;
        }

        return left != null ? left : right;
    }

    //binary search tree, nodes p and q are guranteed to be in the tree
    public Node lowestCommonAncestorBST(Node root, Node p, Node q) {

        //exit condition if we reach bottom of the tre
        if(root == null){
            return null;
        }

        //if both smallter, go left
        if(p.value < root.value && q.value < root.value){
            return lowestCommonAncestorBST(root.left, p, q);
        }

        //if both greater, go right
        if(p.value > root.value && q.value > root.value){
            return lowestCommonAncestorBST(root.right, p, q);
        }

        //either root is the split point, or equals to one of them
        return root;
    }
}
