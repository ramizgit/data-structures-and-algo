package subarray;

public class MinSizeSubArraySumInAPositiveArray {
    public static void main(String[] args)
    {
        System.out.println("min sub array count : "+ minSizeSubArrayWithSumEqualK(new int[]{2,3,1,2,4,3}, 7));
        System.out.println("min sub array count : "+ minSizeSubArrayWithSumEqualK(new int[]{2,3,1,2,4,3}, 6));
        System.out.println("min sub array count : "+ minSizeSubArrayWithSumEqualK(new int[]{2,3,1,2,4,3}, 10));
        System.out.println("min sub array count : "+ minSizeSubArrayWithSumEqualK(new int[]{2,3,1,2,1,4,3,2}, 9));

        System.out.println("AT LEAST :::::::::::::::::");
        System.out.println("min sub array count : "+ minSizeSubArrayWithSumAtleastK(new int[]{2,3,1,2,4,3}, 7));
    }

    public static int minSizeSubArrayWithSumEqualK(int[] arr, int k)
    {
        int i = 0;
        int j = 0;
        int sum = arr[i];
        int minSize = Integer.MAX_VALUE;
        int subArrayCount = 0;


        while (i < arr.length && j < arr.length){
            if(sum >= k){
                if(sum == k){
                    minSize = Math.min(minSize, (j-i)+1);
                    subArrayCount++;
                }

                //decrease window
                sum -= arr[i++];
            }else{
                if(j == arr.length-1){
                    break;
                }
                //increase window size
                sum += arr[++j];
            }
        }

        System.out.println("sub array count : "+subArrayCount);
        return minSize;
    }

    public static int minSizeSubArrayWithSumAtleastK(int[] arr, int k)
    {
        int i = 0;
        int j = 0;
        int sum = arr[i];
        int minSize = Integer.MAX_VALUE;
        int subArrayCount = 0;

        while (i < arr.length && j < arr.length){

            if(sum >= k){
                minSize = Math.min(minSize, (j-i)+1);
                subArrayCount++;

                //decrease window
                sum -= arr[i++];
            }else {
                if(j == arr.length-1){
                    break;
                }

                //increase window size
                sum += arr[++j];
            }
        }

        System.out.println("sub array count : "+subArrayCount);
        return minSize;
    }
}
