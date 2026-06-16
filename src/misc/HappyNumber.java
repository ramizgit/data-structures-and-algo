package misc;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

    //https://leetcode.com/problems/happy-number/description/

    private static boolean isHappy(int n)
    {
        Set<Integer> seen = new HashSet<>(); //to detect cycle

        while (true){

            int sum = 0;

            while (n != 0){
                int lastDigit = n % 10; //extract last digit
                sum += lastDigit * lastDigit;
                n = n / 10; //continue with remaining number
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
