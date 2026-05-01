package google;

import java.util.*;

public class MinimumNumOfPlatform {

    /*
    We are given two arrays that represent the arrival and departure times of trains that stop at the platform.
    We need to find the minimum number of platforms needed at the railway station so that no train has to wait.

    arr[] = {9:00, 9:45, 9:55, 11:00, 15:00, 18:00}
    dep[] = {9:20, 11:30, 11:50, 12:00, 19:00, 20:00}
     */

    /*
    dry run
    arr = [900, 940, 950]
    dep = [910, 1200, 1120]
    (sorted dep = [910, 1120, 1200])

    | Event       | trainsAtStation |
| ----------- | --------- |
| 900 arrives | 1         |
| 910 departs | 0         |
| 940 arrives | 1         |
| 950 arrives | 2         |
     */

    public int countPlatforms(int[] arr, int[] dep)
    {
        int n = arr.length;

        //sort both arrival and departure times in asc order
        Arrays.sort(arr);
        Arrays.sort(dep);

        int i = 0; //arrival ptr
        int j = 0; //departure ptr
        int trainsAtStation = 0;
        int maxTrains = 0;

        while(i < n && j < n){
            // train arrives before or at same time as departure, increase train count
            if(arr[i] <= dep[j]){
                trainsAtStation++;
                i++;
            }else{
                // train departs, decrease train count
                trainsAtStation--;
                j++;
            }

            maxTrains = Math.max(maxTrains, trainsAtStation );
        }

        return maxTrains; //minimum number of platforms we need
    }
}
