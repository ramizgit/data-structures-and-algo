import java.util.LinkedList;
import java.util.Queue;

public class PrintTreeLevelByLevel {
    public static void main(String[] args)
    {
        Node node10 = new Node(10, null, null);
        Node node11 = new Node(11, null, null);
        Node node7 = new Node(7, node10, node11);
        Node node6 = new Node(6, null, null);
        Node node5 = new Node(5, null, null);
        Node node4 = new Node(4, null, null);
        Node node3 = new Node(3, node6, node7);
        Node node2 = new Node(2, node4, node5);
        Node node = new Node(1, node2, node3);

        printTreeLevelByLevel(node);

    }

    public static void printTreeLevelByLevel(Node node)
    {
        if(node == null)
        {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty())
        {
            Node tmpnode = queue.poll();
            System.out.print(tmpnode.value);
            if(tmpnode.left != null)
            {
                queue.add(tmpnode.left);
            }
            if(tmpnode.right != null)
            {
                queue.add(tmpnode.right);
            }
        }
    }
}

class Node{
    int value;
    Node left;
    Node right;

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
