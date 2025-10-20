package stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinimumStack {

    public static void main(String[] args)
    {
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(2);
        minStack.push(0);
        System.out.println(minStack.getMin()); // return 0
        minStack.pop();
        System.out.println(minStack.top()); // return 2
        System.out.println(minStack.getMin()); // return 1
    }
}

class MinStack {

    //main stack that stores all values
    Deque<Integer> stack;

    //min stack that maintains the minimum value at each corresponding level
    Deque<Integer> min;

    public MinStack() {
        stack = new ArrayDeque<>();
        min = new ArrayDeque<>();
    }

    public void push(int val) {
        stack.push(val);

        if (min.isEmpty()) {
            min.push(val);
        } else {
            min.push(Math.min(val, min.peek()));
        }
    }

    public void pop() {
        if(!stack.isEmpty()){
            stack.pop();
            min.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min.peek();
    }
}
