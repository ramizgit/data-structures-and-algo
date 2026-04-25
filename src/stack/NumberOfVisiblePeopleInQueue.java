package google;

import java.util.*;

public class NumberOfVisiblePeopleInQueue {

    //Time : O(n), Space : O(n)
    public int[] canSeePersonsCount(int[] heights)
    {
        //input validation
        if(heights == null || heights.length == 0){
            return new int[0];
        }

        int n = heights.length;
        int[] result = new int[n];
        result[n-1] = 0;

        Stack<Integer> stack = new Stack<>(); //monotonic decreasing stack
        stack.add(heights[n-1]); //right most element

        //loop from right to left
        for(int i=n-2; i>=0; i--){
            int visible = 0;

            while(!stack.isEmpty() && stack.peek() < heights[i]){
                stack.pop();
                visible++;
            }

            if(!stack.isEmpty()){
                visible++;
            }

            stack.add(heights[i]);

            result[i] = visible;
        }

        return result;
    }

    //BRUTE FORCE APPROACH, RUSN IN O(n^2)
    public int[] canSeePersonsCountViaBruteForce(int[] heights)
    {
        int n = heights.length;
        int[] result = new int[n];

        for(int i=0; i<n; i++){

            int maxSoFar = 0;
            int visible = 0;

            for(int j=i+1; j<n; j++){
                if(heights[j] > maxSoFar){
                    visible++;
                    maxSoFar = heights[j];
                }

                if(heights[j] >= heights[i]){
                    break;
                }
            }

            result[i] = visible;
        }

        return result;
    }
}
