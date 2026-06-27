package consistenthashing.lineSweep;

public class MaximumPopulationYear {

    //https://leetcode.com/problems/maximum-population-year/description/

    public int maximumPopulation(int[][] logs)
    {
        //input validation
        if(logs == null || logs.length == 0){
            return 0;
        }

        // convert each person's lifespan into difference array using birth (+1) and death (-1) events.
        int[] diff = new int[101];

        for(int[] log : logs){

            int birthYear = log[0];
            int deathYear = log[1];

            diff[birthYear - 1950] += 1; //person is born, [-1950 for 0 based indexing]
            diff[deathYear - 1950] -= 1; //person dies
        }

        // sweep through the difference array using a prefix sum.
        int population = 0;
        int maxPopulation = 0;
        int earliestYearWithMax = 0;

        for(int i=0; i<diff.length; i++){
            population += diff[i];

            if(population > maxPopulation){
                maxPopulation = population;
                earliestYearWithMax = i + 1950; //add 1950 to get actual year
            }
        }

        return earliestYearWithMax;
    }
}
