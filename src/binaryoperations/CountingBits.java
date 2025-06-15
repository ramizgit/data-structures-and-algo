package binary;

import java.util.ArrayList;
import java.util.List;

public class CountingBits {
    //https://leetcode.com/problems/counting-bits/description/
    public static void main(String[] args)
    {
        System.out.println(countBits(0));
        System.out.println(countBits(2));
        System.out.println(countBits(5));

    }

    private static List<Integer> countBits(int n)
    {
        List<Integer> result = new ArrayList<>();

        for(int i=0; i<=n; i++){
            result.add(numOfOnes(i));
        }

        return result;
    }

    private static int numOfOnes(int n)
    {
        int count = 0;

        while (n != 0){
            n = n & (n-1);
            count++;
        }

        return count;
    }
}
