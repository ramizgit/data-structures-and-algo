package stack;

import java.util.*;

public class StackWithIncrement{

    List<Integer> stack;
    List<Integer> increment;

    public StackWithIncrement(){
        this.stack = new ArrayList<>();
        this.increment = new ArrayList<>();
    }

    public void push(int value) {
        this.stack.addLast(value); // push onto stack
        this.increment.addLast(0); //push into increment array also to keep size same
    }

    public int pop() {

        if(this.stack.isEmpty()){
            return -1; //return -1 if empty
        }

        //pending increment for the top element
        int topIncrement = increment.removeLast();

        //propagate it to the element below (if any)
        if (!increment.isEmpty()) {
            int lastIndex = increment.size() - 1;
            increment.set(lastIndex, increment.get(lastIndex) + topIncrement);
        }

        return stack.removeLast() + topIncrement;
    }

    public int peek() {

        if(this.stack.isEmpty()){
            return -1;  //return -1 if empty
        }

        return this.stack.getLast() + this.increment.getLast();
    }

    public void increment(int k, int val) {
        // increment bottom k elements by val
        if (stack.isEmpty()) {
            return;
        }

        //if k > size, increment all elements
        int index = Math.min(k, stack.size()) - 1; //-1 due to zero-based index
        int existingVal = increment.get(index);

        increment.set(index, existingVal + val);
    }
}
