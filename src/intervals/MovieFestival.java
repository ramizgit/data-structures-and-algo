package intervals;

import java.util.*;

/*
In a movie festival n movies will be shown. You know the starting and ending time of each movie. What is the maximum number of movies you can watch entirely?
Input
The first input line has an integer n: the number of movies.
After this, there are n lines that describe the movies. Each line has two integers a and b: the starting and ending times of a movie.
Output
Print one integer: the maximum number of movies.
Constraints

1 \le n \le 2 \cdot 10^5
1 \le a < b \le 10^9

Example
Input:
3
3 5
4 9
5 8

Output:
2
 */

public class MovieFestival {

    //https://cses.fi/problemset/task/1629

    public int findMaxNumOfMovies(int n, int[][] intervals)
    {
        //sort by end time so we always pick the movie that finishes earliest
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int prevEndTime = intervals[0][1];
        int answer = 1;

        for(int i=1; i<n; i++){

            int currStartTime = intervals[i][0];
            int currEndTime = intervals[i][1];

            if(currStartTime >= prevEndTime){
                answer++;
                prevEndTime = currEndTime;
            }
        }

        return answer;
    }
}
