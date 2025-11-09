package tree;

public class NextNodeInorderTraversal {

    public static void main(String[] args)
    {

    }

    private static PNode nextNode(PNode node)
    {
        if(node == null){
            return null;
        }

        // Case 1: right child exists — find leftmost in right subtree
        if(node.right != null){
            PNode tmpNode = node.right;
            while(tmpNode.left != null){
                tmpNode = tmpNode.left;
            }
            return tmpNode;
        }

        //if not above, if the node is left child of its parent, then return its parent
        //if none of above, travers to its parent chain until we find a parent which is left child its parent, return this parent

        // Case 2: move up until node is left child of its parent
        PNode parent = node.parent;
        while(parent != null && parent.right == node){
            node = parent;
            parent = parent.parent;
        }

        return parent;
    }
}

