package google;

public class SentenceScreenFitting {

    //https://leetcode.com/problems/sentence-screen-fitting/description/

    public int wordsTyping(String[] sentence, int rows, int cols)
    {
        StringBuilder sb = new StringBuilder();
        for(String word : sentence){
            sb.append(word).append(" ");
        }

        String fullString = sb.toString();
        int len = fullString.length();

        int pos = 0;

        for(int i=0; i<rows; i++){
            pos += cols;

            if(fullString.charAt(pos % len) == ' '){
                pos++;
            }else{
                //move back till space
                while(pos > 0 && fullString.charAt( (pos -1) %len) != ' '){
                    pos--;
                }
            }
        }

        return pos / len;
    }
}
