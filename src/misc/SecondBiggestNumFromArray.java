package misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecondBiggestNumFromArray {

    public static void main(String[] args)
    {
        int[] arr = {1, 2, 10};//2110
        System.out.println("second largest : " +secondLargestNum(arr));
    }

    private static String secondLargestNum(int[] arr)
    {
        //covert to list of string
        List<String> numList = new ArrayList<>();
        for(int i : arr){
            numList.add(Integer.toString(i));
        }

        //custom comparator
        Collections.sort(numList, (a, b) -> (b+a).compareTo(a+b));

        String largestNum = String.join("", numList);

        System.out.println("Larget num : "+ largestNum);

        String secondLargest = null;
        List<String> numListCopy = new ArrayList<>(numList);

        for(int i=0; i<numListCopy.size()-1; i++){
            //swap
            String temp = numListCopy.get(i);
            numListCopy.set(i, numListCopy.get(i+1));
            numListCopy.set(i+1, temp);

            //get number
            String tmpNum = String.join("", numListCopy);

            if( (secondLargest == null) || (tmpNum.compareTo(secondLargest) > 0)){
                secondLargest = tmpNum;
            }
        }

        return secondLargest;
    }
}
