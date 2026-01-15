package dp;

public class KnapsackProblem {

    public static void main(String[] args)
    {
        maximiseValue(new int[]{12, 10, 20}, new int[] {2, 1, 3}, 5);
    }

    // using 2-D dp array
    private static void maximiseValue(int[] values, int[] weights, int capacity)
    {
        /*
            v  w    0   1   2   3   4   5    (c)
            0  0    0   0   0   0   0   0
            12 2    0   0   12  12  12  12
            10 1    0   10  12  22  22  22
            20 3    0   10  12  22  30  32
         */

        int m = weights.length;
        int n = capacity;

        int[][] dp = new int[m+1][n+1];
        print(dp);

        //fill dp
        for(int w=1; w<=m; w++){
            for(int c=1; c<=n; c++){
                if(weights[w-1] <= c){
                    dp[w][c] = Math.max(
                                    values[w-1]+dp[w-1][c-weights[w-1]], //pick
                                    dp[w-1][c] //don't pick
                                );
                }else{
                    dp[w][c] = dp[w-1][c]; //don't pick as item is too heavy
                }
            }
        }
        print(dp);

        System.out.println("answer : " + dp[m][n]);
    }

    // using 1-D dp array
    private static void maximiseValue2(int[] values, int[] weights, int capacity)
    {
        int m = weights.length;
        int n = capacity;

        // dp[c] = max value with capacity c
        int[] dp = new int[n + 1];

        // fill dp
        for (int w = 0; w < m; w++) {
            // BACKWARD loop for 0/1 knapsack
            for (int c = capacity; c >= weights[w]; c--) {
                dp[c] = Math.max(
                        dp[c],                             // don't pick
                        values[w] + dp[c - weights[w]]     // pick
                );
            }
        }

        System.out.println("answer : " + dp[n]);
    }

    private static void print(int[][] dp)
    {
        System.out.println("============");

        int m = dp.length;
        int n = dp[0].length;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

    }
}
