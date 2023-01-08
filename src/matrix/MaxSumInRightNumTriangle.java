package matrix;

import java.util.ArrayList;
import java.util.List;

public class MaxSumInRightNumTriangle {
    public static void main(String[] args)
    {
        //2
        //4 1
        //1 2 7

        List<Integer> l1 = new ArrayList<>();
        l1.add(2);

        List<Integer> l2 = new ArrayList<>();
        l2.add(4);l2.add(1);

        List<Integer> l3 = new ArrayList<>();
        l3.add(1);l3.add(2);l3.add(7);

        List<List<Integer>> input = new ArrayList<>();
        input.add(l1);input.add(l2);input.add(l3);


        System.out.println(findMaxSum(input)); //10


        //1
        //1 2
        //4 1 2
        //2 3 1 1

        List<Integer> ll1 = new ArrayList<>();
        ll1.add(1);

        List<Integer> ll2 = new ArrayList<>();
        ll2.add(1);ll2.add(2);

        List<Integer> ll3 = new ArrayList<>();
        ll3.add(4);ll3.add(1);ll3.add(2);

        List<Integer> ll4 = new ArrayList<>();
        ll4.add(2);ll4.add(3);ll4.add(1);ll4.add(1);

        List<List<Integer>> input2 = new ArrayList<>();
        input2.add(ll1);input2.add(ll2);input2.add(ll3);input2.add(ll4);


        System.out.println(findMaxSum(input2)); //9

    }

    public static int findMaxSum(List<List<Integer>> input)
    {
        int size = input.size();
        int[][] dp = new int[size][size];
        int row = 0;
        int max = 0;

        for(List<Integer> list : input){
            for(int i=0; i<list.size(); i++){
                //handle very first cell in matrix
                if(row == 0){
                    dp[row][i] = list.get(i);
                }else {
                    //handle first column
                    if(i == 0){
                        dp[row][i] = list.get(i) + dp[row-1][i];
                    }else if (i == list.size()-1){
                        dp[row][i] = list.get(i) + dp[row-1][i-1];
                    } else {
                        dp[row][i] = Math.max( (list.get(i) + dp[row-1][i-1]), (list.get(i) + dp[row-1][i]) );
                    }
                }
                max = Math.max(max, dp[row][i]);
            }
            row++;
        }
        return max;
    }
}
