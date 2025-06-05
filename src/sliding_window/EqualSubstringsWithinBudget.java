package prefixSum;

public class EqualSubstringsWithinBudget {

    //https://leetcode.com/problems/get-equal-substrings-within-budget/

    //hint : sliding window technique

    public static void main(String[] args)
    {
        System.out.println(equalSubstring("abcd", "bcdf", 3)); //3
        System.out.println(equalSubstring("abcd", "cdef", 3)); //1
    }

    private static int equalSubstring(String s, String t, int maxCost)
    {
        int left = 0;
        int right = 0;
        int cost = 0;
        int result = 0;

        while (right < s.length()){
            cost += Math.abs( (s.charAt(right) - '0') - (t.charAt(right) - '0') );

            while(cost > maxCost){
                //move left
                cost -= Math.abs( (s.charAt(left) - '0') - (t.charAt(left) - '0') );
                left++;
            }

            result = Math.max(result, right - left + 1);
            right++;
        }

        return result;
    }
}

