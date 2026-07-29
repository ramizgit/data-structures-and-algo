package twopointers;

import java.util.*;

/*
There are n children who want to go to a Ferris wheel, and your task is to find a gondola for each child.
Each gondola may have one or two children in it, and in addition, the total weight in a gondola may not exceed x. You know the weight of every child.
What is the minimum number of gondolas needed for the children?
Input
The first input line contains two integers n and x: the number of children and the maximum allowed weight.
The next line contains n integers p_1,p_2,\ldots,p_n: the weight of each child.
Output
Print one integer: the minimum number of gondolas.
Constraints

1 \le n \le 2 \cdot 10^5
1 \le x \le 10^9
1 \le p_i \le x

Example
Input:
4 10
7 2 3 9

Output:
3
*/

public class FerrisWheel {

    //https://cses.fi/problemset/task/1090

    public int findMinGondola(int n, int x, int[] weights)
    {
        //n and x: the number of children and the maximum allowed weight.

        Arrays.sort(weights);

        int left = 0;
        int right = n - 1;

        int answer = 0;

        while(left <= right){

            if(weights[left] + weights[right] <= x){
                //pair the lightest and heaviest child
                left++;
                right--;
            }else{
                //the heaviest must ride alone
                right--;
            }

            answer++; //increment gondola count
        }

        return answer;
    }
}
