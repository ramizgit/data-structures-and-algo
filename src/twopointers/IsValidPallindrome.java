package pallindrome;

public class IsValidPallindrome {

    public static void main(String[] args)
    {
        /*
        A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, 
        it reads the same forward and backward. Alphanumeric characters include letters and numbers.
        Given a string s, return true if it is a palindrome, or false otherwise.
         */
        System.out.println(isValidPallindrome("A man, a plan, a canal: Panama"));
    }

    private static boolean isValidPallindrome(String input)
    {
        int left = 0;
        int right = input.length()-1;

        while (left < right)
        {
            while (left < right && !isAlphaNum(input.charAt(left))){
                left++;
            }

            while (right > left && !isAlphaNum(input.charAt(right))){
                right--;
            }

            if(Character.toLowerCase(input.charAt(left)) != Character.toLowerCase(input.charAt(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private static boolean isAlphaNum(char ch)
    {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9');
    }
}
