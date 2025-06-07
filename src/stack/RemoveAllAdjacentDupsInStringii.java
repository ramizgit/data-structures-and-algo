package stack;

import java.util.Stack;

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
        Stack<CharCountPair> stack = new Stack<>();

        for(char ch : s.toCharArray()){
            if(!stack.empty() && ch == stack.peek().ch){
                CharCountPair pair = stack.pop();
                if(pair.count < k - 1){
                    stack.push(new CharCountPair(ch, pair.count + 1));
                }
            }else {
                stack.push(new CharCountPair(ch, 1));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.empty()){
            CharCountPair pair = stack.pop();
            for(int i=0; i<pair.count; i++){
                sb.append(pair.ch);
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

