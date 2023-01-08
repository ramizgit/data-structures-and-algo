package array;

import java.util.ArrayList;
import java.util.List;

public class OceanView {
    public static void main(String[] args)
    {
        findBuildingsWithOceanViews(new int[]{4,3,2,1}); //0 1 2 3
        findBuildingsWithOceanViews(new int[]{4,2,3,1}); //0 2 3
        findBuildingsWithOceanViews(new int[]{1,2,3,4}); //3
    }

    private static int[] findBuildingsWithOceanViews(int[] input)
    {
        List<Integer> result = new ArrayList<>();
        result.add(input.length-1);

        int rightMax = input[input.length-1];

        for(int i=input.length-2; i>=0; i--)
        {
            if(input[i] > rightMax){
                result.add(i);
                rightMax = input[i];
            }
        }

        int[] resultarray = new int[result.size()];

        for(int i = result.size()-1, j=0; i>=0; i--, j++){
            resultarray[j] = result.get(i);
        }

        for(int e : resultarray){
            System.out.print(e + " ");
        }
        System.out.println();

        return resultarray;
    }
}
