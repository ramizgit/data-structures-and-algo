package stack;

import java.util.*;

public class DecodeString {

    //https://leetcode.com/problems/decode-string/description/

    //todo:practice it

    private static String decodeString(String s)
    {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder curr = new StringBuilder();
        int num = 0;

        for(char ch : s.toCharArray()){
            
            if(Character.isDigit(ch)){
                num = num * 10 + (ch - '0');
            }
            else if(ch == '['){
                //save current state
                countStack.push(num);
                strStack.push(curr);

                //reset state for new sequence
                num = 0;
                curr = new StringBuilder();
            }
            else if(ch == ']'){
                int repeat = countStack.pop();
                StringBuilder prev = strStack.pop();

                //append curr char seq repeat times
                while(repeat > 0){
                    prev.append(curr);
                    repeat--;
                }
                curr = prev;
            }
            else {
                curr.append(ch);
            }
        }
        
        return curr.toString();
    }
}
