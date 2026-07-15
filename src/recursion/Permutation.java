package recursion;

import java.util.*;

public class Permutation {
    public static void main(String[] args)
    {
        List<Character> input = Arrays.asList('a', 'b', 'c');
        List<Character> output = new ArrayList<>();

        permute(input, output, new HashSet<>());

        //------------via swap method
        System.out.println("=============via swap===============");
        char[] arr = new char[]{'a', 'b', 'c'};
        permute(arr, 0, arr.length);

    }

    //recommended
    public static void permute(List<Character> input, List<Character> output, boolean[] visited)
    {
        //recursion end condition
        if (output.size() == input.size()) {
            System.out.println(output);
            return;
        }

        //Recursion = move to the next decision (next position/level).
        //For loop = enumerate all possible choices at the current decision.
        for (int i = 0; i < input.size(); i++) {

            // Skip if current element is already used
            if (visited[i]) {
                continue;
            }

            // Choose
            visited[i] = true;
            output.add(input.get(i));

            // Explore
            permute(input, output, visited);

            // Undo (Backtrack)
            output.removeLast();
            visited[i] = false;
        }
    }

    public static void permute(char[] arr, int left, int right)
    {
        if(left == right){
            System.out.println(arr);
            return;
        }

        for(int i=left; i<right; i++){

            swap(arr, i, left);

            permute(arr, left+1, right);

            swap(arr, left, i);
        }
    }

    private static void swap(char[] arr, int i, int j)
    {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
