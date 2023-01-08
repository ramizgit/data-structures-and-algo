package tree;

import java.util.HashSet;
import java.util.Set;

public class LeastCommonAncestorIIILeetcode {
    
    public static void main(String[] args)
    {
        PNode node12 = new PNode(12, null, null);
        PNode node10 = new PNode(10, null, null);
        PNode node11 = new PNode(11, node12, null);
        PNode node7 = new PNode(7, node10, node11);
        PNode node6 = new PNode(6, null, null);
        PNode node5 = new PNode(5, null, null);
        PNode node4 = new PNode(4, null, null);
        PNode node3 = new PNode(3, node6, node7);
        PNode node2 = new PNode(2, node4, node5);
        PNode node = new PNode(1, node2, node3);

        node.setParent(null);
        node2.setParent(node);
        node3.setParent(node);
        node4.setParent(node2);
        node5.setParent(node2);
        node6.setParent(node3);
        node7.setParent(node3);
        node10.setParent(node7);
        node11.setParent(node7);
        node12.setParent(node11);

        /*
              1
           2     3
        4    5  6    7
                   10   11
                      12
         */

        System.out.println("##########With extra space#################");
        System.out.println(findLcaUsingExtraSpace(node4, node5).value); //2
        System.out.println(findLcaUsingExtraSpace(node11, node12).value); //11
        System.out.println(findLcaUsingExtraSpace(node10, node11).value); //7
        System.out.println(findLcaUsingExtraSpace(node3, node12).value); //3

        System.out.println("##########Without extra space#################");
        System.out.println(findLcaNoExtraSpace(node4, node5).value); //2
        System.out.println(findLcaNoExtraSpace(node11, node12).value); //11
        System.out.println(findLcaNoExtraSpace(node10, node11).value); //7
        System.out.println(findLcaNoExtraSpace(node3, node12).value); //3
    }

    private static PNode findLcaNoExtraSpace(PNode node1, PNode node2)
    {
        PNode node1copy = new PNode(node1.value, node1.left, node1.right, node1.parent);
        PNode node2copy = new PNode(node2.value, node2.left, node2.right, node2.parent);

        while (node1copy.value != node2copy.value){
            node1copy = (node1copy.parent != null) ? node1copy.parent : node2;
            node2copy = (node2copy.parent != null) ? node2copy.parent : node1;
        }

        return node1copy;
    }

    private static PNode findLcaUsingExtraSpace(PNode node1, PNode node2)
    {
        Set<Integer> visited = new HashSet<>();

        while (node1 != null){
            visited.add(node1.value);
            node1 = node1.parent;
        }

        while (node2 != null){
            if(visited.contains(node2.value)){
                return node2;
            }
            node2 = node2.parent;
        }

        return null;
    }
}

class PNode{
    int value;
    PNode left;
    PNode right;
    PNode parent;

    public PNode(int value, PNode left, PNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public PNode(int value, PNode left, PNode right, PNode parent) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public void setParent(PNode parent){
        this.parent = parent;
    }
}
