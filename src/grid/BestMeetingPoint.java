package grid;

import java.util.*;

public class BestMeetingPoint {

    //https://leetcode.com/problems/best-meeting-point/description/

  //todo : practice

    /*APPRAOCH:-
    We collect row and column indices of all people. Since median minimizes total Manhattan distance,
    we sort these indices and pick the middle
     */

    public int minTotalDistance(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();

        // Collect row indices (already sorted because we traverse row-wise)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                }
            }
        }

        // Collect column indices
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 1) {
                    cols.add(j);
                }
            }
        }

        // Find medians
        int medianRow = rows.get(rows.size() / 2);
        int medianCol = cols.get(cols.size() / 2);

        // Compute total distance
        int distance = 0;

        for (int r : rows) {
            distance += Math.abs(r - medianRow);
        }

        for (int c : cols) {
            distance += Math.abs(c - medianCol);
        }

        return distance;
    }
}
