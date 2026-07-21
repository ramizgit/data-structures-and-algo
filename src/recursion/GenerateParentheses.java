package recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static void main(String[] args)
    {
        List<String> output1 = new ArrayList<>();
        generateParantheses(1, 0, 0, "", output1);
        System.out.println(output1);

        List<String> output2 = new ArrayList<>();
        generateParantheses(2, 0, 0, "", output2);
        System.out.println(output2);

        List<String> output3 = new ArrayList<>();
        generateParantheses(3, 0, 0, "", output3);
        System.out.println(output3);

        List<String> output4 = new ArrayList<>();
        generateParantheses(4, 0, 0, "", output4);
        System.out.println(output4);
    }

    /*
    Time Complexity:
    - O(Cn * n), where Cn is the nth Catalan number.
    - There are Cn valid parentheses combinations, and each combination has length 2n.
    - Using StringBuilder ensures append/delete operations are O(1), so each valid string takes O(n) to construct.

    Space Complexity:
    - O(n) auxiliary space for the recursion stack and StringBuilder.
    - O(Cn * n) space for storing the final output list (not counted as auxiliary space).
    */

    public static void generateParantheses(int input, int open, int close, String currentState, List<String> output)
    {
        //if current parantheses string has reached max limit of 2*input, add to final output list
        if(currentState.length() == input * 2)
        {
            output.add(currentState);
            return;
        }

        //keep on putting open parantheses till it exhausts input limit, then it will backtrack recursively
        if(open < input)
        {
            generateParantheses(input, open+1, close, currentState + "(", output);
        }

        //put close parantheses to match open parantheses
        if(close < open){
            generateParantheses(input, open, close+1, currentState + ")", output);
        }
    }
}
