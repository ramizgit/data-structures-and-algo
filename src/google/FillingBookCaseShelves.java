package dp;

import java.util.*;

public class FillingBookCaseShelves {

    //https://leetcode.com/problems/filling-bookcase-shelves/description/

    public int minHeightShelves(int[][] books, int shelfWidth)
    {
        if(books == null || books.length == 0){
            return 0;
        }

        int n = books.length; //number of books

        int[] dp = new int[n]; // dp[i] = minimum height to place books from 0 to i

        for(int i=0; i<n; i++){
            //for each book try both approach
            //1. start with separate shelf
            //2. keep on combining with prev books as long as width constrant permits

            //1. place current book on new shelf
            int currWidth = books[i][0];
            int currHeight = books[i][1];
            dp[i] = currHeight  + (i > 0 ? dp[i - 1] : 0);

            //2. try placing previous books on same shelf
            for(int j=i-1; j>=0; j--){ //since books need to be places in order

                currWidth += books[j][0];

                if(currWidth > shelfWidth){
                    break; //cant proceed as it exceeds total shelf width constraint
                }

                // books[j...i] are placed on same shelf
                currHeight = Math.max(currHeight, books[j][1]);

                dp[i] = Math.min(dp[i], currHeight + (j > 0 ? dp[j - 1] : 0));
            }
        }

        return dp[n-1];
    }
}
