package recursion;

import java.util.*;

public class RemoveInvalidParentheses {
    public static void main(String[] args)
    {
        System.out.println(removeInvalidParentheses("()())()"));
        System.out.println(removeInvalidParentheses("(a)())()"));
        System.out.println(removeInvalidParentheses(")("));
        System.out.println(removeInvalidParentheses("()())())"));
    }

    public static List<String> removeInvalidParentheses(String s)
    {
        int minNumOfRemovals = getMinNumOfRemovals(s);
        List<String> result = new ArrayList<>();

        solve(s, new HashSet<>(), minNumOfRemovals, result);

        return result;
    }

    private static void solve(String str, Set<String> visited, int removals, List<String> result)
    {
        if(visited.contains(str)){
            return;
        }
        visited.add(str);

        if(removals == 0){
            if(getMinNumOfRemovals(str) == 0){
                result.add(str);
            }
        }

        for(int i=0; i<str.length(); i++){
            String left = str.substring(0, i);
            String right = str.substring(i+1);
            String s = left + right;
            solve(s, visited, removals-1, result);
        }
    }

    private static int getMinNumOfRemovals(String s)
    {
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(ch == '('){
                stack.push(ch);
            }else if(ch == ')'){
                if(!stack.empty() && stack.peek() == '('){
                    stack.pop();
                }else {
                    stack.push(ch);
                }
            }
        }

        return stack.size();
    }
}
