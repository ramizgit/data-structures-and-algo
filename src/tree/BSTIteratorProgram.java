package tree;

import java.util.Stack;

public class BSTIteratorProgram {
    public static void main(String[] args)
    {
        Node node6 = new Node(6, null, null);
        Node node8 = new Node(8, null, null);
        Node node7 = new Node(7, node6, node8);
        Node node4 = new Node(4, null, null);
        Node node2 = new Node(2, null, null);
        Node node3 = new Node(3, node2, node4);
        Node node = new Node(5, node3, node7);

        BSTIterator iterator = new BSTIterator(node);

        while (iterator.hasNext()){
            System.out.print(iterator.next()+ " ");
        }

        System.out.println();

        BSTIterator reverseIterator = new BSTIterator(node, true);

        while (reverseIterator.hasNext()){
            System.out.print(reverseIterator.next()+ " ");
        }
    }
}

class BSTIterator{
    private Stack<Node> stack = new Stack<>();
    private boolean isReverseIterator;

    public BSTIterator(Node root) {
        this.isReverseIterator = false;
        this.pushIntoStack(root);
    }

    public BSTIterator(Node root, boolean isReverseIterator) {
        this.isReverseIterator = isReverseIterator;
        this.pushIntoStack(root);
    }

    private void pushIntoStack(Node node){
        while(node != null){
            this.stack.push(node);
            if(this.isReverseIterator){
                node = node.right;
            }else {
                node = node.left;
            }
        }
    }

    public boolean hasNext(){
        return !this.stack.empty();
    }

    public int next(){
        Node pop = this.stack.pop();

        if(this.isReverseIterator){
            pushIntoStack(pop.left);
        }else {
            pushIntoStack(pop.right);
        }
        return pop.value;
    }
}

class BSTReverseIterator{
    public Stack<Node> stack = new Stack<>();

    public BSTReverseIterator(Node root) {
        this.pushIntoStack(root);
    }

    private void pushIntoStack(Node node){
        while(node != null){
            this.stack.push(node);
            node = node.right;
        }
    }

    public boolean hasNext(){
        return !this.stack.empty();
    }

    public int next(){
        Node pop = this.stack.pop();

        //push left child and then all of the right children
        if(pop.left != null){
            pushIntoStack(pop.left);
        }

        return pop.value;
    }
}
