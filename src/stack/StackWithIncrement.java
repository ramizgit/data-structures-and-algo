package stack;

import java.util.*;

public class StackWithIncrement {

    //todo : practice

    List<Integer> stack;
    List<Integer> increment;

    public StackWithIncrement(){
        this.stack = new ArrayList<>();
        this.increment = new ArrayList<>();
    }

    public void push(int value) {
        // push onto stack
        this.stack.addLast(value);
        this.increment.addLast(0);
    }

    public int pop() {
        // return top element
        // return -1 if empty
        if(this.stack.isEmpty()){
            return -1;
        }

        // Pending increment for the top element
        int topIncrement = increment.removeLast();

        // Propagate it to the element below (if any)
        if (!increment.isEmpty()) {
            int lastIndex = increment.size() - 1;
            increment.set(lastIndex, increment.get(lastIndex) + topIncrement);
        }

        return stack.removeLast() + topIncrement;
    }

    public int peek() {
        // return top element
        // return -1 if empty
        if(this.stack.isEmpty()){
            return -1;
        }

        return this.stack.getLast() + this.increment.getLast();
    }

    public void increment(int k, int val) {
        // increment bottom k elements by val
        if (stack.isEmpty()) {
            return;
        }

        // If k > size, increment all elements
        int index = Math.min(k, stack.size()) - 1;

        increment.set(index, increment.get(index) + val);
    }
}
