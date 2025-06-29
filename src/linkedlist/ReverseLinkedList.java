package linkedlist;

public class ReverseLinkedList {
    //https://leetcode.com/problems/reverse-linked-list/description/

    public static void main(String[] args)
    {
        Node node5 = new Node(5, null);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);

        Node.print(node1);

        //Node node = reverseIteratively(node1);
        Node node = reverseViaRecursion(node1);

        Node.print(node);
    }

    private static Node reverseIteratively(Node head)
    {
        if(head == null || head.next == null){
            return head;
        }

        Node curr = head;
        Node prev = null;
        Node next = null;

        while (curr != null){
            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        return prev;
    }

    private static Node reverseViaRecursion(Node head)
    {
        if(head == null || head.next == null){
            return head;
        }

        Node newHead = reverseViaRecursion(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }
}
