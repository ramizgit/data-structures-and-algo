package linkedlist;

public class DetectCycle {

    public static void main(String[] args)
    {
        Node node6 = new Node(6, null);
        Node node5 = new Node(5, node6);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);

        node6.setNext(node3);

        hasCycle(node1);
    }

    private static boolean hasCycle(Node head)
    {
        if(head == null){
            return false;
        }

        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                System.out.println("Cycle detected !!!");
                System.out.println("length of cycle : "+ lengthOfCycle(fast, slow));
                System.out.println("starting point of cycle : "+ startingPointOfCycle(head, slow).value);
                return true;
            }
        }

        return false;
    }

    private static int lengthOfCycle(Node fast, Node slow)
    {
        int length = 1;
        fast = fast.next;

        while(fast != slow){
            fast = fast.next;
            length++;
        }

        return length;
    }

    private static Node startingPointOfCycle(Node head, Node meet)
    {
        Node node = head;

        while (node != meet){
            node = node.next;
            meet = meet.next;
        }

        return node;
    }
}
