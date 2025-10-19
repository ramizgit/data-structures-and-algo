package strings;

public class RunLengthEncoding {
    public static void main(String[] args)
    {
        System.out.println(runLengthEncoding("abbcccdddd"));
        System.out.println(runLengthEncoding("aabbcccddddzzzzzzzzzzz"));
        System.out.println(runLengthEncoding("abcde"));

        System.out.println(runLengthEncoding2("abbcccdddd"));
        System.out.println(runLengthEncoding2("aabbcccddddzzzzzzzzzzz"));
        System.out.println(runLengthEncoding2("abcde"));
    }

    public static String runLengthEncoding(String input)
    {
        StringBuilder output = new StringBuilder();
        int left = 0;
        int right = 1;

        while (right < input.length()){
            if(input.charAt(left) != input.charAt(right)){
                output.append(right-left).append(input.charAt(left));
                left = right;
            }
            right++;
        }

        //append left over
        output.append(right-left).append(input.charAt(left));

        return output.toString();
    }

    public static String runLengthEncoding2(String input)
    {
        StringBuilder output = new StringBuilder();
        int left = 0;
        int right = 1;

        while (right < input.length()){
            if(input.charAt(left) != input.charAt(right)){
                output.append(input.charAt(left));
                output.append(right-left);
                left = right;
            }
            right++;
        }

        //append left over
        output.append(input.charAt(left));
        output.append(right-left);

        return output.toString();
    }
}
