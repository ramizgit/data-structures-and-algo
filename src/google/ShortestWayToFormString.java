package twoPointers;

public class ShortestWayToFormString {

    //https://leetcode.com/problems/shortest-way-to-form-string/description/

    public int shortestWay(String source, String target)
    {
        int t = 0;
        int count = 0;

        // keep forming subsequences until target is fully matched
        while(t < target.length()) {
            int oldT = t; // track progress in this pass

            // one full scan of source = one subsequence
            for(int s = 0; s < source.length() && t < target.length(); s++) {
                if(source.charAt(s) == target.charAt(t)){
                    t++;
                }
            }

            // no character matched in this entire pass → impossible
            if(oldT == t){
                return -1;
            }

            count++;
        }

        return count;
    }
}
