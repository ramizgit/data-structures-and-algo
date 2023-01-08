package recursion;

import java.util.*;

public class MobileKeypadPressCombination {
    public static void main(String[] args)
    {
        int[] arr = {2,3,4};
        Map<Integer, List<Character>> map = new HashMap<>();
        map.put(2, Arrays.asList('a', 'b', 'c'));
        map.put(3, Arrays.asList('d', 'e', 'f'));
        map.put(4, Arrays.asList('g', 'h', 'i'));

        backtrack(arr, map, new ArrayList<>(), 0);

    }

    public static void backtrack(int[] arr, Map<Integer, List<Character>> map, List<Character> result, int index)
    {
        if(result.size() == arr.length){
            System.out.println(result);
            return;
        }

        for(char ch : map.get(arr[index])){
            result.add(ch);
            backtrack(arr, map, result, index+1);
            result.remove(result.size()-1);
        }
    }
}
