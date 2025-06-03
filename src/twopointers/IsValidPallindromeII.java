package pallindrome;

public class IsValidPallindromeII {

    public static void main(String[] args)
    {
        /*
        Given a string s, return true if the s can be palindrome after deleting at most one character from it.
         */
        System.out.println(validPallindrome("aba"));
        System.out.println(validPallindrome("abc"));
        System.out.println(validPallindrome("zaba"));
        System.out.println(validPallindrome("azba"));
    }

    private static boolean validPallindrome(String input)
    {
        int left = 0;
        int right = input.length()-1;

        while (left < right){
            if(input.charAt(left) != input.charAt(right)){
                return isPallindrome(input.substring(left+1, right+1)) || isPallindrome(input.substring(left, right));
            }
            left++;
            right--;
        }

        return true;
    }

    private static boolean isPallindrome(String input)
    {
        int left = 0;
        int right = input.length()-1;

        while (left < right){
            if(input.charAt(left) != input.charAt(right)){
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
