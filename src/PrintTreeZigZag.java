import java.util.Stack;

public class PrintTreeZigZag {
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

        printTreeZigZag(node);
    }

    public static void printTreeZigZag(Node node)
    {
        if(node == null)
        {
            return;
        }

        Stack<Node> oddLevelStack = new Stack<>();
        Stack<Node> evenLevelStack = new Stack<>();

        oddLevelStack.push(node);

        while (!oddLevelStack.empty() || !evenLevelStack.empty())
        {
            while(!oddLevelStack.empty())
            {
                Node tmpnode = oddLevelStack.pop();
                System.out.print(tmpnode.value);

                if(tmpnode.left != null)
                {
                    evenLevelStack.push(tmpnode.left);
                }
                if(tmpnode.right != null)
                {
                    evenLevelStack.push(tmpnode.right);
                }
            }

            while (!evenLevelStack.empty())
            {
                Node tmpnode = evenLevelStack.pop();
                System.out.print(tmpnode.value);
                if(tmpnode.right != null)
                {
                    oddLevelStack.push(tmpnode.right);
                }
                if(tmpnode.left != null)
                {
                    oddLevelStack.push(tmpnode.left);
                }
            }
        }
    }
}
