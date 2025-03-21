package misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BiggestNumFromArray {

    public static void main(String[] args)
    {
        int[] arr = {3, 30, 34, 5, 9};
        System.out.println(biggestNum(arr));
    }

    private static String biggestNum(int[] arr)
    {
        if(arr == null || arr.length == 0){
            return "";
        }

        //convert to List<String>
        List<String> nums = new ArrayList<>();
        for(int a : arr){
            nums.add(Integer.toString(a));
        }

        //now sort srting using custom comapartor
        Collections.sort(nums, (a, b) -> (b+a).compareTo(a+b));

        StringBuilder sb = new StringBuilder();
        for(String n : nums){
            sb.append(n);
        }

        return sb.toString();
    }
}
