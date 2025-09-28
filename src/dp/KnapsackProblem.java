package dp;

public class KnapsackProblem {

    public static void main(String[] args)
    {
        maximiseValue(new int[]{12, 10, 20}, new int[] {2, 1, 3}, 5);
    }

    private static void maximiseValue(int[] values, int[] weights, int capacity)
    {
        int m = values.length;
        int n = capacity;

        int[][] dp = new int[m+1][n+1];
        print(dp);

        //fill dp
        for(int i=1; i<=m; i++){
            for(int w=1; w<=n; w++){
                if(weights[i-1] <= w){
                    dp[i][w] = Math.max(
                                    values[i-1]+dp[i-1][w-weights[i-1]], //pick
                                    dp[i-1][w] //don't pick
                                );
                }else{
                    dp[i][w] = dp[i-1][w]; //don't pick as item is too heavy
                }
            }
        }
        print(dp);

        System.out.println("answer : " + dp[m][n]);
    }

    private static void print(int[][] dp)
    {
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
