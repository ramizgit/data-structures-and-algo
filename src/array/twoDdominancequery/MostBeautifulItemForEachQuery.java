package arrays.twoDdominancequery;

import java.util.*;

//https://leetcode.com/problems/most-beautiful-item-for-each-query/description/

public class MostBeautifulItemForEachQuery {

    public int[] maximumBeauty(int[][] items, int[] queries)
    {
        //sort by asc price
        Arrays.sort(items, (a, b) -> {
           return a[0] - b[0]; //ascending price
        });

        //before sort : [[1,2],[3,2],[2,4],[5,6],[3,5]]
        //after sort : [[1,2],[2,4],[3,2],[3,5],[5,6]]
        Map<Integer, Integer> priceToMaxBeauty = new HashMap<>();
        int maxBeautySoFar = 0;
        for(int[] item : items){
            maxBeautySoFar = Math.max(maxBeautySoFar, item[1]);
            priceToMaxBeauty.put(item[0], maxBeautySoFar);
        }

        //binary search on items
        int n = queries.length;
        int[] answer = new int[n];

        for(int i=0; i<n; i++){
            int low = 0;
            int high = items.length - 1;
            int bestMatchPrice = 0;

            while(low <= high){
                int mid = low + (high - low)/2;

                if(items[mid][0] <= queries[i]){
                    bestMatchPrice = items[mid][0];
                    low = mid + 1; //try higher
                }else{
                    high = mid - 1; //try lower
                }
            }

            answer[i] =  priceToMaxBeauty.getOrDefault(bestMatchPrice, 0);
        }

        return answer;
    }
}
