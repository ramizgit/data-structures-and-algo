import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    public static void main(String[] args)
    {
        getPascalsTriangle(4);
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
}
