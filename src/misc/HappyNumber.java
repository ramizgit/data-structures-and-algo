package misc;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    //https://leetcode.com/problems/happy-number/description/
    public static void main(String[] args)
    {
        System.out.println(isHappy(19));
        System.out.println(isHappy(2));
    }

    private static boolean isHappy(int n)
    {
        Set<Integer> seen = new HashSet<>();
        while (true){
            int sum = 0;
            while (n != 0){
                sum += Math.pow(n%10, 2);
                n = n / 10;
            }

            if(sum == 1){
                return true;
            } else if (seen.contains(sum)) {
                return false;
            } else {
                n = sum;
                seen.add(n);
            }
        }
    }
}
