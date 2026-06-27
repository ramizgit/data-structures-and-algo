package consistenthashing.lineSweep;

import java.util.Arrays;

public class CorporateFlightBookings {

    //https://leetcode.com/problems/corporate-flight-bookings/description/

    // Trick: Difference Array (Sweep Line on a bounded coordinate range).

    // Time  : O(bookings.length + n)
    // Space : O(n)
    public int[] corpFlightBookings(int[][] bookings, int n)
    {
        int[] diff = new int[n+1];

        // Convert bookings into difference array updates.
        for(int[] booking : bookings){

            int first = booking[0] - 1; // convert to 0-based index
            int last = booking[1] - 1; // convert to 0-based index
            int seats = booking[2];

            diff[first] += seats;
            diff[last+1] -= seats;
        }

        // Compute the actual seats booked for each flight via prefix sum
        for(int i=1; i<n; i++){
            diff[i] += diff[i-1];
        }

        return Arrays.copyOf(diff, n); // Return only the first n elements (ignore the extra sentinel element).
    }
}
