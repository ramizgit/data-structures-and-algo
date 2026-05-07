package dp;

import java.util.*;

public class FillingBookCaseShelves {

    //https://leetcode.com/problems/filling-bookcase-shelves/description/

    //todo

    public int minHeightShelves(int[][] books, int shelfWidth)
    {
        //edge case
        if(books == null || books.length == 0){
            return 0;
        }

        int n = books.length;

        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; //base case, no books, no height

        for(int i=1; i<=n; i++){
            //for each book try both approach
            //1. start with separate shelf
            //2. keep on combining with prev books as long as width constrant permits

            int currWidth = 0;
            int currHeight = 0;

            for(int j=i; j>=1; j--){
                currWidth += books[j-1][0];

                if(currWidth > shelfWidth){
                    break; //cant proceed as it exceeds total shelf width constraint
                }

                currHeight = Math.max(currHeight, books[j-1][1]);

                dp[i] = Math.min(
                        dp[i], // best answer found so far for first i books

                        dp[j-1] + currHeight
                        // books[0 ... j-2] arranged optimally
                        // books[j-1 ... i-1] placed on current(last) shelf
                );
            }
        }

        return dp[n];
    }
}
