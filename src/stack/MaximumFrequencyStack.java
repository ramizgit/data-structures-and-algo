package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class MaximumFrequencyStack {
    public static void main(String[] args)
    {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);

        System.out.println(freqStack.pop()); //5
        System.out.println(freqStack.pop()); //7
        System.out.println(freqStack.pop()); //5
        System.out.println(freqStack.pop()); //4
    }
}

class FreqStack {
    private Map<Integer, Integer> freq;
    private Map<Integer, Deque<Integer>> freqStack;
    private int maxFreq;

    public FreqStack() {
        this.freq = new HashMap<>();
        this.freqStack = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {
        int newFreq = freq.getOrDefault(val, 0) + 1;
        freq.put(val, newFreq);
        freqStack.computeIfAbsent(newFreq, key -> new ArrayDeque<>()).push(val);

        //update max freq
        maxFreq = Math.max(maxFreq, newFreq);
    }

    public int pop() {
        Deque<Integer> stack = freqStack.get(maxFreq);
        int val = stack.pop();

        //reduce freq
        freq.put(val, freq.get(val) - 1);

        // if no more elements at this frequency, decrease maxFreq
        if (stack.isEmpty()) {
            freqStack.remove(maxFreq); // optional, keeps map clean
            maxFreq--;
        }

        return val;
    }
}
