package strings;

public class ShortestWordDistanceLeetcode {

    public static void main(String[] args)
    {
        System.out.println(shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "coding","practice")); //3
        System.out.println(shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "makes","practice")); //1
    }

    private static int shortestDistance(String[] words, String word1, String word2)
    {
        int index1 = -1;
        int index2 = -1;
        int min = Integer.MAX_VALUE;

        for(int i=0; i<words.length; i++){
            if(words[i].equals(word1)){
                index1 = i;
            }else if(words[i].equals(word2)){
                index2 = i;
            }

            if(index1 != -1 && index2 != -1){
                min = Math.min(min, Math.abs(index1-index2));
            }
        }

        return min;
    }
}
