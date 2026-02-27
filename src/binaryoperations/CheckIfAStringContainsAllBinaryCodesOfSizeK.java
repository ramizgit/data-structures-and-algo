package BinaryOps;

import java.util.*;

public class CheckIfAStringContainsAllBinaryCodesOfSizeK {
    //https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/description/

    public boolean hasAllCodes(String s, int k)
    {
        int n = s.length();
        if (n < k) return false;

        Set<Integer> seen = new HashSet<>();
        int needed = (1 << k);

        int num = 0;
        int mask = (1 << k) - 1; //to ensure we collect nums of only size k

        for(int i=0; i<n; i++){
            num = ((num << 1) & mask) | (s.charAt(i) - '0');

            if(i >= k-1){
                seen.add(num);
            }

            if(seen.size() == needed){
                return true; //early exit
            }

        }

        return seen.size() == needed;
    }
}
