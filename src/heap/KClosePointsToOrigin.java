package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class KClosePointsToOrigin {

    //https://leetcode.com/problems/k-closest-points-to-origin/description/

    public List<List<Integer>> kClosest(int[][] points, int k)
    {
        PriorityQueue<Point> minheap = new PriorityQueue<>( (a,b) -> a.dist - b.dist );
        List<List<Integer>> answer = new ArrayList<>();

        for(int[] point : points){
            int dist = (point[0] * point[0]) + (point[1] * point[1]);
            minheap.add(new Point(point[0], point[1], dist));
        }

        for(int i=0; i<k; i++){
            Point poll = minheap.poll();
            answer.add(Arrays.asList(poll.x, poll.y));
        }

        return answer;
    }

    static class Point{
        int x;
        int y;
        int dist;

        Point(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}


