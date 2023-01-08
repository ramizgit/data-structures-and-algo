package array;

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {

    public static void main(String[] args)
    {
        System.out.println(findSbArray(new int[]{23,2,4,6,7}, 6)); //true
        System.out.println(findSbArray(new int[]{23,2,6,4,7}, 6)); //true
        System.out.println(findSbArray(new int[]{23,2,6,4,7}, 13)); //false
    }

    public static boolean findSbArray(int[] arr, int k)
    {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = arr[0];

        for(int i=1; i<arr.length; i++)
        {
            sum += arr[i];
            int rem = sum % k;
            if(rem == 0){
                return true;
            }else {
                if(map.containsKey(rem)){
                    if( (i - map.get(rem)) >= 2){
                        return true;
                    }
                }else {
                    map.put(rem, i);
                }
            }
        }
        return false;
    }
}
