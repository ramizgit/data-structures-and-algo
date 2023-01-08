package linkedlist;

public class ReverseLinkedList {
    public static void main(String[] args)
    {
        //5 -> 3 -> 6 -> 8 -> 7 -> null
        //7 -> 8 -> 6 -> 3 -> 5 -> null

        Node node7 = new Node(7, null);
        Node node8 = new Node(8, node7);
        Node node6 = new Node(6, node8);
        Node node3 = new Node(3, node6);
        Node node5 = new Node(5, node3);

        print(node5);
        print(reverseViaRecursion(node5));
        //print(reverseViaIteration(node5));
    }

    public static Node reverseViaIteration(Node node)
    {
        Node prev = null;
        Node next = null;
        Node current = node;

        while(current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    public static Node reverseViaRecursion(Node node)
    {
        if(node.next == null){
            return node;
        }

        Node head = reverseViaRecursion(node.next);
        node.next.next = node;
        node.next = null;
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
