package binarySearch;

public class MinimumTimeToCompleteTrips {

    //https://leetcode.com/problems/minimum-time-to-complete-trips/

    //time = [1,2,3], totalTrips = 5

    public long minimumTime(int[] time, int totalTrips)
    {
        int fastest = Integer.MAX_VALUE;
        for(int t : time){
            fastest = Math.min(fastest, t);
        }

        int low = 1; //minimum meaningful time is 1 unit
        int high = fastest * totalTrips; //even in the worst case (only fastest machine working), we can finish all trips.
        int answer = 0;

        while(low <= high){
            int mid = low + (high - low)/2;

            if(canCompleteTrip(time, totalTrips, mid)){
                answer = mid; //possible answer
                high = mid - 1; //try lower
            }else{
                low = mid + 1;
            }
        }

        return answer;
    }

    public boolean canCompleteTrip(int[] time, int totalTrips, int target)
    {
        int count = 0;

        for(int t : time){
            count += target/t;

            //early exit
            if (count >= totalTrips) {
                return true;
            }
        }

        return count >= totalTrips;
    }
}
