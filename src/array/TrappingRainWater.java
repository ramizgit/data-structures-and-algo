package array;

public class TrappingRainWater {
    public static void main(String[] args)
    {
        System.out.println(maxRainWater(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));

        System.out.println(maxRainWater(new int[]{4,2,0,3,2,5}));
    }

    public static int maxRainWater(int[] arr)
    {
        if(arr == null || arr.length == 0){
            return 0;
        }

        int[] maxLeft = new int[arr.length];
        int[] maxRight = new int[arr.length];

        populateMaxLeftArray(arr, maxLeft);
        populateMaxRightArray(arr, maxRight);

        int maxWater = 0;

        for(int i=0; i<arr.length; i++){
            int height = Math.min(maxLeft[i], maxRight[i]) - arr[i];
            if(height > 0){
                maxWater += height;
            }
        }

        return maxWater;
    }

    public static void populateMaxLeftArray(int[] arr, int[] maxLeft)
    {
        maxLeft[0] = 0;
        maxLeft[1] = arr[0];
        for(int i=2; i<arr.length; i++){
            maxLeft[i] = Math.max(arr[i-1], maxLeft[i-1]);
        }
    }

    public static void populateMaxRightArray(int[] arr, int[] maxRight)
    {
        maxRight[maxRight.length-1] = 0;
        maxRight[maxRight.length-2] = arr[arr.length-1];
        for(int i=arr.length-3; i>=0; i--){
            maxRight[i] = Math.max(arr[i+1], maxRight[i+1]);
        }
    }
}
