package array;

import java.util.Map;
import java.util.TreeMap;

public class MyCalendar {

    public static TreeMap<Integer, Integer> map = new TreeMap<>();

    public static void main(String[] args)
    {
        System.out.println(book(10, 20)); //true
        System.out.println(book(15, 25)); //false
        System.out.println(book(20, 30)); //true
        System.out.println(book(40, 50)); //true
        System.out.println(book(35, 45)); //false
    }

    public static boolean book(int start, int end)
    {
        //check floor
        Map.Entry<Integer, Integer> floorEntry = map.floorEntry(start);
        if( floorEntry != null && start < floorEntry.getValue() ){
            return false;
        }

        //check ceiling
        Map.Entry<Integer, Integer> ceilingEntry = map.ceilingEntry(start);
        if( ceilingEntry != null && end > ceilingEntry.getKey()){
            return false;
        }

        map.put(start, end);
        return true;
    }
}
