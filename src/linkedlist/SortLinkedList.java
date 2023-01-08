package linkedlist;

public class SortLinkedList {
    public static void main(String[] args)
    {

        //O(n^2)

        Node node9 = new Node(9, null);
        Node node6 = new Node(6, node9);
        Node node8 = new Node(8, node6);
        Node node7 = new Node(7, node8);
        Node node1 = new Node(1, node7);
        Node node5 = new Node(5, node1);
        Node node3 = new Node(3, node5);

        print(node3);

        print(sort(node3));

        Node node55 = new Node(1, null);
        Node node44 = new Node(2, node55);
        Node node33 = new Node(3, node44);
        Node node22 = new Node(4, node33);
        Node node11 = new Node(5, node22);

        print(node11);

        print(sort(node11));
    }

    public static Node sort(Node node)
    {
        if(node == null || node.next == null){
            return node;
        }

        Node head = node;
        Node lastSorted = node;
        Node toBeSorted = node.next;

        while (toBeSorted != null){

            if(toBeSorted.value >= lastSorted.value){
                //do nothing, just increment pointers
                lastSorted = toBeSorted;
                toBeSorted = toBeSorted.next;
            }
            else if(toBeSorted.value < head.value){
                //tobesorted node is less than head, bring it before head and switch head
                lastSorted.next = toBeSorted.next;
                toBeSorted.next = head;
                head = toBeSorted;
                toBeSorted = lastSorted.next;
            }else {
                //tobesorted is in between head and lastsorted, scan the list since beginning
                Node tmpnode = head;
                while(tmpnode != toBeSorted && tmpnode.next.value < toBeSorted.value){
                    tmpnode = tmpnode.next;
                }

                lastSorted.next = toBeSorted.next;
                toBeSorted.next = tmpnode.next;
                tmpnode.next = toBeSorted;

                toBeSorted = lastSorted.next;
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
