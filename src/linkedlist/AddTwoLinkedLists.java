package linkedlist;

public class AddTwoLinkedLists {
    public static void main(String[] args)
    {
        Node node5 = new Node(5, null);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);

        print(node1);

        Node node33 = new Node(9, null);
        Node node22 = new Node(8, node33);
        Node node11 = new Node(7, node22);

        print(node11);
        add(node1, node11);
    }

    public static void add(Node node1, Node node2)
    {
        Node head1 = node1;
        Node head2 = node2;

        //----------------Add zero nodes to shorter list
        int len1 = length(head1);
        int len2 = length(head2);

        if(len1 < len2){
            //add zero nodes to node1
            for(int i=0; i<(len2-len1); i++){
                Node n = new Node(0, head1);
                head1 = n;
            }
        }else {
            //add zero nodes to node2
            for(int i=0; i<(len1-len2); i++){
                Node n = new Node(0, head2);
                head2 = n;
            }
        }

        System.out.println("print nodes after adding zeros");
        print(head1);
        print(head2);

        //---------------reverse the lists
        Node revhead1 = reverse(head1);
        Node revhead2 = reverse(head2);

        System.out.println("print nodes after reversing");
        print(revhead1);
        print(revhead2);

        //-------------start adding from head
        Node p1 =revhead1;
        Node p2 = revhead2;
        Node result = null;
        int sum=0;
        int remainder=0;
        int carry=0;
        Node prev = null;

        while(p1 != null &&  p2 != null){
            sum = p1.value + p2.value + carry;
            remainder = sum % 10;
            carry = sum / 10;

            Node tmpnode = new Node(remainder, null);
            if(prev == null){
                result = tmpnode;
            }else {
                prev.next = tmpnode;
            }
            prev = tmpnode;
            p1 = p1.next;
            p2 = p2.next;
        }

        print(result);

        Node answerNode = reverse(result);

        System.out.println("final answer is : ");
        print(answerNode);

    }

    public static Node reverse(Node node)
    {
        Node current = node;
        Node prev = null;
        Node next = null;

        while(current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    public static int length(Node node)
    {
        int length=0;
        while(node != null){
            length++;
            node = node.next;
        }
        return length;
    }

    public static void print(Node node){
        while(node != null){
            System.out.print(node.value + " -> ");
            node = node.next;
        }
        System.out.println();
    }
}
