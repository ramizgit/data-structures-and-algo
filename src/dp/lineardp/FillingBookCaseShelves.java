package dp.lineardp;

public class FillingBookCaseShelves {

    //https://leetcode.com/problems/filling-bookcase-shelves/description/

    public int minHeightShelves(int[][] books, int shelfWidth)
    {
        if(books == null || books.length == 0){
            return 0;
        }

        int n = books.length; //number of books

        int[] dp = new int[n]; // dp[i] = minimum height to place books[0...i]

        for(int i=0; i<n; i++){
            //for each book try both approach
            //1. start with separate shelf
            //2. keep on combining with prev books as long as width constraint permits

            //1. place current book on new shelf
            int currWidth = books[i][0];
            int currHeight = books[i][1];

            dp[i] = currHeight  + (i > 0 ? dp[i - 1] : 0);

            //2. try extending the last shelf backwards by including previous books
            for(int j=i-1; j>=0; j--){ //since books need to be placed in order

                //try placing books[j...i] on the new shelf

                currWidth += books[j][0];

                if(currWidth > shelfWidth){
                    break; //cant proceed as it exceeds total shelf width constraint
                }

                // books[j...i] are placed on same shelf
                currHeight = Math.max(currHeight, books[j][1]); //max within a shelf, min across possible shelf arrangements.

                //take the minimum across all possible arrangements.
                dp[i] = Math.min(dp[i], currHeight + (j > 0 ? dp[j - 1] : 0)); //max within a shelf, min across possible shelf arrangements.
            }
        }

        return dp[n-1];
    }
}

/*
the DP approach :-

"Let's decide where the last shelf begins."

Imagine you're building the final arrangement from scratch.

Suppose you're placing book i.

Candidate 1

The last shelf starts at i.

Previous shelves : books[0...i-1]

Last shelf       : i
Candidate 2

The last shelf starts at i-1.

Previous shelves : books[0...i-2]

Last shelf       : i-1, i
Candidate 3

The last shelf starts at i-2.

Previous shelves : books[0...i-3]

Last shelf       : i-2, i-1, i
Candidate 4

The last shelf starts at i-3.

Previous shelves : books[0...i-4]

Last shelf       : i-3, i-2, i-1, i

and so on...
 */