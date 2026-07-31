package dp.recursivedp;

public class StudentAttendanceRecordII {

    //todo : practice

    private Integer[][][] dp;

    public int checkRecord(int n) {
        dp = new Integer[n][2][3];
        return dfs(0, 0, 0, n);
    }

    /*
     * State:
     * dfs(index, absent, late)
     *
     * index    -> current index
     * absent -> number of 'A's used so far (0 or 1)
     * late   -> consecutive trailing 'L's (0, 1, or 2)
     *
     * Returns the number of valid attendance records that can be formed
     * from this state.
     */
    private int dfs(int index, int absent, int late, int n) {

        // Successfully built one valid attendance record.
        if (index == n) {
            return 1;
        }

        if (dp[index][absent][late] != null) {
            return dp[index][absent][late];
        }

        int ans = 0;

        // Place 'P'
        ans += dfs(index + 1, absent, 0, n);

        // Place 'A' (allowed only once)
        if (absent == 0) {
            ans += dfs(index + 1, 1, 0, n);
        }

        // Place 'L' (cannot have 3 consecutive L's)
        if (late < 2) {
            ans += dfs(index + 1, absent, late + 1, n);
        }

        dp[index][absent][late] = ans;
        return ans;
    }
}
