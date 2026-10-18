package binarysearch.answersearch;

/*
You are given an integer array wood, where wood[i] represents the length of the \(i\)-th piece of wood, and an integer k.
Cut the wood pieces into smaller pieces such that:
- Every resulting piece has the same integer length.
- You need to obtain at least k pieces.
- You can discard leftover wood.
- Return the maximum possible length of each piece.
- If you cannot obtain k pieces, return 0.
Example 1
Input: wood = [5, 9, 7], k = 3
Output: 5


Explanation:
- Wood of length 5 produces one piece of length 5.
- Wood of length 9 produces one piece of length 5.
- Wood of length 7 produces one piece of length 5.
Total: 3 pieces of length 5.
Example 2
Input: wood = [5, 9, 7], k = 4
Output: 4


Explanation:
- 5 / 4 = 1 piece
- 9 / 4 = 2 pieces
- 7 / 4 = 1 piece
Total: 4 pieces of length 4. A length of 5 produces only 3 pieces, so 4 is optimal.
 */

public class WoodCut {

    public int maxWookLen(int[] wood, int k)
    {
        //input validation
        if (wood == null || wood.length == 0 || k <= 0) {
            return 0;
        }

        int max = 0;
        for(int w : wood){
            max = Math.max(max, w);
        }

        //binary search
        int low = 1; //min possible wood length
        int high = max; //max possible wood length
        int answer = 0;

        while(low <= high){

            int mid = low + (high - low) / 2;

            if(canCutAtLength(wood, k, mid)){
                answer = mid; //possible answer
                low = mid + 1; //try bigger to maximise the answer
            }else{
                high = mid - 1; //try smaller
            }
        }

        return answer;
    }

    private boolean canCutAtLength(int[] wood, int k, int length)
    {
        int count = 0;

        for(int w : wood){
            count += w/length;

            //early exit: we already have enough pieces
            if (count >= k) {
                return true;
            }
        }

        return false;
    }
}
