package matrix;

public class CountNegNumsInSortedMatrix {
    public static void main(String[] args)
    {
        int[][] input = { {4,3,2,-1},
                          {3,2,1,-1},
                          {1,1,-1,-2},
                          {-1,-1,-2,-3} };
        System.out.println(countInDescendingOrderMatrix(input)); //8

        int[][] input2 = { {-3,-2,-1,1}, {-2,2,3,4}, {4,5,7,8} };
        System.out.println(countInAscendingOrderMatrix(input2)); //4

        //Search in row wise and col wise sorted matrix
        int[][] input3 = { {10, 20, 30, 40}, {15, 25, 35, 45}, {27, 29, 37, 48}, {32, 33, 39, 50} };
        System.out.println(searchInRowAndColWiseSortedMatrix(input3, 29)); //true
        System.out.println(searchInRowAndColWiseSortedMatrix(input3, 19)); //false
    }

    public static int countInDescendingOrderMatrix(int[][] input)
    {
        int rows = input.length;
        int cols = input[0].length;
        int count=0;

        int i = 0;
        int j = cols-1;

        while (i < rows &&  j >= 0){
            if(input[i][j] < 0){
                //add to the answer count
                count += (rows - i);

                //move left
                j--;
            }else {
                //move down
                i++;
            }
        }

        return count;
    }

    public static int countInAscendingOrderMatrix(int[][] input)
    {
        int rows = input.length;
        int cols = input[0].length;
        int count=0;

        int i = 0;
        int j = cols-1;

        while (i < rows &&  j >= 0){
            if(input[i][j] < 0){
                //add to the answer count
                count += (j+1);

                //move down
                i++;
            }else {
                //move left
                j--;
            }
        }

        return count;
    }

    public static boolean searchInRowAndColWiseSortedMatrix(int[][] input, int target)
    {
        int rows = input.length;
        int cols = input[0].length;

        int i=0;
        int j=cols-1;

        while (i<rows && j>=0){
            if(input[i][j] == target){
                return true;
            }else if(target < input[i][j]){
                //move left
                j--;
            }else {
                //move down
                i++;
            }
        }
        return false;
    }
}
