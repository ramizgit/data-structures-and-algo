package binarysearch;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubSeq {
    //https://leetcode.com/problems/longest-increasing-subsequence/description/

    public static void main(String[] args)
    {
        System.out.println(longestIncreasingSubSeq(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(longestIncreasingSubSeq(new int[]{7,7,7,7,7}));
    }

    private static int longestIncreasingSubSeq(int[] num)
    {
        List<Integer> list = new ArrayList<>();
        list.add(num[0]);

        for(int i=1; i<num.length; i++){
            int left = 0;
            int right = list.size()-1;

            while(left <= right){
                int mid = left + (right -left)/2;
                if(num[i] > list.get(mid)){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }

            //if num[i] is greater than all elemetns, just append
            if(left == list.size()){
                list.add(num[i]);
            }else{
                list.set(left, num[i]); //else replace element which is just greater than num[i]
            }
        }

        System.out.println(list);

        return list.size();
    }
}
