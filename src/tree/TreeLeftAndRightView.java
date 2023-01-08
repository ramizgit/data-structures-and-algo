package tree;

import java.util.HashSet;
import java.util.Set;

public class TreeLeftAndRightView {

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

        System.out.print("Left view : ");
        printLeftViewOfTree(node, 0, new HashSet<>());
        System.out.println();
        System.out.println("======================");
        System.out.print("Right view : ");
        printRightViewOfTree(node, 0, new HashSet<>());

    }

    public static void printLeftViewOfTree(Node root, int currentLevel, Set<Integer> levels)
    {
        if(root == null){
            return;
        }

        if(!levels.contains(currentLevel)){
            System.out.print(root.value+ " ");
            levels.add(currentLevel);
        }

        printLeftViewOfTree(root.left, currentLevel+1, levels);
        printLeftViewOfTree(root.right, currentLevel+1, levels);
    }

    public static void printRightViewOfTree(Node root, int currentLevel, Set<Integer> levels)
    {
        if(root == null){
            return;
        }

        if(!levels.contains(currentLevel)){
            System.out.print(root.value+ " ");
            levels.add(currentLevel);
        }

        printRightViewOfTree(root.right, currentLevel+1, levels);
        printRightViewOfTree(root.left, currentLevel+1, levels);
    }
}

class Node{
    int value;
    Node left;
    Node right;

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
