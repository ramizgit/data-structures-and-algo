package dp;

public class DecodeWays {

    //https://leetcode.com/problems/decode-ways/description/

    public int numDecodings(String input)
    {
        int n = input.length();
        int[] dp = new int[n+1]; //dp[i] is the number of ways to decode the first i characters of the string
        dp[0] = 1; //base case - empty string has only one way of valid decoding
        dp[1] = input.charAt(0) == '0' ? 0 : 1;

        for(int i=2; i<=n; i++){
            int oneDigit = Integer.parseInt(input.substring(i-1, i));
            if(oneDigit >= 1){
                dp[i] += dp[i-1];
            }

            int twoDigits = Integer.parseInt(input.substring(i-2, i));
            if(twoDigits >= 10 && twoDigits <= 26){
                dp[i] += dp[i-2];
            }
        }

        return dp[n];
    }
}
