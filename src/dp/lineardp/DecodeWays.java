package dp.lineardp;

public class DecodeWays {

    //https://leetcode.com/problems/decode-ways/description/

    public int numDecodings(String input)
    {
        /*
        "Define dp[i] as the number of ways to decode the first i characters.
        For each position, look at the final decoding choice.
        The last character can either be decoded alone (if it's 1-9),  contributing dp[i-1] ways,
        or the last two characters can be decoded together (if they're 10-26), contributing dp[i-2] ways.
        Adding those gives the total ways for dp[i]."
         */

        int n = input.length();

        /*
        If dp[i] represents an array index, use n; if dp[i] represents a prefix of length i (first i elements/characters), use n + 1.
         */

        int[] dp = new int[n+1]; //dp[i] is the number of ways to decode the first i characters of the string

        dp[0] = 1; //base case - empty string has only one way of valid decoding
        dp[1] = input.charAt(0) == '0' ? 0 : 1;

        for(int i=2; i<=n; i++){

            // Choice 1: Decode the last character alone.
            // Every valid decoding of the first (i-1) characters can be extended by this digit.
            int oneDigit = input.charAt(i - 1) - '0';
            if(oneDigit >= 1){
                dp[i] += dp[i-1];
            }

            // Choice 2: Decode the last two characters together.
            // Every valid decoding of the first (i-2) characters can be extended by this two-digit letter.
            int twoDigits = (input.charAt(i - 2) - '0') * 10 + (input.charAt(i - 1) - '0');
            if(twoDigits >= 10 && twoDigits <= 26){
                dp[i] += dp[i-2];
            }
        }

        return dp[n];
    }
}
