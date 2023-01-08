package linkedlist;

import java.util.HashMap;

public class OddEven {
    public static void main(String[] args)
    {
        Node node5 = new Node(5, null);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);

        print(node1);
        print(putOddBeforeEven(node1));

    }

    public static Node putOddBeforeEven(Node node)
    {
        //null check
        if(node == null ||  node.next == null){
            return node;
        }

        Node head = node;
        Node prev = node;
        Node current = node.next;

        while(current != null){
            if( (prev.value % 2 == 0) && (current.value % 2 != 0) ){
                prev.next = current.next;
                current.next = head;
                head = current;
                current = prev.next;
            }else {
                prev = current;
                current = current.next;
            }
        }

        return head;
    }

    public static void print(Node node){
        while(node != null){
            System.out.print(node.value + " -> ");
            node = node.next;
        }
        System.out.println();
    }
}
