package tree;

import java.util.HashSet;
import java.util.Stack;

public class PrintPath {

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

        printPathFromRootToGivenNode(node, 10, new Stack<>());
        System.out.println();
        printPathFromRootToGivenNode(node, 5, new Stack<>());
        System.out.println();
        printPathFromRootToGivenNode(node, 12, new Stack<>());
    }

    public static void printPathFromRootToGivenNode(Node root, int target, Stack<Integer> path)
    {
        if(root == null){
            return;
        }

        path.push(root.value);

        if(root.value == target){
            //print path
            while (!path.empty()){
                System.out.print(path.pop()+ " ");
            }
            return;
        }


        printPathFromRootToGivenNode(root.left, target, path);
        printPathFromRootToGivenNode(root.right, target, path);

        if(!path.empty()){
            path.pop();
        }


    }
}
