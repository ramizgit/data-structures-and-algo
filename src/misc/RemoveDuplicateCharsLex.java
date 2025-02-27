package array;

import java.util.*;

public class RemoveDuplicateCharsLex {

    public static void main(String[] args)
    {
        System.out.println(removeDuplicateChars("bcabc"));//abc
        System.out.println(removeDuplicateChars("cbacdcbc"));//acdb
    }

    private static String removeDuplicateChars(String input)
    {
        //build frequency map
        Map<Character, Integer> freq = new HashMap<>();
        for(char ch : input.toCharArray()){
            freq.put(ch, freq.getOrDefault(ch, 0)+1);
        }

        Set<Character> visited = new HashSet<>();
        Stack<Character> stack = new Stack<>();

        StringBuilder output = new StringBuilder();

        for(char ch : input.toCharArray()){
            if(visited.contains(ch)){
                freq.put(ch, freq.get(ch)-1);
            }else {
                while (!stack.empty() && ch < stack.peek() && freq.get(stack.peek())>0){
                    visited.remove(stack.pop());
                }
                stack.push(ch);
                freq.put(ch, freq.get(ch)-1);
                visited.add(ch);
            }
        }

        while (!stack.empty()){
            output.append(stack.pop());
        }

        return output.reverse().toString();
    }
}
