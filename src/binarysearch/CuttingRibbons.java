package binarysearch;

public class CuttingRibbons {

    //https://github.com/doocs/leetcode/blob/main/solution/1800-1899/1891.Cutting%20Ribbons/README_EN.md

    /*
    Approach:
    1. Binary search on the answer (possible ribbon length).
    2. Search space:
       - Low = 1 (minimum possible piece length)
       - High = maximum ribbon length (cannot make a piece longer than the longest ribbon)
    3. For each candidate length:
       - Count how many pieces of that length can be obtained from all ribbons.
       - If we can make at least k pieces, try a larger length.
       - Otherwise, try a smaller length.
    4. The largest feasible length is the answer.
    */

    public int maxLength(int[] ribbons, int k)
    {
        //todo : implement

        return 0;
    }
}
