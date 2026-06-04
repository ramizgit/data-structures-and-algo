package stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinimumStack {

    class MinStack {

        //main stack that stores all values
        Deque<Integer> stack;

        //min stack that maintains the minimum value at each corresponding level
        Deque<Integer> minStack;

        public MinStack() {
            stack = new ArrayDeque<>();
            minStack = new ArrayDeque<>();
        }

        public void push(int val) {
            stack.push(val);

            if (minStack.isEmpty()) {
                minStack.push(val);
            } else {
                minStack.push(Math.min(val, minStack.peek()));
            }
        }

        public void pop() {
            if(!stack.isEmpty()){
                stack.pop();
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
