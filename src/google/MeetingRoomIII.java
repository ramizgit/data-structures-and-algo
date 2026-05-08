package google;

import java.util.*;

public class MeetingRoomIII {

    //https://leetcode.com/problems/meeting-rooms-iii/description/

    //Time complexity : O(m log m + m log n)
    //Space complexity : O(n)
    public int mostBooked(int n, int[][] meetings)
    {
        //minheap for unused rooms {roomId}
        PriorityQueue<Integer> unusedRooms = new PriorityQueue<>();

        for(int i=0; i<n; i++){ //Time : O(n)
            unusedRooms.add(i);
        }

        //minheap for used rooms {roomId, endtime}
        PriorityQueue<int[]> usedRooms = new PriorityQueue<>(
                // first sort by end time, then by smaller room id
                // bcz if two rooms free at same time, we must pick smaller room id.
                (a, b) -> {
                    if(a[1] == b[1]){
                        return a[0] - b[0];
                    }
                    return a[1] - b[1];
                }
        );

        int[] numOfMeetings = new int[n]; //to track number of meetings being held in each room
        int maxNumOfMeetings = 0; //to track max number of meetings being held in each room

        //sort meetings by start time
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]); //Time : O(mlogm)

        for(int[] meeting : meetings){ //Time : O(m)
            int start = meeting[0];
            int end = meeting[1];

            //free all rooms whose meetings ended before current meeting start
            while(!usedRooms.isEmpty() && usedRooms.peek()[1] <= start){
                int[] room = usedRooms.poll(); //Time : O(logn)
                unusedRooms.offer(room[0]);
            }

            if(!unusedRooms.isEmpty()){
                //rooms is avaialble
                //assign smallest available room and add to used rooms heap
                int lowestUnusedRoom = unusedRooms.poll();
                usedRooms.add(new int[]{lowestUnusedRoom, end});

                //track meeting request
                numOfMeetings[lowestUnusedRoom]++;
                maxNumOfMeetings = Math.max(maxNumOfMeetings, numOfMeetings[lowestUnusedRoom]);
            }else{
                //no room available
                //immediately delay the current meeting to the earliest room that becomes free.
                int[] earliestRoom = usedRooms.poll();
                int currMeetingDuration = end - start;
                int newEndTime = earliestRoom[1] + currMeetingDuration;

                //add back to used room
                usedRooms.add(new int[]{earliestRoom[0], newEndTime});

                //track meeting request
                numOfMeetings[earliestRoom[0]]++;
                maxNumOfMeetings = Math.max(maxNumOfMeetings, numOfMeetings[earliestRoom[0]]);
            }
        }

        //return smallrest room that handles max meetings
        for(int i=0; i<n; i++){
            if(numOfMeetings[i] == maxNumOfMeetings){
                return i;
            }
        }

        return -1; //should not reach here
    }
}
