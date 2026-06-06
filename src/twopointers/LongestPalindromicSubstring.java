package consistenthashing.twopointers;

public class LongestPalindromicSubstring {

    //https://leetcode.com/problems/longest-palindromic-substring/description/

    //just get the length
    public int longestPalindromeLen(String s)
    {
        //input validation
        if(s == null || s.isEmpty()){
            return 0;
        }

        int maxLen = 0;

        for(int i=0; i<s.length(); i++){

            //odd length pallindrome
            maxLen = Math.max(maxLen, expand(s, i, i));

            //even length pallindrom
            maxLen = Math.max(maxLen, expand(s, i, i+1));
        }

        return maxLen;
    }

    //get pallindromic substring
    public String longestPalindrome(String s)
    {
        //input validation
        if(s == null || s.isEmpty()){
            return "";
        }

        int n = s.length();
        int start = -1;
        int maxLen = 0;

        for(int i=0; i<n; i++){

            //odd length pallindrome
            int odd = Math.max(maxLen, expand(s, i, i));

            //even length pallindrome
            int even = Math.max(maxLen, expand(s, i, i+1));

            int length = Math.max(odd, even);

            if(length > maxLen){
                start = i - (n-1)/2;
            }
        }

        return s.substring(start, start + maxLen);
    }

    private int expand(String s, int left, int right)
    {
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }

        return right - left - 1; //subtract 1 because both pointers moved one step beyond the palindrome boundary
    }
}
