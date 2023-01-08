package tree;

import java.util.LinkedList;
import java.util.Queue;

public class PopulateNextRightPointer {
    public static void main(String[] args)
    {
        TNode node7 = new TNode(7, null, null, null);
        TNode node6 = new TNode(6, null, null, null);
        TNode node5 = new TNode(5, null, null, null);
        TNode node4 = new TNode(4, null, null, null);

        TNode node3 = new TNode(3, node6, node7, null);
        TNode node2 = new TNode(2, node4, node5, null);
        TNode node1 = new TNode(1, node2, node3, null);

        populateNextRightPointer(node1);
        System.out.println("done");
    }

    public static void populateNextRightPointer(TNode node)
    {
        if(node == null){
            return;
        }

        Queue<TNode> queue = new LinkedList<>();
        queue.add(node);
        queue.add(null);

        while (!queue.isEmpty()){
            TNode poll = queue.poll();

            if(poll != null){
                //populate next pointer
                poll.next = queue.peek();

                //bfs, add children in the queue
                if(poll.left != null){
                    queue.add(poll.left);
                }
                if(poll.right != null){
                    queue.add(poll.right);
                }
            }else if(queue.size() > 1){
                queue.add(null);
            }
        }
    }

    static class TNode {
        public int val;
        public TNode left;
        public TNode right;
        public TNode next;

        public TNode() {}

        public TNode(int _val) {
            val = _val;
        }

        public TNode(int _val, TNode _left, TNode _right, TNode _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
