package linkedlist;

public class DeepCloneLinkedList {
    public static void main(String[] args)
    {
        Node node5 = new Node(5, null);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);

        print(node1);

        Node copy = clone(node1);

        print(copy);
    }

    private static Node clone(Node node)
    {
        if(node == null){
            return null;
        }

        Node copy = new Node(node.value);
        copy.next = clone(node.next);

        return copy;
    }

    private static void print(Node node){
        while(node != null){
            System.out.print(node.value + " -> ");
            node = node.next;
        }
        System.out.println();
    }
}
