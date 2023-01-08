public class MaxWaterContainer {
    public static void main(String[] args)
    {
        System.out.println(getMaxContainer(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));//14
        System.out.println(getMaxContainer(new int[]{1,8,6,2,5,4,8,3,7}));//49
    }

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
