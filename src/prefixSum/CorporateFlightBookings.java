package prefixSum;

public class CorporateFlightBookings {

    public int[] corpFlightBookings(int[][] bookings, int n)
    {
        int[] prefix = new int[n];

        for(int[] booking : bookings){
            int first = booking[0] - 1; // convert to 0-based index
            int last = booking[1] - 1; // convert to 0-based index
            int seats = booking[2];

            prefix[first] += seats;
            if(last < n - 1){
                prefix[last+1] -= seats;
            }
        }

        for(int i=1; i<n; i++){
            prefix[i] += prefix[i-1];
        }

        return prefix;
    }
}
