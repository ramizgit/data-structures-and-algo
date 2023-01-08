package dp;

public class LongestPallindromeSubstring {
    public static void main(String[] args)
    {
        System.out.println(getLongestPallindromeSubstringLen("abbacd")); //4
        System.out.println(getLongestPallindromeSubstringLen("abbbbbbbacd")); //9
        System.out.println(getLongestPallindromeSubstringLen("abbacdzyyyyyyyyyyz")); //12
    }

    public static int getLongestPallindromeSubstringLen(String input)
    {
        int len = input.length();

        int[][] dp = new int[len][len];
        int max = 0;
        int start=0;
        int end=0;

        //populate all cells zero, diagonal as 1 as single char is always a pallindrome
        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                dp[i][j] = i == j ? 1 : 0;
            }
        }

        //populate two char pallindome also
        for(int i=0; i<len-1; i++){
            if(input.charAt(i) == input.charAt(i+1)){
                dp[i][i+1] = 1;
            }else {
                dp[i][i+1] = 0;
            }
        }

        //now populate remaining matrix using below formula
        //dp[i][j] == 1 if input[i]==input[j] && dp[i+1][j-1]==1

        int col=2;
        while(col < len){
            int j=col;
            for(int i=0; i<len && j<len; i++,j++){
                if(input.charAt(i) == input.charAt(j) && dp[i+1][j-1] == 1){
                    dp[i][j] = 1;
                    //max = Math.max(max, j-i+1);

                    if(max < (j-i+1)){
                        max = j-i+1;
                        start = i;
                        end = j;
                    }
                }else {
                    dp[i][j] = 0;
                }
            }
            col++;
        }

        System.out.println("max pallindrom is : "+input.substring(start, end+1));

        return max;
    }
}
