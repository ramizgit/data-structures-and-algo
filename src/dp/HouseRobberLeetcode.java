package dp;

public class HouseRobberLeetcode {

    public int maxrobDp(int[] arr)
    {
        //input validation
        if(arr == null || arr.length == 0){
            return 0;
        }

        if(arr.length == 1){
            return arr[0];
        }

        if(arr.length == 2){
            return Math.max(arr[0], arr[1]);
        }

        int[] dp = new int[arr.length]; //dp[i] = max amount that can be robbed from houses 0..i
        dp[0] = arr[0]; //base case
        dp[1] = Math.max(arr[0], arr[1]);

        for(int i=2; i<arr.length; i++){

            dp[i] = Math.max(
                    arr[i] + dp[i-2], // rob current house
                    dp[i-1] // skip current house
            );
        }

        return dp[dp.length-1];
    }

    private static int maxrob(int[] arr)
    {
        if(arr == null || arr.length == 0){
            return 0;
        }

        if(arr.length == 1){
            return arr[0];
        }

        if(arr.length == 2){
            return Math.max(arr[0], arr[1]);
        }

        int max = 0;
        int m1 = arr[0];
        int m2 = Math.max(arr[0], arr[1]);

        for(int i=2; i<arr.length; i++){
            max = Math.max(arr[i] + m1, m2);
            m1 = m2;
            m2 = max;
        }

        return max;
    }
}
