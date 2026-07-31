package google;

public class ShortestWayToFormString {

    //https://leetcode.com/problems/shortest-way-to-form-string/description/
    //https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1055.Shortest%20Way%20to%20Form%20String/README_EN.md

    public int shortestWay(String source, String target)
    {
        int t = 0; //pointer on target
        int count = 0;

        //keep scanning source until we've matched every character in the target
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
