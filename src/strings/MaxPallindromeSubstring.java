package strings;

public class MaxPallindromeSubstring {
    public static void main(String[] args)
    {
        System.out.println(getMaxPallindromeSubstring("aabaczwywztsssuuzzzzzzzzuusss"));
        System.out.println(getMaxPallindromeSubstring("aab"));
    }

    public static String getMaxPallindromeSubstring(String input)
    {
        //todo:check in gs codebase
        ////DO not follow this

        int largestPallindromeStartIdx = -1;
        int maxCount=0;

        for(int i=0; i<input.length(); i++)
        {
            int left = i-1;
            int right = i+1;

            //handle same char by moving just left pointer
            while(left > 0 && input.charAt(i) == input.charAt(left))
            {
                left--;
            }

            //handle same char by moving just right pointer
            while(right < input.length() && input.charAt(i) == input.charAt(right))
            {
                right++;
            }

            //handle diff char by moving both left and right pointers and comparing at the same time
            while(left > 0 && right < input.length() && input.charAt(left) == input.charAt(right)){
                left--;
                right++;
            }

            int count = right - left - 1;

            if( count > maxCount ){
                maxCount = count;
                largestPallindromeStartIdx = left + 1;
            }
        }

        System.out.println("largestPallindromeStartIdx="+largestPallindromeStartIdx);
        return input.substring(largestPallindromeStartIdx, largestPallindromeStartIdx + maxCount);
    }
}
