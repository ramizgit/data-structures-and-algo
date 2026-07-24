package greedy;

import java.util.*;

public class Ipo {

    //https://leetcode.com/problems/ipo/description/

    /*
    Approach:
    1. Store all projects in a min-heap ordered by required capital.
    2. Repeatedly move all currently affordable projects into a max-heap ordered by profit.
    3. Greedily pick the most profitable available project and add its profit to the current capital.
    4. Repeat at most k times.
    */

    /*
    Time Complexity:
    - O((n + k) log n)
    - Each project is inserted into and removed from a heap at most once.
    - Up to k projects are selected from the profit heap.

    Space Complexity:
    - O(n)
    - Both heaps together store at most n projects.
    */

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital)
    {
        //input validation
        if (k <= 0 || profits == null || capital == null || profits.length == 0) {
            return w;
        }

        PriorityQueue<Project> capitalMinHeap = new PriorityQueue<>( (a, b) -> Integer.compare(a.capital, b.capital) );
        PriorityQueue<Project> profitMaxHeap = new PriorityQueue<>( (a, b) -> Integer.compare(b.profit, a.profit) );

        int currCapital = w; //starting capital

        //put every project into the capital min heap
        for(int i=0; i<capital.length; i++){
            capitalMinHeap.offer(new Project(capital[i], profits[i]));
        }

        while(k != 0){

            //move all projects that can be afforded into the profit max-heap
            while(!capitalMinHeap.isEmpty() && capitalMinHeap.peek().capital <= currCapital){
                profitMaxHeap.offer(capitalMinHeap.poll());
            }

            if (profitMaxHeap.isEmpty()) {
                break;
            }

            //select the most profitable available project
            currCapital += profitMaxHeap.poll().profit;
            k--;
        }

        return currCapital;
    }

    static class Project{
        int capital;
        int profit;

        public Project(int capital, int profit) {
            this.capital = capital;
            this.profit = profit;
        }
    }
}
