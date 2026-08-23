package twopointers;

public class MaxWaterContainer {

    //https://leetcode.com/problems/container-with-most-water/

    public static int getMaxContainer(int[] arr)
    {
        int start = 0;
        int end = arr.length - 1;
        int max = 0;

        while(start < end)
        {
            int height = Math.min(arr[start], arr[end]);
            int width = end - start;

            max = Math.max(max, (height * width));

            if(arr[start] < arr[end]){
                start++;
            }else{
                end--;
            }
        }

        return max;
    }
}
