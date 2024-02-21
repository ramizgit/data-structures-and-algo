import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalsTriangle {
    public static void main(String[] args)
    {
        getPascalsTriangle(4);
        getPascalsTriangle2(4);

        System.out.println("pascal element for a given index");
        System.out.println(getPascalElement(3, 2)); //3
        System.out.println(getPascalElement(3, 2)); //3

        System.out.println(getPascalElementUsingFact(4, 2)); //6
        System.out.println(getPascalElementUsingFact(4, 2)); //6
    }

    public static void getPascalsTriangle(int row)
    {
        List<List<Integer>> triangleList = new ArrayList<>();
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        triangleList.add(firstRow);

        for(int i=1; i<=row; i++)
        {
            List<Integer> rowList = new ArrayList<>();
            rowList.add(1);

            List<Integer> prevList = triangleList.get(i - 1);

            for(int j=1; j < i; j++)
            {
                rowList.add(prevList.get(j-1)+prevList.get(j));
            }

            rowList.add(1);
            triangleList.add(rowList);
        }
        System.out.println(triangleList);
    }

    public static void getPascalsTriangle2(int row)
    {
        List<List<Integer>> result = new ArrayList<>();

        //add first row
        result.add(Arrays.asList(1));

        for(int i=1; i<row; i++){
            List<Integer> currList = new ArrayList<>();

            //add one in the beginning
            currList.add(1);

            List<Integer> prevList = result.get(i - 1);
            //populate middle numbers
            for(int j=1; j<i; j++){
                currList.add(prevList.get(j-1) + prevList.get(j));
            }

            //add one in the end
            currList.add(1);

            result.add(currList);
        }

        System.out.println(result);

    }

    private static int getPascalElement(int row, int col)
    {
        if(col == 0 || row == col){
            return 1;
        }

        return getPascalElement(row - 1, col - 1) + getPascalElement(row - 1, col);
    }

    private static int getPascalElementUsingFact(int row, int col)
    {
        return factorial(row) / ( factorial(col) * factorial(row - col));
    }

    private static int factorial(int n)
    {
        if(n == 1){
            return 1;
        }

        return n * factorial(n-1);
    }
}
