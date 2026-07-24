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
        //input validation
        if(ribbons == null || ribbons.length == 0){
            return 0;
        }

        if(k <= 0){
            return 0;
        }

        int max = 0;
        for(int ribbon : ribbons){
            max = Math.max(max, ribbon);
        }

        int low = 1; //minimum possible piece length
        int high = max; //cannot make a piece longer than the longest ribbon
        int answer = 0;

        while(low <= high){

            int mid = low + (high - low)/2;

            if(feasibleToCut(ribbons, mid, k)){
                answer = mid; //possible answer
                low = mid + 1; //try higher to maximize the answer
            }else{
                high = mid - 1; //not feasible to cut, try lower
            }
        }

        return answer;
    }

    private boolean feasibleToCut(int[] ribbons, int len, int k)
    {
        long count = 0;

        for (int ribbon : ribbons) {
            count += ribbon / len;

            if (count >= k) {
                return true; //early exit
            }
        }

        return false;
    }
}
