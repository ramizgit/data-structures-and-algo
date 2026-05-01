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

    public int[] assignPlatformNumbers(int[] arr, int[] dep)
    {
        int n = arr.length;

        //create list of trains from input
        List<Train> trains = new ArrayList<>();
        for(int i=0; i<n; i++){
            trains.add(new Train(arr[i], dep[i], i));
        }

        trains.sort((a, b) -> a.arr - b.arr); //sort by arrival time

        //minheap for departure times, to get time of earliest platform availability
        PriorityQueue<Train> minheap = new PriorityQueue<>( (a, b) -> a.dep - b.dep );

        int[] result = new int[n];
        int platform = 0;

        for(Train train : trains){
            if(!minheap.isEmpty() && train.arr > minheap.peek().dep){
                //reuse
                Train earliestDep = minheap.poll();
                train.platform = earliestDep.platform;
                result[train.idx] = train.platform;

            }else{
                //assign new platform
                platform++;
                train.platform = platform;
                result[train.idx] = train.platform;
            }
            minheap.add(train);
        }

        return result;
    }

    class Train{
        int arr;
        int dep;
        int platform;
        int idx;

        public Train(int arr, int dep, int idx) {
            this.arr = arr;
            this.dep = dep;
            this.idx = idx;
        }
    }
}
