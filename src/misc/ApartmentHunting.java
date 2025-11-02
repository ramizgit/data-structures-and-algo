package misc;

import java.util.*;

public class ApartmentHunting {

    public static void main(String[] args)
    {
        List<Map<String, Boolean>> blocks = List.of(
                Map.of("gym", false, "school", true,  "store", false),
                Map.of("gym", true,  "school", false, "store", false),
                Map.of("gym", true,  "school", true,  "store", false),
                Map.of("gym", false, "school", true,  "store", true)
        );

        List<String> reqs = List.of("gym", "school", "store");

        System.out.println(apartmentHunting(blocks, reqs));
    }

    private static int apartmentHunting(List<Map<String, Boolean>> blocks, List<String> reqs)
    {
        int r = reqs.size();
        int n = blocks.size();
        int[][] distances = new int[r][n];

        //initialize all cell with max int
        for(int i=0; i<r; i++){
            for(int j=0; j<n; j++){
                distances[i][j] = Integer.MAX_VALUE;
            }
        }

        //left to right pass
        for(int i=0; i<r; i++){
            String req = reqs.get(i);
            int lastSeen = -1;

            for(int j=0; j<blocks.size(); j++){
                if(blocks.get(j).get(req)){
                    distances[i][j] = 0;
                    lastSeen = j;
                }else if(lastSeen != -1){
                    distances[i][j] = j - lastSeen;
                }
            }
        }

        //right to left pass
        for(int i=0; i<r; i++){
            String req = reqs.get(i);
            int lastSeen = -1;

            for(int j=blocks.size()-1; j>=0; j--){
                if(blocks.get(j).get(req)){
                    distances[i][j] = 0;
                    lastSeen = j;
                }else if(lastSeen != -1){
                    distances[i][j] = Math.min(distances[i][j], lastSeen - j);
                }
            }
        }

        //find max distance/penalty in each block
        int[] maxInEachBlock = new int[n];

        for(int i=0; i<n; i++){
            for(int j=0; j<r; j++){
                maxInEachBlock[i] = Math.max(maxInEachBlock[i], distances[j][i]);
            }
        }

        //find block with smallest max distance/penatly
        int min = maxInEachBlock[0];
        int idx = 0;

        for(int i=1; i<n; i++){
            if(min > maxInEachBlock[i]){
                min = maxInEachBlock[i];
                idx = i;
            }
        }

        return idx;
    }
}
