package matrix;

import java.util.ArrayList;
import java.util.List;

public class PacificAtlanticWaterFlow {

    public static void main(String[] args)
    {
        int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println(pacificAtlantic(heights)); //Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]

        int[][] heights2 = {{2,1},{1,2}};
        System.out.println(pacificAtlantic(heights2)); //Output: [[0,0],[0,1],[1,0],[1,1]]
    }

    public static List<List<Integer>> pacificAtlantic(int[][] heights)
    {
        int row = heights.length;
        int col = heights[0].length;

        boolean[][] pacific = new boolean[row][col];
        boolean[][] atlantic = new boolean[row][col];

        //populate first column and first row of pacific as true, populate last column and last row of atlantic as true
        for(int i=0; i<col; i++){
            pacific[0][i] = true;
            atlantic[row-1][i] = true;
        }
        for(int i=0; i<row; i++){
            pacific[i][0] = true;
            atlantic[i][col-1] = true;
        }

        //populate rest of the pacific dp matrix
        for(int i=1; i<row; i++){
            for(int j=1; j<col; j++){
                if(!(pacific[i-1][j] || pacific[i][j-1])){
                    pacific[i][j] = false;
                }else {
                    if(heights[i][j] >= heights[i-1][j] || heights[i][j] >= heights[i][j-1]){
                        pacific[i][j] = true;
                    }else {
                        pacific[i][j] = false;
                    }
                }
            }
        }

        //populate rest of the atlantic dp matrix
        for(int i=row-2; i>=0; i--){
            for(int j=col-2; j>=0; j--){
                if(!(atlantic[i+1][j] || atlantic[i][j+1])){
                    atlantic[i][j] = false;
                }else {
                    if(heights[i][j] >= heights[i+1][j] || heights[i][j] >= heights[i][j+1]){
                        atlantic[i][j] = true;
                    }else {
                        atlantic[i][j] = false;
                    }
                }
            }
        }

        //find intersection
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(pacific[i][j] && atlantic[i][j]){
                    List<Integer> tmplist = new ArrayList<>();
                    tmplist.add(i);
                    tmplist.add(j);
                    list.add(tmplist);
                }
            }
        }

        return list;
    }
}
