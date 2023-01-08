package array;

import java.util.Arrays;
import java.util.Random;

public class RandomPickWithWeight {

    private static int[] arr = new int[5];
    private static int[] weightedArr = new int[5];

    public static void main(String[] args)
    {
        arr[0] = 1;
        arr[1] = 3;
        arr[2] = 7;
        arr[3] = 11;
        arr[4] = 15;

        weightedArr[0] = arr[0];

        for(int i=1; i<arr.length; i++){
            weightedArr[i] = arr[i] + weightedArr[i-1];
        }

        int sum = weightedArr[weightedArr.length-1];

        System.out.println(pickIndex(sum));
        System.out.println(pickIndex(sum));
        System.out.println(pickIndex(sum));
        System.out.println(pickIndex(sum));
        System.out.println(pickIndex(sum));

    }

    private static int pickIndex(int sum)
    {
        int nextInt = new Random().nextInt(sum)+1;

        int idx =  Arrays.binarySearch(weightedArr, nextInt);

        return idx >= 0 ? idx : (-1 * idx) - 1;
    }
}
