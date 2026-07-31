package twopointers;

public class IsSubsequence {

    //https://leetcode.com/problems/is-subsequence/

    //n = t.length()
    //Time = O(n), as j dominates the loop, its incremented everytime
    public boolean isSubsequence(String s, String t)
    {
        int i = 0; //pointer on s
        int j = 0; //pointer on t

        while(i < s.length() && j < t.length()){

            if(s.charAt(i) == t.charAt(j)){
                //move both pointers
                i++;
                j++;
            }else{
                j++; //only increment target pointer
            }
        }

        return i == s.length();
    }
}
