package consistenthashing.graph.bfsZeroOne;

import java.util.*;

/*
Problem Statement

You are given an m x n grid containing:

'S'  -> start cell
'T'  -> target cell
'.'  -> empty cell
'#'  -> wall (cannot enter)
'a'...'z' -> teleporter cells

You can move:

up
down
left
right

to a valid non-wall cell.

Movement Cost

Normal move:

cost = 1
Teleportation

If you're standing on a teleporter cell, for example:

'a'

you may instantly move to any other 'a' cell.

Teleportation cost:

0
Important Constraint

Each teleporter letter can be used at most once globally.

Example:

a . . a . a

If you use the 'a' teleporter once, all 'a' teleporters become inactive forever.

You may still walk through 'a' cells normally.

Goal

Return the minimum cost to reach:

'S' -> 'T'

or:

-1

if impossible.

LeetCode-style Signature
public int minCost(char[][] grid)

Example:

char[][] grid = {
    {'S', '.', 'a', '.', '.'},
    {'#', '#', '#', '#', '.'},
    {'a', '.', '.', '.', '.'},
    {'#', '#', '#', '#', '.'},
    {'.', '.', '.', '.', 'T'}
};

int answer = minCost(grid);

Expected answer:

8
 */

public class MinMoveInGridWithTeleporter {

    public int minCost(char[][] grid)
    {
        //input validation
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length;

        int startRow = -1;
        int startCol = -1;
        Map<Character, List<int[]>> teleporters = new HashMap<>();

        //iterate grid and collect start, end cells and teleporters
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 'S'){
                    startRow = i;
                    startCol = j;
                }else if(grid[i][j] >= 'a' && grid[i][j] <= 'z'){
                    //teleporter
                    teleporters.computeIfAbsent(grid[i][j], key -> new ArrayList<>()).add(new int[]{i, j});
                }
            }
        }

        //edge case missing start location
        if (startRow == -1) {
            return -1;
        }

        Deque<State> bfsDeque = new ArrayDeque<>();
        bfsDeque.offerFirst(new State(startRow, startCol, 0)); //starting cell

        //cost array cost[i][j] = min cost to reach cell (i, j)
        int[][] cost = new int[m][n];
        for(int i=0; i<m; i++){
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }
        cost[startRow][startCol] = 0;

        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

        while(!bfsDeque.isEmpty()){

            State curr = bfsDeque.pollFirst();

            //staleness check
            if(curr.cost > cost[curr.row][curr.col]){
                continue; //stale entry
            }

            char currChar = grid[curr.row][curr.col];

            //exit condition
            if(currChar == 'T'){
                return curr.cost; //target reached
            }

            //teleporter
            //note : The teleport edge is just another edge in the graph.
            if(currChar >= 'a' && currChar <= 'z'){

                //enqueue all other teleporters of same char
                // Process teleport edges
                for(int[] cell : teleporters.getOrDefault(currChar, Collections.emptyList())){

                    if(curr.cost < cost[cell[0]][cell[1]]){ //since there may be multiple ways to reach the same teleporter cell.
                        cost[cell[0]][cell[1]] = curr.cost;
                        bfsDeque.offerFirst(new State(cell[0], cell[1], curr.cost)); //no additional cost incurred, teleport costs 0
                    }
                }

                //important : remove this teleporter from map as we can use it only once
                teleporters.remove(currChar);
            }

            //explore neighbours, Teleportation is optional; continue exploring normal moves as well.
            for(int[] dir : directions){

                int newRow = curr.row + dir[0];
                int newCol = curr.col + dir[1];

                //boundary check
                if(newRow < 0 || newRow >= m || newCol < 0 || newCol >= n){
                    continue; //out of boundary
                }

                //wall check
                if(grid[newRow][newCol] == '#'){
                    continue; //hit a wall
                }

                int newCost = curr.cost + 1; //normal move costs 1

                if(newCost < cost[newRow][newCol]){
                    //relaxation
                    cost[newRow][newCol] = newCost;
                    bfsDeque.offerLast(new State(newRow, newCol, newCost));
                }
            }
        }

        return -1;
    }

    class State{
        int row;
        int col;
        int cost;

        public State(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
    }
}
