package dp;

public class HouseRobberLeetcode {
    public static void main(String[] args)
    {
        System.out.println(maxrob(new int[]{1,2,3,1}));//4
        System.out.println(maxrobDp(new int[]{1,2,3,1}));//4

        System.out.println(maxrob(new int[]{2,7,9,3,1}));//12
        System.out.println(maxrobDp(new int[]{2,7,9,3,1}));//12

        System.out.println(maxrob(new int[]{10,1,12,50}));//60
        System.out.println(maxrobDp(new int[]{10,1,12,50}));//60
    }

    private static int maxrobDp(int[] arr)
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

        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        for(int i=2; i<arr.length; i++){
            dp[i] = Math.max(arr[i] + dp[i-2], dp[i-1]);
        }

        return dp[arr.length-1];
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
