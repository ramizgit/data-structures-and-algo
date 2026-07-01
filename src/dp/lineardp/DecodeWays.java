package consistenthashing.dp.lineardp;

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
        int[] dp = new int[n+1]; //dp[i] is the number of ways to decode the first i characters of the string
        dp[0] = 1; //base case - empty string has only one way of valid decoding
        dp[1] = input.charAt(0) == '0' ? 0 : 1;

        for(int i=2; i<=n; i++){
            //int oneDigit = Integer.parseInt(input.substring(i-1, i));
            int oneDigit = input.charAt(i - 1) - '0';
            if(oneDigit >= 1){
                dp[i] += dp[i-1];
            }

            //int twoDigits = Integer.parseInt(input.substring(i-2, i));
            int twoDigits = (input.charAt(i - 2) - '0') * 10 + (input.charAt(i - 1) - '0');
            if(twoDigits >= 10 && twoDigits <= 26){
                dp[i] += dp[i-2];
            }
        }

        return dp[n];
    }
}
