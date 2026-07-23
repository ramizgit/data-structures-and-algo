package sliding_window;

public class PermutationInString {

    //https://leetcode.com/problems/permutation-in-string/description/

    public boolean checkInclusion(String s1, String s2)
    {
        //input validation
        if (s1 == null || s2 == null) {
            return false;
        }

        if (s1.isEmpty()) {
            return true;
        }

        if (s1.length() > s2.length()) {
            return false;
        }

        //build freq of s1
        int[] need = new int[26]; //since only lowercase english letters

        for(char ch : s1.toCharArray()){
            need[ch - 'a']++;
        }

        int windowStart = 0;
        int[] window = new int[26];

        //slide through s2
        for(int windowEnd = 0; windowEnd < s2.length(); windowEnd++){

            //expand window
            char ch = s2.charAt(windowEnd);
            window[ch - 'a']++;

            //proceed only if window of size s1 is built
            if(windowEnd - windowStart + 1 < s1.length()){
                continue;
            }

            //compare need vs window
            if(compare(need, window)){
                return true;
            }

            //shrink window
            char lch = s2.charAt(windowStart++);
            window[lch - 'a']--;
        }

        return false;
    }

    private boolean compare(int[] need, int[] window)
    {
        for(int i=0; i<26; i++){
            if(need[i] != window[i]){
                return false;
            }
        }

        return true;
    }
}
