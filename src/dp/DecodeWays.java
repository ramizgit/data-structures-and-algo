package dp;

public class DecodeWays {
    //https://leetcode.com/problems/decode-ways/description/

    public static void main(String[] args)
    {
        System.out.println(numDecodings("06"));
        System.out.println(numDecodings("1"));
        System.out.println(numDecodings("12"));
        System.out.println(numDecodings("123"));
    }

    private static int numDecodings(String s)
    {
        int n = s.length();
        int[] dp = new int[n+1];

        //empty string handling
        dp[0] = 1;

        //first char handling
        char ch = s.charAt(0);
        if(ch == '0'){
            return 0;
        }
        dp[1] = 1;

        for(int i=2; i<=n; i++){
            int currOneDigit = Integer.valueOf(s.charAt(i-1));
            int currTwoDigit = Integer.valueOf(s.substring(i-2, i));

            if(currOneDigit >= 1){
                dp[i] = dp[i-1];
            }

            if(currTwoDigit >= 10 && currTwoDigit <= 26){
                dp[i] += dp[i-2];
            }
        }

        return dp[n];
    }
}
