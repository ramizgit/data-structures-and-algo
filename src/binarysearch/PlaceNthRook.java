package consistenthashing.binarysearch;

import java.util.*;

/*
You are given an N x N chess board.

There are already N - 1 rooks placed on the board.

These rooks do not attack each other. More formally:
no row contains more than one rook
no column contains more than one rook
You need to place the Nth rook and return the position (i, j) where it should be placed.

You do not know where the existing rooks are located.

Instead, you are given a list of rectangle query results.

Each rectangle query result tells how many already placed rooks are inside a given inclusive rectangle.

The board uses 1-indexed coordinates.
Method Signatures
RookPlacement(int n, List<String> rectangleQueries)
Initializes the object with the board size and the given rectangle query results
Each string in rectangleQueries is in the format "a,b,c,d,count"
a and b are row boundaries, where a ≤ b
c and d are column boundaries, where c ≤ d
count is the number of already placed rooks inside rows a through b and columns c through d

String placeNthRook()
Returns the answer as "i,j"
i is the row index where the Nth rook should be placed
j is the column index where the Nth rook should be placed
Input
The constructor input is:
n: the size of the board
rectangleQueries: a list of rectangle query results
Each query result is given as a comma-separated string:

"a,b,c,d,count"

There are two useful types of rectangle queries:
A row-range query has c = 1 and d = n. It counts rooks in rows a through b across all columns.
A column-range query has a = 1 and b = n. It counts rooks in columns c through d across all rows.
The given rectangle queries are guaranteed to contain enough information to uniquely determine the missing row and the missing column.
Output
Return a string in the format "i,j", where:
i is the row of the missing rook position
j is the column of the missing rook position
If multiple valid positions are possible, return the position with the smallest row index.
If there is still a tie, return the position with the smallest column index.
Constraints
1 ≤ n ≤ 10^5
1 ≤ rectangleQueries.size() ≤ 2 * 10^5
Each query string is in the format "a,b,c,d,count"
1 ≤ a ≤ b ≤ n
1 ≤ c ≤ d ≤ n
0 ≤ count ≤ n - 1
Every query is either a row-range query or a column-range query
Exactly n - 1 rooks are already placed on the board
No two existing rooks share the same row
No two existing rooks share the same column
The given rectangle queries are consistent with one valid board configuration
The given rectangle queries are sufficient to uniquely identify the missing row and missing column
The given rectangle queries are consistent with at least one valid board configuration
If multiple missing rows or missing columns are possible, return the lexicographically smallest valid position "i,j"
The board is 1-indexed
Notes
Because there are n - 1 non-attacking rooks on an n x n board:
exactly one row does not contain a rook
exactly one column does not contain a rook
the answer is the intersection of that row and that column
For a row-range query covering rows a through b:
if the missing row is inside this range, then the count is b - a
if the missing row is outside this range, then the count is b - a + 1
For a column-range query covering columns c through d:
if the missing column is inside this range, then the count is d - c
if the missing column is outside this range, then the count is d - c + 1
 */

public class PlaceNthRook {

    //https://codezym.com/question/159

    //binary search approach
    //Time O(Q)
    public String rookPlacement(int n, List<String> rectangleQueries)
    {
        int row = findMissingRow(n, rectangleQueries);
        int col = findMissingColumn(n, rectangleQueries);

        return row + "," + col;
    }

    private int findMissingRow(int n, List<String> rectangleQueries)
    {
        int low = 1;
        int high = n;

        for (String query : rectangleQueries) {

            String[] entry = query.split(",");
            int a = Integer.parseInt(entry[0]);
            int b = Integer.parseInt(entry[1]);
            int c= Integer.parseInt(entry[2]);
            int d = Integer.parseInt(entry[3]);
            int count = Integer.parseInt(entry[4]);

            //check if row query
            if (!(c == 1 && d == n)){
                continue;
            }

            int length = b - a + 1;

            if (count == length - 1) {
                // Missing row is inside [a,b]
                low = a;
                high = b;
            } else {
                // Missing row is outside [a,b]

                // Left half was queried
                if (a == low) {
                    low = b + 1;
                }
                // Right half was queried
                else {
                    high = a - 1;
                }
            }

            // Early exit
            if (low == high)
                return low;
        }

        return low;
    }

    private int findMissingColumn(int n, List<String> rectangleQueries)
    {
        int low = 1;
        int high = n;

        for (String query : rectangleQueries) {

            String[] entry = query.split(",");
            int a = Integer.parseInt(entry[0]);
            int b = Integer.parseInt(entry[1]);
            int c= Integer.parseInt(entry[2]);
            int d = Integer.parseInt(entry[3]);
            int count = Integer.parseInt(entry[4]);

            if (!(a == 1 && b == n)) {
                continue;
            }

            int length = d - c + 1;

            if (count == length - 1) {
                // Missing column is inside [c,d]
                low = c;
                high = d;
            } else {
                // Missing column is outside [c,d]

                // Left half queried
                if (c == low) {
                    low = d + 1;
                }
                // Right half queried
                else {
                    high = c - 1;
                }
            }

            // Early exit
            if (low == high)
                return low;
        }

        return low;
    }

    //this is brute force
    //time : O(Q × N)
    public void rookPlacementBruteFroce(int n, List<String> rectangleQueries)
    {
        boolean[] possibleRow = new boolean[n+1];
        boolean[] possibleCol = new boolean[n+1];

        //initially mark all row and coll as possibility to place the rook
        Arrays.fill(possibleRow, true);
        Arrays.fill(possibleCol, true);

        //iterate rectangleQueries and eliminate options
        for(String query : rectangleQueries){

            String[] entry = query.split(",");
            int a = Integer.parseInt(entry[0]);
            int b = Integer.parseInt(entry[1]);
            int c= Integer.parseInt(entry[2]);
            int d = Integer.parseInt(entry[3]);
            int count = Integer.parseInt(entry[4]);

            if(c == 1 && d == n){
                //row query
                int expectedCount = b - a + 1;
                if(expectedCount == count){
                    //eliminate [a,b] range as it cant be answer
                    for(int i=a; i<=b; i++){
                        possibleRow[i] = false;
                    }
                }else{
                    //answer lies in [a,b] range
                    for(int i=1; i<=n; i++){

                        if(i >= a && i <= b){
                            continue;
                        }

                        possibleRow[i] = false;
                    }
                }
            }else if(a == 1 && b == n){
                //col query
                int expectedCount = d - c + 1;
                if(expectedCount == count){
                    //eliminate [c,d] range as it cant be answer
                    for(int i=c; i<=d; i++){
                        possibleCol[i] = false;
                    }
                }else{
                    //answer lies in [c,d] range
                    for(int i=1; i<=n; i++){

                        if(i >= c && i <= d){
                            continue;
                        }

                        possibleCol[i] = false;
                    }
                }
            }
        }

        //collect answer
        int row = -1;
        int col = -1;

        for (int i = 1; i <= n; i++) {
            if (possibleRow[i]) {
                row = i;
                break;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (possibleCol[i]) {
                col = i;
                break;
            }
        }
    }
}

