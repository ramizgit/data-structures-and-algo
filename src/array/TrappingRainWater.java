package array;

public class TrappingRainWater {
    public static void main(String[] args)
    {
        System.out.println(maxRainWater(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})); //6
        System.out.println(maxRainWater(new int[]{4,2,0,3,2,5})); //9
    }

    public static int maxRainWater(int[] arr)
    {
        if(arr == null || arr.length == 0){
            return 0;
        }

        int[] maxLeft = getMaxLeftArray(arr);
        int[] maxRight = getMaxRightArray(arr);

        int maxWater = 0;

        for(int i=0; i<arr.length; i++){
            int height = Math.min(maxLeft[i], maxRight[i]) - arr[i];
            if(height > 0){
                maxWater += height;
            }
        }

        return maxWater;
    }

    public static int[] getMaxLeftArray(int[] arr)
    {
        int[] maxLeft = new int[arr.length];
        maxLeft[0] = 0;
        for(int i=1; i<arr.length; i++){
            maxLeft[i] = Math.max(arr[i-1], maxLeft[i-1]);
        }
        return maxLeft;
    }

    public static int[] getMaxRightArray(int[] arr)
    {
        int[] maxRight = new int[arr.length];
        maxRight[maxRight.length-1] = 0;
        for(int i=arr.length-2; i>=0; i--){
            maxRight[i] = Math.max(arr[i+1], maxRight[i+1]);
        }
        return maxRight;
    }
}
