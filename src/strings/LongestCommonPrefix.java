package misc;

public class LongestCommonPrefix {

    public static void main(String[] args)
    {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "fly"}));
        System.out.println(longestCommonPrefix(new String[]{"flower", "high", "five"}));
        System.out.println(longestCommonPrefix(new String[]{"a", "b"}));
        System.out.println(longestCommonPrefix(new String[]{"a", ""}));
    }

    private static String longestCommonPrefix(String[] inputs)
    {
        if(inputs == null || inputs.length == 0){
            return "";
        }

        String prefix = inputs[0];

        for(int i=1; i<inputs.length; i++){
            while(!inputs[i].startsWith(prefix)){
                prefix = prefix.substring(0, prefix.length()-1);

                if(prefix.isEmpty()){
                    return "";
                }
            }
        }

        return prefix;
    }
}
