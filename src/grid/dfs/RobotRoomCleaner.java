package grid.dfs;

import java.util.HashSet;
import java.util.Set;

public class RobotRoomCleaner {

    //https://leetcode.com/problems/robot-room-cleaner/description

    //todo : implement

    class Solution {

        // up, right, down, left
        private final int[][] DIRS = {
                {-1, 0},
                {0, 1},
                {1, 0},
                {0, -1}
        };

        private Set<String> visited = new HashSet<>();

        public void cleanRoom(Robot robot) {
            dfs(robot, 0, 0, 0);
        }

        private void dfs(Robot robot, int row, int col, int dir) {

            robot.clean();
            visited.add(row + "," + col);

            // Try all 4 directions
            for (int i = 0; i < 4; i++) {

                int newDir = (dir + i) % 4; //boundary check

                int newRow = row + DIRS[newDir][0];
                int newCol = col + DIRS[newDir][1];

                // Move only if not visited and not blocked
                if (!visited.contains(newRow + "," + newCol) && robot.moveForward()) {

                    dfs(robot, newRow, newCol, newDir);

                    goBack(robot); // Backtrack to previous cell
                }

                // Rotate to face next direction
                robot.turnRight();
            }
        }

        private void goBack(Robot robot) {

            robot.turnRight();
            robot.turnRight();

            robot.moveForward();

            //After returning, we want the robot to be in exactly the same state (same cell, same orientation) so that the DFS loop can continue trying the remaining directions.
            //hence turn right again
            robot.turnRight();
            robot.turnRight();
        }
    }

    interface Robot {
        boolean moveForward();
        void turnLeft();
        void turnRight();
        void clean();
    }
}
