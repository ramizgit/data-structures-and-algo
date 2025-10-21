package stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveAllAdjacentDupsInStringii {
    //https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/description/

    public static void main(String[] args)
    {
        System.out.println(removeDuplicates("deeedbbcccbdaa", 3)); //aa
        System.out.println(removeDuplicates("abcd", 2)); //abcd
        System.out.println(removeDuplicates("pbbcggttciiippooaais", 2)); //ps
    }

    private static String removeDuplicates(String s, int k)
    {
        Deque<CharCountPair> stack = new ArrayDeque<>();

        for(char ch : s.toCharArray()){
            if(!stack.isEmpty() && ch == stack.peek().ch){
                stack.peek().count++;
                if(stack.peek().count == k){
                    stack.pop();
                }
            }else{
                stack.push(new CharCountPair(ch, 1));
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            CharCountPair pop = stack.pop();
            for(int i=0; i<pop.count; i++){
                sb.append(pop.ch);
            }
        }

        return sb.reverse().toString();
    }
}

class CharCountPair{
    char ch;
    int count;

    public CharCountPair(char ch, int count) {
        this.ch = ch;
        this.count = count;
    }
}
