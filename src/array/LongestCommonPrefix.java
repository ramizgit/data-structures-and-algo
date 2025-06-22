package array;

public class LongestCommonPrefix {
    //https://leetcode.com/problems/longest-common-prefix/description/

    public static void main(String[] args)
    {
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"})); //fl
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","fight"})); //f
        System.out.println(longestCommonPrefix(new String[]{"dog","racecar","car"})); //""
    }

    private static String longestCommonPrefix(String[] strs)
    {
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<strs[0].length(); i++){
            for(String s : strs){
                if(i == s.length() || s.charAt(i) != strs[0].charAt(i)){
                    return sb.toString();
                }
            }
            sb.append(strs[0].charAt(i));
        }

        return sb.toString();
    }
}
