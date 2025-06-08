package stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MaximumFrequencyStack {
    //https://leetcode.com/problems/maximum-frequency-stack/description/

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

    Map<Integer, Integer> keyFreq;
    Map<Integer, Deque<Integer>> freqList;
    int maxFreq = Integer.MIN_VALUE;

    public FreqStack() {
        this.keyFreq = new HashMap<>();
        this.freqList = new HashMap<>();
    }

    public void push(int val) {
        this.keyFreq.put(val, this.keyFreq.getOrDefault(val, 0) + 1);
        maxFreq = Math.max(maxFreq, this.keyFreq.get(val));
        this.freqList.computeIfAbsent(this.keyFreq.get(val), key -> new LinkedList<>()).addLast(val);
    }

    public int pop() {
        int popped = this.freqList.get(this.maxFreq).removeLast();
        this.keyFreq.put(popped, this.keyFreq.get(popped) - 1);

        if(this.freqList.get(maxFreq).isEmpty()){
            maxFreq--;
        }

        return popped;
    }
}
