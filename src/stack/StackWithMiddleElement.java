package stack;

import java.util.LinkedList;

public class StackWithMiddleElement {

    public static void main(String[] args)
    {
        StackWithMiddle stack = new StackWithMiddle();
        stack.push(1);
        System.out.println(stack.peekMiddle());
        stack.push(2);
        System.out.println(stack.peekMiddle());
        stack.push(3);
        System.out.println(stack.peekMiddle());
        stack.push(4);
        System.out.println(stack.peekMiddle());
        stack.push(5);
        System.out.println(stack.peekMiddle());
        stack.push(6);
        System.out.println("peek middle ="+ stack.peekMiddle());

        System.out.println("peek = "+stack.peek());
        System.out.println("pop = "+stack.pop());
        System.out.println("peek middle ="+ stack.peekMiddle());

        System.out.println("pop middle ="+ stack.popMiddle());
        System.out.println("peek middle ="+ stack.peekMiddle());
    }
}
class StackWithMiddle{

    LinkedList<Integer> linkedList = new LinkedList<>();
    private int middle = 0;

    public void push(Integer item) {
        this.linkedList.addLast(item);
        this.updateMiddleIndex();
    }

    public int pop() {
        int pop = this.linkedList.removeLast();
        this.updateMiddleIndex();
        return pop;
    }

    public int peek(){
        return this.linkedList.getLast();
    }

    public int peekMiddle() {
        return this.linkedList.get(middle);
    }

    public int popMiddle(){
        int pop = this.linkedList.remove(middle);
        this.updateMiddleIndex();
        return pop;
    }

    private void updateMiddleIndex() {
        this.middle = (this.linkedList.size())/2;
    }
}
