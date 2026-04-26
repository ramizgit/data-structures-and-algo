package matrix;

public class CountNegNumsInSortedMatrix {

    public static int countInDescendingOrderMatrix(int[][] input)
    {
        int rows = input.length;
        int cols = input[0].length;
        int count=0;

        int row = 0;
        int col = cols-1;

        while (row < rows &&  col >= 0){
            if(input[row][col] < 0){
                count += (rows - row); //add to the answer count
                col--; //move left
            }else {
                row++; //move down
            }
        }

        return count;
    }

    public static int countInAscendingOrderMatrix(int[][] input)
    {
        int rows = input.length;
        int cols = input[0].length;
        int count=0;

        int row = 0;
        int col = cols-1;

        while (row < rows &&  col >= 0){
            if(input[row][col] < 0){
                count += (col+1); //add to the answer count
                row++; //move down
            }else {
                col--; //move left
            }
        }

        return count;
    }

    public static boolean searchInRowAndColWiseSortedMatrix(int[][] input, int target)
    {
        int rows = input.length;
        int cols = input[0].length;

        int row=0;
        int col=cols-1;

        while (row<rows && col>=0){
            if(input[row][col] == target){
                return true;
            }else if(target < input[row][col]){
                col--; //move left
            }else {
                row++; //move down
            }
        }
        return false;
    }
}
