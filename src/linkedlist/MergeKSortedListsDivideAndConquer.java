package linkedlist;

import java.util.*;

public class MergeKSortedListsDivideAndConquer {
    public static void main(String[] args)
    {
        //Via divide and conquer in "O(nlogk)"

        Node node9 = new Node(9, null);
        Node node7 = new Node(7, node9);
        Node node1 = new Node(1, node7);

        print(node1);

        Node node6 = new Node(6, null);
        Node node4 = new Node(4, node6);
        Node node2 = new Node(2, node4);

        print(node2);


        Node node5 = new Node(5, null);
        Node node3 = new Node(3, node5);

        print(node3);

        Node mergednode = merge(new Node[]{node1, node2, node3});
        print(mergednode);
    }

    private static Node merge(Node[] list)
    {
        //handle edge cases
        if(list == null){
            return null;
        }

        if(list.length == 1){
            return list[0];
        }

        Queue<Node> queue = new LinkedList<>(Arrays.asList(list));

        while (queue.size() > 1){
            Node node1 = queue.remove();
            Node node2 = queue.remove();
            queue.add(mergeTwoLists(node1, node2));
        }

        return queue.remove();
    }

    private static Node mergeTwoLists(Node node1, Node node2)
    {
        Node head = new Node();
        Node prev = head;

        while (node1 != null && node2 != null){
            if(node1.value < node2.value){
                prev.next = new Node(node1.value);
                node1 = node1.next;
            }else {
                prev.next = new Node(node2.value);
                node2 = node2.next;
            }
            prev = prev.next;
        }

        while (node1 != null){
            prev.next = new Node(node1.value);
            node1 = node1.next;
            prev = prev.next;
        }

        while (node2 != null){
            prev.next = new Node(node2.value);
            node2 = node2.next;
            prev = prev.next;
        }

        return head.next;
    }

    private static void print(Node node){
        while(node != null){
            System.out.print(node.value + " -> ");
            node = node.next;
        }
        System.out.println();
    }
}


