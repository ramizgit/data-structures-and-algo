package array;

import java.util.HashMap;
import java.util.Map;

public class LargetSubArrayWithZeroSum {
    public static void main(String[] args)
    {
        getLargestSubArrayWithZeroSum(new int[]{1, -1, 3, 2, -2, -8, 1, 7, 10, 23});
    }

    public static void getLargestSubArrayWithZeroSum(int[] arr)
    {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxLength = 0;
        int start = 0;
        int end = 0;

        for(int i=0; i<arr.length; i++){
            sum += arr[i];

            if(map.containsKey(sum)){
                int len = i - map.get(sum);
                if(len > maxLength){
                    maxLength = len;
                    start = map.get(sum)+1;
                    end = i;
                }
            }else {
                map.put(sum, i);
            }
        }

        System.out.println("max length : "+maxLength);
        System.out.println("start : "+start);
        System.out.println("end : "+end);


    }
}
