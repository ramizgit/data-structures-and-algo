package google;

import java.util.*;

public class SortIntegersByThePowerValue {

    //https://leetcode.com/problems/sort-integers-by-the-power-value/description/

    public int getKth(int lo, int hi, int k)
    {
        //memoization map
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 0); //base case

        List<int[]> numsWithPower = new ArrayList<>();

        for(int i=lo; i<=hi; i++){
            int power = getPower(i, map);
            numsWithPower.add(new int[]{i, power});
        }

        //sort by power ascending, if tie sort by num ascending
        numsWithPower.sort( (a, b) -> {

            if(a[1] == b[1]){
                return a[0] - b[0]; //if same power, sort nums in asc
            }

            return a[1] - b[1]; //sort by asc power
        } );

        return numsWithPower.get(k-1)[0];
    }

    private int getPower(int num, Map<Integer, Integer> map)
    {
        int originalNum = num;

        if(map.containsKey(num)){
            return map.get(num);
        }

        //else compute
        int power = 0;

        while(num != 1){
            if(num % 2 == 0){
                num /= 2;
            }else{
                num = 3 * num + 1;
            }

            power++;
        }

        map.put(originalNum, power);

        return power;
    }
}
