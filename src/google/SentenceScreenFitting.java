package google;

public class SentenceScreenFitting {

    //https://leetcode.com/problems/sentence-screen-fitting/description/

    public int wordsTyping(String[] sentence, int rows, int cols)
    {
        /*
        Approach:-
        // Build one cyclic sentence string with spaces.
        // For each row, greedily consume 'cols' characters.
        // If we land on a space, start the next row after it.
        // Otherwise, backtrack to the previous space so that no word is split.
        // The number of complete sentence fits equals total characters placed / sentence length.
         */

        //build one full string
        StringBuilder sb = new StringBuilder();
        for(String word : sentence){
            sb.append(word).append(" ");
        }

        String fullSentence = sb.toString();
        int len = fullSentence.length();

        int pos = 0;

        for(int i=0; i<rows; i++){

            pos += cols; //for each row, greedily consume 'cols' characters

            if(fullSentence.charAt(pos % len) == ' '){
                pos++; //great, we have landed on a space
            }else{
                //oops, we landed in the middle of a word, backtrack to the previous space so that no word is split
                while(pos > 0 && fullSentence.charAt( (pos -1) %len) != ' '){
                    pos--;
                }
            }
        }

        return pos / len;
    }
}
