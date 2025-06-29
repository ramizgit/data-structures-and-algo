package linkedlist;

public class MergeTwoLists {
    //https://leetcode.com/problems/merge-two-sorted-lists/description/
    public static void main(String[] args)
    {
        Node node5 = new Node(5, null);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);
        Node.print(node1);

        Node node51 = new Node(8, null);
        Node node41 = new Node(7, node51);
        Node node31 = new Node(6, node41);
        Node node21 = new Node(4, node31);
        Node node11 = new Node(2, node21);
        Node.print(node11);

        Node merged = merge(node1, node11);
        Node.print(merged);
    }

    private static Node merge(Node head1, Node head2)
    {
        Node newHead = new Node();
        Node list1 = head1;
        Node list2 = head2;
        Node prev = newHead;

        while (list1 != null && list2 != null){
            if(list1.value < list2.value){
                prev.next = list1;
                list1 = list1.next;
            }else {
                prev.next = list2;
                list2 = list2.next;
            }

            prev = prev.next;
        }

        prev.next = list1 != null ? list1 : list2;

        return newHead.next;
    }
}
