package twopointers;

public class IsSubsequence {

    //https://leetcode.com/problems/is-subsequence/

    public boolean isSubsequence(String s, String t)
    {
        int i = 0;
        int j = 0;

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
