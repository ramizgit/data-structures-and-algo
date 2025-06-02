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
        List<Integer> buildings = new ArrayList<>();

        //last building can always view the ocean
        buildings.add(input.length-1);
        int maxRight = input[input.length-1];

        for(int i= input.length-2; i>=0; i--){
            if(input[i] > maxRight){
                buildings.add(i);

                //reset max
                maxRight = input[i];
            }
        }

        System.out.println(buildings);

        int[] output = new int[buildings.size()];
        int idx = 0;
        for(int i=buildings.size()-1; i>=0 ;i--){
            output[idx++] = buildings.get(i);
        }

        return output;
    }
}
