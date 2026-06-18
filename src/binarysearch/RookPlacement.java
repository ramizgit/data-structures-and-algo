package binarySearch;

/*
Problem Setup

You have:

n x n chessboard

There are:

n - 1 rooks

placed such that:

No two rooks share row or column

So:

exactly ONE row missing rook
exactly ONE column missing rook

Your goal:

find missing rook position
You CANNOT See Board

Only API available:

countRook(r1, c1, r2, c2)
API Meaning

Parameters:

(r1, c1) -> top-left corner
(r2, c2) -> bottom-right corner

The API returns:

number of rooks inside this rectangle
 */

public class RookPlacement {

    //https://leetcode.com/discuss/post/8268786/google-l3-bangalore-india-interview-expe-dt1h/

    public int[] findMissingRook(int n)
    {
        int missingRow = findMissingRow(n);
        int missingCol = findMissingCol(n);

        return new int[]{missingRow, missingCol};
    }

    private int findMissingRow(int n)
    {
        int low = 0; //first row
        int high = n-1; //last row
        int answer = -1;

        while(low <= high){
            int mid = low + (high - low)/2; //middle row

            //check upper rectangle in the row range of low -> mid
            int rookCount = countRook(low, 0, mid, n-1);
            int expectedRookCount = mid - low + 1;

            if(rookCount < expectedRookCount){
                answer = mid; //possible answer
                high = mid - 1; //shrink upper rectangle
            }else{
                low = mid + 1; //move to lower rectangle
            }

        }

        return answer;
    }

    private int findMissingCol(int n)
    {
        int low = 0; //first col
        int high = n-1; //last col
        int answer = -1;

        while(low <= high){
            int mid = low + (high - low)/2; //middle col

            //check left rectangle in the column range of low -> mid
            int rookCount = countRook(0, low, n-1, mid);
            int expectedRookCount = mid - low + 1;

            if(rookCount < expectedRookCount){
                answer = mid; //possible answer
                high = mid - 1; //shrink left rectangle
            }else{
                low = mid + 1; //move to right rectangle
            }
        }

        return answer;
    }

    public int countRook(int r1, int c1, int r2, int c2)
    {
        return 0; //dummy
    }
}
