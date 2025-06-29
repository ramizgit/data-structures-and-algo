package linkedlist;

public class Node {
    public int value;
    public Node next;

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public static void print(Node node){
        while(node != null){
            System.out.print(node.value + " -> ");
            node = node.next;
        }
        System.out.println();
    }
}
