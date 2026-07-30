package intervals;

import java.util.*;

/*
There is a large hotel, and n customers will arrive soon. Each customer wants to have a single room.
You know each customer's arrival and departure day. Two customers can stay in the same room if the departure day of the first customer is earlier than the arrival day of the second customer.
What is the minimum number of rooms that are needed to accommodate all customers? And how can the rooms be allocated?
Input
The first input line contains an integer n: the number of customers.
Then there are n lines, each of which describes one customer. Each line has two integers a and b: the arrival and departure day.
Output
Print first an integer k: the minimum number of rooms required.
After that, print a line that contains the room number of each customer in the same order as in the input. The rooms are numbered 1,2,\ldots,k. You can print any valid solution.
Constraints

1 \le n \le 2 \cdot 10^5
1 \le a \le b \le 10^9

Example
Input:
3
1 2
2 4
4 4

Output:
2
1 2 1
 */

public class RoomAllocation {

    //https://cses.fi/problemset/task/1164

    public int[] assignRooms(int n, int[][] intervals)
    {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            customers.add(new Customer(intervals[i][0], intervals[i][1], i));
        }

        //sort by asc arrival time
        customers.sort((a, b) -> Integer.compare(a.arrival, b.arrival));

        PriorityQueue<Customer> minHeap = new PriorityQueue<>( (a, b) -> Integer.compare(a.departure, b.departure) ); //always process earliest departure first

        int[] rooms = new int[n];
        int nextRoom = 1;

        for (Customer customer : customers) {

            int currArrival = customer.arrival;

            int roomToBeAssigned;

            if(!minHeap.isEmpty() && minHeap.peek().departure < currArrival){
                roomToBeAssigned = minHeap.poll().room; //reuse
            }else{
                roomToBeAssigned = nextRoom++;
            }

            customer.room = roomToBeAssigned;

            minHeap.offer(customer);
            rooms[customer.index] = roomToBeAssigned;
        }

        int minRoomRequired = nextRoom - 1;

        return rooms;
    }

    static class Customer{
        int arrival;
        int departure;
        int index;
        int room;

        public Customer(int arrival, int departure, int index) {
            this.arrival = arrival;
            this.departure = departure;
            this.index = index;
        }
    }
}
