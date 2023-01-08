package linkedlist;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedListsViaMinHeap {
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

        Queue<Node> minheap = new PriorityQueue<>( (a,b) -> a.value - b.value );
        minheap.addAll(Arrays.asList(list));

        Node head = new Node();
        Node prev = head;

        while (!minheap.isEmpty()){
            Node remove = minheap.remove();
            prev.next = remove;
            prev = prev.next;

            if(remove.next != null){
                minheap.add(remove.next);
            }
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
