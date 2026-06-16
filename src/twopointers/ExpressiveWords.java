package consistenthashing.twopointers;

public class ExpressiveWords {

    //https://leetcode.com/problems/expressive-words/description/

    public int expressiveWords(String s, String[] words)
    {
        int count = 0;

        for(String word : words){
            if(isStretchy(s, word)){
                count++;
            }
        }

        return count;
    }

    private boolean isStretchy(String s, String word)
    {
        int i = 0; //ptr on s
        int j = 0; //ptr on word

        while(i < s.length() && j < word.length()){

            //early return if not stretchy
            if(s.charAt(i) != word.charAt(j)){
                return false;
            }

            char current = s.charAt(i);

            //find group in s and count
            int countS = 0;
            while(i < s.length() && s.charAt(i) == current){
                i++;
                countS++;
            }

            //find group in word and count
            int countW = 0;
            while(j < word.length() && word.charAt(j) == current){
                j++;
                countW++;
            }

            if(countW > countS){
                return false;
            }

            if(countW < countS && countS < 3){
                return false; //cant be extended if <3
            }
        }

        return i == s.length() && j == word.length(); //both strings must be fully consumed
    }
}
