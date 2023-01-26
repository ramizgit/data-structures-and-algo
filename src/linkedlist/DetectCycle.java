package linkedlist;

public class DetectCycle {

    public static void main(String[] args)
    {
        Node node5 = new Node(5, null);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);

        node5.setNext(node3);

        System.out.println(hasCycle(node1));
    }

    private static boolean hasCycle(Node node)
    {
        if(node == null){
            return false;
        }

        Node slow = node;
        Node fast = node.next;

        while(fast != null && fast.next != null){
            if(slow == fast){
                return true;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }
}
