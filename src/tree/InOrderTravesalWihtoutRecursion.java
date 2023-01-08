package tree;

import java.util.Stack;

public class InOrderTravesalWihtoutRecursion {
    public static void main(String[] args)
    {
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

        inorder(node);
        System.out.println();
        inorderWithoutRecursion(node);
    }

    public static void inorder(Node node)
    {
        if(node == null){
            return;
        }

        inorder(node.left);
        System.out.print(node.value+ " ");
        inorder(node.right);
    }

    public static void inorderWithoutRecursion(Node node)
    {
        Stack<Node> stack = new Stack<>();
        Node current = node;

        while(current != null || !stack.empty()){

            if(current != null){
                stack.push(current);
                current = current.left;
            }else {
                Node pop = stack.pop();
                System.out.print(pop.value+" ");
                //push right child
                if(pop.right != null){
                    current = pop.right;
                }
            }
        }
    }
}
