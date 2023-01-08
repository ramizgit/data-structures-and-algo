package array;

import java.util.ArrayList;
import java.util.List;

public class LeetcodeTextJustification {

    public static void main(String[] args)
    {
        /*
        Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
         */

    }

    public List<String> fullJustify(String[] words, int maxWidth)
    {
        int i = 0;
        List<String> output = new ArrayList<>();

        while (i < words.length){
            String string = words[i];
            int length = words[i].length();
            int j = i+1;

            //move pointer j forward till the word can be included
            while( j < words.length && words[j].length() <= (maxWidth - length - (j -i)) ){
                length += words[j].length();
                j++;
            }

            //now take care of spaces
            int spaces = maxWidth - length; //total spaces to be assigned on this line
            int numOfWords = j - 1;

            if(numOfWords == 1 || j >= words.length){
                output.add(leftJustify(words, spaces, i, j));
            }else {
                output.add(rightJustify(words, spaces, i, j));
            }

            i = j;
        }

        return output;
    }

    public static String leftJustify(String[] words, int spaces, int i, int j)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(words[i]);

        for(int k=i+1; k<j; k++){
            sb.append(" "+words[k]);
        }

        int spacesOnRight = spaces - (j-i);
        for(int k=0; k<spacesOnRight; k++){
            sb.append(" ");
        }
        return sb.toString();
    }

    public static String rightJustify(String[] words, int spaces, int i, int j)
    {
        //todo:implement
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
}
