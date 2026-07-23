package sliding_window;

public class MinimumWindowSubstringv2 {

    //https://leetcode.com/problems/minimum-window-substring/description/

    public String minWindow(String s, String t)
    {

        //input validation
        if(s == null || t == null){
            return "";
        }

        if(t.length() > s.length()){
            return "";
        }

        //build frequency of chars in t
        int[] need = new int[128]; //all ASCII including uppercase and lowercase English letters

        for(char ch : t.toCharArray()){
            need[ch]++;
        }

        int windowStart = 0;
        int[] window = new int[128];
        int minLen = Integer.MAX_VALUE;
        int minStart = -1;

        //slide through s to find min window substring
        for(int windowEnd = 0; windowEnd < s.length(); windowEnd++){

            //expand window
            char ch = s.charAt(windowEnd);
            window[ch]++;

            //proceed only if window size is at least size of t
            if(windowEnd - windowStart + 1 < t.length()){
                continue;
            }

            //shrink window till substring criteria meets
            while(isSubString(need, window)){

                int windowLen = windowEnd - windowStart + 1;

                if (windowLen < minLen) {
                    minLen = windowLen;
                    minStart = windowStart;
                }

                char lch = s.charAt(windowStart);
                window[lch]--;
                windowStart++;
            }
        }

        return minStart == -1 ? "" : s.substring(minStart, minStart + minLen);
    }

    private boolean isSubString(int[] need, int[] window)
    {
        for(int i=0; i<128; i++){
            if(window[i] < need[i]){
                return false;
            }
        }

        return true;
    }
}
