package tree;

public class DistributeCoinsInBinaryTree {
    public static void main(String[] args)
    {
        Node node3 = new Node(3, null, null);
        Node node2 = new Node(0, null, null);
        Node node = new Node(0, node2, node3);

        int[] numOfMoves = new int[1];
        getMinimumNumOfMoves(node, numOfMoves);
        System.out.println("num of moves : "+numOfMoves[0]); //3

        Node node10 = new Node(0, null, null);
        Node node9 = new Node(0, null, null);
        Node node8 = new Node(7, null, null);
        Node node7 = new Node(0, null, null);
        Node node6 = new Node(0, node9, node10);
        Node node5 = new Node(0, node7, node8);
        Node node4 = new Node(0, node5, node6);

        int[] numOfMove = new int[1];
        getMinimumNumOfMoves(node4, numOfMove);
        System.out.println("num of moves : "+numOfMove[0]); //16
    }

    public static void getMinimumNumOfMoves(Node node, int[] numOfMove)
    {
        dfs(node, node, numOfMove);
    }

    public static void dfs(Node current, Node parent, int[] numOfMove)
    {
        if(current == null){
            return;
        }

        dfs(current.left, current, numOfMove);
        dfs(current.right, current, numOfMove);

        if(current.value > 1){
            //keep only one coin and move rest to the parent
            int extraCoins = current.value - 1;
            current.value = 1;
            parent.value += extraCoins;
            numOfMove[0] += extraCoins;
        }else if (current.value < 1){
            //pull coins from parent
            int neededCoins = 1 + Math.abs(current.value);
            current.value = 1;
            parent.value -= neededCoins;
            numOfMove[0] += neededCoins;
        }
    }
}
