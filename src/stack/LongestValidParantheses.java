package stack;

public class LongestValidParantheses {
    public static void main(String[] args)
    {
        System.out.println(getLongestValidParantheses("()(())"));
        System.out.println(getLongestValidParantheses("(()"));
        System.out.println(getLongestValidParantheses(")()())"));
        System.out.println(getLongestValidParantheses(")(()))"));
        System.out.println(getLongestValidParantheses(""));
    }

    public static int getLongestValidParantheses(String input)
    {
        int left = 0;
        int right = 0;
        int max = 0;

        //left pass
        for(int i=0; i<input.length(); i++){
            if(input.charAt(i) == '('){
                left++;
            }else{
                right++;
            }

            if(left == right){
                max = Math.max(max, left * 2);
            }else if(right > left){
                //reset
                left = 0;
                right = 0;
            }
        }

        //right pass
        left = 0;
        right = 0;
        for(int i=input.length()-1; i>=0; i--){
            if(input.charAt(i) == '('){
                left++;
            }else{
                right++;
            }

            if(left == right){
                max = Math.max(max, left * 2);
            }else if(left > right){
                //reset
                left = 0;
                right = 0;
            }
        }

        return max;
    }
}
