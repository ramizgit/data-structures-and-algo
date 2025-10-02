package dp;

public class DecodeWays {
    //https://leetcode.com/problems/decode-ways/description/
    public static void main(String[] args)
    {
        System.out.println(numDecodings("12"));
        System.out.println(numDecodings("226"));
        System.out.println(numDecodings("061"));
        System.out.println(numDecodings("10011"));
    }

    private static int numDecodings(String input)
    {
        int n = input.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = input.charAt(0) == '0' ? 0 : 1;

        for(int i=2; i<=n; i++){
            int oneDigit = Integer.parseInt(input.substring(i-1, i)); //input.charAt(i-1) - '0';
            if(oneDigit >= 1){
                dp[i] = dp[i-1];
            }

            int twoDigits = Integer.parseInt(input.substring(i-2, i));
            if(twoDigits >= 10 && twoDigits <= 26){
                dp[i] += dp[i-2];
            }
        }

        return dp[n];
    }
}
